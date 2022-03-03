package com.djq.springGarden.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.convert.Convert;
import com.djq.springGarden.entity.*;
import com.djq.springGarden.mapper.CategoryMapper;
import com.djq.springGarden.mapper.ProductimageMapper;
import com.djq.springGarden.service.*;
import com.djq.springGarden.vo.ProductSearchVo;
import com.djq.springGarden.vo.ProductVO;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.ProductMapper;


import javax.annotation.Resource;
import javax.swing.*;


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
        HashMap<String, Object> result = new HashMap<>();
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        //查询客房信息
        List<Product> productList = select(productSearchVo);
        for (Product pro : productList) {
            //房间id
            Integer proId = pro.getId();
            //房间类型id
            Integer categoryId = pro.getCategoryId();

            //筛选客房信息
            //人数：客房人数根据房间最大入住人数选择
            Integer people = productSearchVo.getPeople();
            if (people.compareTo(pro.getPeople()) > 0) {
                //不符合条件 跳过;
                continue;
            }

            //价格
            if (pro.getOrignalPrice().compareTo(productSearchVo.getStartPrice()) < 0 || pro.getOrignalPrice().compareTo(productSearchVo.getEndPrice()) > 0) {
                //不符合条件 跳过;
                continue;
            }

            //时间：如果该时间段的当前房间的库存不够，跳过
            //查询对应房间的订单，如果在选择的时间内有预定，则跳过
            Order order = new Order();
            order.setProductId(proId);
            List<Order> orderList = orderService.select(order);
            //遍历订单在对应时间中是否有订单
            boolean flag = false;
            for (Order ord : orderList) {
                //判断状态是否为无效状态：0离店，3取消订单
                if (ord.getStatus() == 0 || ord.getStatus() == 3) {
                    continue;
                }
                if (productSearchVo.getStartTime().before(ord.getStartTime()) && productSearchVo.getEndTime().after(ord.getEndTime())) {
                    //有订单
                    flag = true;
                    break;
                }
            }
            if (flag) {
                //有订单的房间，跳过
                continue;
            }


            //封装房间的信息
            result.put("house", pro);

            //封装房间的属性
            Property property = new Property();
            property.setCategoryId(categoryId);
            List<Property> propertyList = propertyService.select(property);
            //查询是否属性情况：
            for (Property proper : propertyList) {
                Propertyvalue propertyvalue = new Propertyvalue();
                propertyvalue.setProductId(proId);
                propertyvalue.setPropertyId(proper.getId());
                List<Propertyvalue> propertyValueList = propertyvalueService.select(propertyvalue);
                for (Propertyvalue properValueItem : propertyValueList) {
                    Integer status = properValueItem.getStatus();
                    result.put(proper.getName(), status);
                }
            }

            //房间的图片列表
            Productimage productimage = new Productimage();
            productimage.setProductId(proId);
            List<Productimage> imageList = productimageMapper.select(productimage);
            result.put("images", imageList);

            resultList.add(result);
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
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    @Override
//    public int insertProduct(Product product,List<String> fileList) {
    public int insertProduct(Product product) {
        productMapper.insert(product);
        //获取客房id
        Integer id = product.getId();
        List<Productimage> productimageList = new ArrayList<>();
        //图片信息添加

        for (String s : product.getFileList()) {
            Productimage productimage = new Productimage();
            productimage.setProductId(id);
            productimage.setUrl(s);
            productimageList.add(productimage);
        }

        return productimageMapper.insertList(productimageList);
    }

    /**
     * 修改客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateByPrimaryKey(product);
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
