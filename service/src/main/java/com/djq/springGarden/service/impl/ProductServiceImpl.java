package com.djq.springGarden.service.impl;

import java.util.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import cn.hutool.core.convert.Convert;
import com.djq.springGarden.entity.*;
import com.djq.springGarden.mapper.ProductimageMapper;
import com.djq.springGarden.mapper.PropertyvalueMapper;
import com.djq.springGarden.service.*;
import com.djq.springGarden.vo.ProductSearchVo;
import com.djq.springGarden.vo.ProductVO;
import org.springframework.beans.PropertyValue;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.ProductMapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.provider.ExampleProvider;


/**
 * 客房;商品信息相关：分类，商品图片，商品规格，商品参数Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    /**
     * 类型
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 房间的图片
     */
    @Resource
    private ProductimageMapper productimageMapper;

    /**
     * 房间的属性
     */
    @Resource
    private PropertyService propertyService;

    /**
     * 房间的属性情况
     */
    @Resource
    private PropertyvalueService propertyvalueService;

    /**
     * 房间的属性情况
     */
    @Resource
    private PropertyvalueMapper propertyvalueMapper;


    /**
     * 订单
     */
    @Resource
    private OrderService orderService;


    /**
     * 条件查询客房;商品信息相关：分类，商品图片，商品规格，商品参数列表
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @Override
    public List<Product> select(Product product) {
        return productMapper.select(product);
    }


    /**
     * 客房界面展示列表查询接口：
     * 查询条件：人数，价格，时间，床数，各个属性
     *
     * @return 返回客房信息，简介，图片，部分属性。
     */
    @Override
    public List<Map<String, Object>> search(ProductSearchVo productSearchVo) {
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        //查询客房信息
        List<Product> productList = productMapper.searchAllByTime(productSearchVo);
//        List<Product> productList = select(productSearchVo);
        //按时间倒序
        System.out.println(productList);
        for (Product pro : productList) {
            HashMap<String, Object> result = new HashMap<>();
            //房间id
            Integer proId = pro.getId();
            //房间类型id
            Integer categoryId = pro.getCategoryId();

            //筛选客房信息
            //人数：客房人数根据房间最大入住人数选择
            Integer people = productSearchVo.getPeople();
            if (Objects.equals(people, pro.getPeople())) {
                //不符合条件 跳过;
                continue;
            }

            //价格
            if (productSearchVo.getStartPrice() != null && productSearchVo.getEndPrice() != null) {
                if (pro.getOrignalPrice().compareTo(productSearchVo.getStartPrice()) < 0 || pro.getOrignalPrice().compareTo(productSearchVo.getEndPrice()) > 0) {
                    //不符合条件 跳过;
                    continue;
                }
            }

            //判断当前房间在对应时间是否有订单
            if (!orderService.check(productSearchVo.getStartTime(), productSearchVo.getEndTime(), proId)) {
                //有订单，该房间不能被租
                continue;
            }

            //封装房间的信息
            result.put("house", pro);

            //封装房间的属性
            Propertyvalue propertyvalue = new Propertyvalue();
            propertyvalue.setProductId(proId);
            List<Propertyvalue> propertyvalueList = propertyvalueService.select(propertyvalue);
            List<Map<String, Object>> maps = new ArrayList<>();
            for (Propertyvalue propertyvalue1 : propertyvalueList) {
                if (propertyvalue1 == null) {
                    continue;
                }
                Map<String, Object> map = new HashMap<>();
                Integer propertyId = propertyvalue1.getPropertyId();
                Property property = new Property();
                property.setId(propertyId);
                Property property1 = propertyService.selectOne(property);
                if (property1 != null) {
                    map.put("propertyId", propertyId);
                    map.put("status", propertyvalue1.getStatus());
                    map.put("propertyName", property1.getName());
                    maps.add(map);
                }
            }
            result.put("property", maps);

            //房间的图片列表
            Productimage productimage = new Productimage();
            productimage.setProductId(proId);
            List<Productimage> imageList = productimageMapper.select(productimage);
            result.put("img", imageList);

            //房间的类型
            Category category = new Category();
            category.setId(pro.getCategoryId());
            Category selectOne = categoryService.selectOne(category);
            result.put("category", selectOne);

            //评价
//            result.put("housePrice", pro.getOrignalPrice());

            //title
//            result.put("title", pro.getName());
            resultList.add(result);
//            System.out.println(result);
        }
        return resultList;
    }

    /**
     * 条件查询客房;商品信息相关：分类，商品图片，商品规格，商品参数单个
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @Override
    public Product selectOne(Product product) {
        return productMapper.selectOne(product);
    }


    /**
     * 管理员查询所有房屋列表
     *
     * @return 返回结果
     */
    @Override
    public List<ProductVO> listByAdmin(Product product) {
        List<Product> houses = productMapper.select(product);
        List<ProductVO> houseList = new ArrayList<>();
        for (Product pro : houses) {
            ProductVO productVO = Convert.convert(ProductVO.class, pro);
//            ProductVO productVO = (ProductVO) pro;
            //查询类型名字
            Integer categoryId = pro.getCategoryId();
            Category category = new Category();
            category.setId(categoryId);
            categoryService.selectOne(category);

            //查询图片信息
            Integer id = productVO.getId();
            Productimage productimage = new Productimage();
            productimage.setProductId(id);
            List<Productimage> images = productimageMapper.select(productimage);

            //封装返回结果
            productVO.setTypeName(category.getTypeName());
            productVO.setImgs(images);
            houseList.add(productVO);
        }

        return houseList;
    }

    /**
     * 新增客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param productVO 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    @Override
//    public int insertProduct(Product product,List<String> fileList) {
    public int insertProduct(ProductVO productVO) {
        //设置当前时间
        productVO.setCreateTime(new Date());
        int insert = productMapper.insert(productVO);
        //获取客房id
        Integer id = productVO.getId();
        //图片信息添加
        List<Productimage> productimageList = new ArrayList<>();
        for (String s : productVO.getImgList()) {
            //添加图片
            Productimage productimage = new Productimage();
            productimage.setProductId(id);
            productimage.setUrl(s);
            productimageList.add(productimage);
        }
        productimageMapper.insertList(productimageList);

        //房间的属性添加
        List<Propertyvalue> propertyValueList = new ArrayList<>();
        for (Integer proId : productVO.getProperties()) {
            Propertyvalue propertyvalue = new Propertyvalue();
            propertyvalue.setProductId(id);
            propertyvalue.setPropertyId(proId);
            propertyvalue.setStatus(0);
            propertyValueList.add(propertyvalue);
        }
        propertyvalueMapper.insertList(propertyValueList);

        return insert;
    }

    /**
     * 修改客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param productVO 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    @Override
    public int updateProduct(ProductVO productVO) {
        //修改房屋信息
        productVO.setCreateTime(new Date());
        int update = productMapper.updateByPrimaryKey(productVO);
        //修改图片列表
        Integer id = productVO.getId();
        for (String s : productVO.getImgList()) {
            Productimage productimage = new Productimage();
            productimage.setProductId(id);
            productimage.setUrl(s);
            Example example = new Example(Productimage.class);
            example.createCriteria().andEqualTo("productId", id);
            productimageMapper.updateByExampleSelective(productimage, example);
        }
        //修改属性
        for (Integer proId : productVO.getProperties()) {
            Propertyvalue propertyvalue = new Propertyvalue();
            propertyvalue.setProductId(id);
            propertyvalue.setPropertyId(proId);
            propertyvalue.setStatus(0);
            Example example = new Example(Propertyvalue.class);
            example.createCriteria().andEqualTo("productId",id);
            propertyvalueMapper.updateByExampleSelective(propertyvalue,example);
        }
        return update;
    }

    /**
     * 删除客房;商品信息相关：分类，商品图片，商品规格，商品参数信息
     *
     * @param id 客房;商品信息相关：分类，商品图片，商品规格，商品参数主键
     * @return 结果
     */
    @Override
    public int deleteProductById(Integer id) {

        Product product = new Product();
        product.setId(id);
        return productMapper.delete(product);
    }

}
