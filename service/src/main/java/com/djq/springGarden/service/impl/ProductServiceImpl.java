package com.djq.springGarden.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import cn.hutool.core.convert.Convert;
import com.djq.springGarden.entity.*;
import com.djq.springGarden.mapper.OrderMapper;
import com.djq.springGarden.mapper.ProductimageMapper;
import com.djq.springGarden.mapper.PropertyvalueMapper;
import com.djq.springGarden.service.*;
import com.djq.springGarden.vo.ProductSearchVo;
import com.djq.springGarden.vo.ProductVO;
import io.swagger.models.auth.In;
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
     * 房间的属性情况
     */
    @Resource
    private OrderMapper orderMapper;


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
     * 对应房间能有房和无房的时间列表
     *
     * @param productId 房间id
     * @return 时间列表
     */
    @Override
    public List<String> timeList(Integer productId) {
        //查询对应房间的今天之后的所有订单
        Example example = new Example(OrderT.class);
        Example.Criteria criteria = example.createCriteria();
        Example.Criteria and = example.and();
        criteria.andEqualTo("productId", productId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        criteria.andCondition("start_Time >=", calendar.getTime());
        and.andEqualTo("status", 1);
        and.orEqualTo("status", 2);
        List<OrderT> orderTList = orderMapper.selectByExample(example);
        //封装时间
        ArrayList<String> timeList = new ArrayList<>();
        for (OrderT orderT : orderTList) {
            Date startTime = orderT.getStartTime();
            Date endTime = orderT.getEndTime();
            long diff = (endTime.getTime() - startTime.getTime()) / 1000 / 3600 / 24;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String startString = format.format(startTime);
            Calendar c = Calendar.getInstance();
            //设置当前日期
            c.setTime(startTime);
            for (int i = 0; i < diff; i++) {
                c.add(Calendar.DATE, i);
                String time = format.format(c.getTime());
                timeList.add(time);
            }
        }
        return timeList;
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


            //价格
            if (productSearchVo.getStartPrice() != null && productSearchVo.getEndPrice() != null) {
                if (pro.getPromotePrice().compareTo(productSearchVo.getStartPrice()) < 0 || pro.getPromotePrice().compareTo(productSearchVo.getEndPrice()) > 0) {
                    //不符合条件 跳过;
                    continue;
                }
            }

            //判断当前房间在对应时间是否有订单
            int status = 0;
            if (productSearchVo.getStartTime() != null && productSearchVo.getEndTime() != null) {
                if (!orderService.check(productSearchVo.getStartTime(), productSearchVo.getEndTime(), proId)) {
                    //有订单，该房间不能被租
                    status = 1;
                }
            }

            //封装房间的属性
            Propertyvalue propertyvalue = new Propertyvalue();
            propertyvalue.setProductId(proId);
            List<Propertyvalue> propertyvalueList = propertyvalueService.select(propertyvalue);
            ArrayList<Integer> integerIdList = new ArrayList<>();
            List<Map<String, Object>> maps = new ArrayList<>();
            for (Propertyvalue propertyvalue1 : propertyvalueList) {
                integerIdList.add(propertyvalue1.getPropertyId());
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
            //筛选属性，如果不完成包含条件中的属性列表，则去掉
            List<Integer> properties = productSearchVo.getProperties();
            if (properties != null) {
                if (!integerIdList.containsAll(properties)) {
                    continue;
                }
            }


            //房间的图片列表
            Productimage productimage = new Productimage();
            productimage.setProductId(proId);
            List<Productimage> imageList = productimageMapper.select(productimage);



            //封装房间的信息
            result.put("house", pro);
            //房间的图片列表
            result.put("img", imageList);
            //属性
            result.put("property", maps);
            //房间状态
            result.put("status", status);

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
     * 管理员查询所有房屋列表进行订单添加
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


            //查询图片信息
            Integer id = productVO.getId();
            Productimage productimage = new Productimage();
            productimage.setProductId(id);
            List<Productimage> images = productimageMapper.select(productimage);

            //封装返回结果
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
        for (Productimage img : productVO.getImgs()) {
            //添加图片
            img.setProductId(id);
            productimageList.add(img);
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
        if (propertyValueList.size() != 0) {
            propertyvalueMapper.insertList(propertyValueList);
        }
        return insert;
    }

    /**
     * 修改客房;
     *
     * @param productVO 客房;
     * @return 结果
     */
    @Override
    public int updateProduct(ProductVO productVO) {
        //修改房屋信息
        int update = productMapper.updateByPrimaryKeySelective(productVO);
        if (update <= 0) {
            return -1;
        }
        //修改图片列表
        Integer id = productVO.getId();
        List<Productimage> imgs = productVO.getImgs();
        for (Productimage img : imgs) {
            if (img.getId() == null) {
                //新增
                img.setProductId(id);
                productimageMapper.insert(img);
            } else {
                productimageMapper.updateByPrimaryKeySelective(img);
            }
        }
        //修改属性
        List<Integer> properties = productVO.getProperties();
        if (properties == null || properties.size() <= 0) {
            return update;
        }
        //查询原来的属性列表
        Propertyvalue propertyvalueCon = new Propertyvalue();
        propertyvalueCon.setProductId(id);
        List<Propertyvalue> propertyvalues = propertyvalueMapper.select(propertyvalueCon);
        //大于0，减少了。小于0，增加了。等于0更换
        int diff = propertyvalues.size() - properties.size();
        int i = 1;
        for (Integer propertyId : properties) {
            //判断属性是多还是少
            if ((diff <= 0 && i <= propertyvalues.size())) {
                //更新
                Propertyvalue propertyvalue = propertyvalues.get(i - 1);
                propertyvalue.setPropertyId(propertyId);
                propertyvalueMapper.updateByPrimaryKeySelective(propertyvalue);
            } else if (diff < 0) {
                //新增
                Propertyvalue propertyvalue = new Propertyvalue();
                propertyvalue.setProductId(id);
                propertyvalue.setPropertyId(propertyId);
                propertyvalue.setStatus(0);
                propertyvalueMapper.insert(propertyvalue);
            }
            i++;
        }
        if (diff > 0) {
            //删除
            for (int j = 1; j <= diff; j++) {
                Propertyvalue propertyvalue = propertyvalues.get(propertyvalues.size() - j);
                propertyvalueMapper.deleteByPrimaryKey(propertyvalue);
            }
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
