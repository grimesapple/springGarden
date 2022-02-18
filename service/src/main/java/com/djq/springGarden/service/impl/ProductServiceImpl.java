package com.djq.springGarden.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.convert.Convert;
import com.djq.springGarden.entity.Category;
import com.djq.springGarden.entity.Productimage;
import com.djq.springGarden.mapper.CategoryMapper;
import com.djq.springGarden.mapper.ProductimageMapper;
import com.djq.springGarden.service.CategoryService;
import com.djq.springGarden.service.ProductimageService;
import com.djq.springGarden.vo.ProductVO;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.ProductMapper;
import com.djq.springGarden.entity.Product;
import com.djq.springGarden.service.ProductService;


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
     * 房间图片
     */
    @Resource
    private ProductimageMapper productimageMapper;






    /**
     * 条件查询客房;商品信息相关：分类，商品图片，商品规格，商品参数列表
     *
     * @param product  客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @Override
    public List<Product> select(Product product) {
        return productMapper.select(product);
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

        for (String s : product.getFileList() ) {
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
