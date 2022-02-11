package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Product;

/**
 * 客房;商品信息相关：分类，商品图片，商品规格，商品参数Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface ProductService {
    /**
     * 条件查询客房;商品信息相关：分类，商品图片，商品规格，商品参数列表
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 客房;商品信息相关：分类，商品图片，商品规格，商品参数集合
     */
    List<Product> select(Product product);

    /**
     * 条件查询客房;商品信息相关：分类，商品图片，商品规格，商品参数:查询单个
     *
     * @param  product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
     Product selectOne(Product product);


    /**
     * 新增客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    int insertProduct(Product product);

    /**
     * 修改客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    int updateProduct(Product product);

    /**
     * 删除客房;商品信息相关：分类，商品图片，商品规格，商品参数信息
     *
     * @param id 客房;商品信息相关：分类，商品图片，商品规格，商品参数主键
     * @return 结果
     */
    int deleteProductById(Integer id);
}
