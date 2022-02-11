package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.ProductMapper;
import com.djq.springGarden.entity.Product;
import com.djq.springGarden.service.ProductService;


import javax.annotation.Resource;


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
     * 新增客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    @Override
    public int insertProduct(Product product) {
        return productMapper.insert(product);
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
