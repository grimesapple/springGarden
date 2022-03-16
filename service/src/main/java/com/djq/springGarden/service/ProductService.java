package com.djq.springGarden.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.vo.ProductSearchVo;
import com.djq.springGarden.vo.ProductVO;

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
     *
     * 对应房间能有房和无房的时间列表
     *
     * @param productId 房间id
     * @return 时间列表
     */
    List<String> timeList(Integer productId);



    /**
     * 客房界面展示列表查询接口
     * @param productSearchVo 查询条件
     * @return 返回客房信息，简介，图片，部分属性。
     */
    List<Map<String,Object>> search(ProductSearchVo productSearchVo);


    /**
     * 条件查询客房;商品信息相关：分类，商品图片，商品规格，商品参数:查询单个
     *
     * @param  product 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
     Product selectOne(Product product);

    /**
     * 管理员查询所有房屋列表
     * @return 返回结果
     */
    List<ProductVO> listByAdmin(Product product);


    /**
     * 新增客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param productVO 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    int insertProduct(ProductVO productVO);

    /**
     * 修改客房;商品信息相关：分类，商品图片，商品规格，商品参数
     *
     * @param productVO 客房;商品信息相关：分类，商品图片，商品规格，商品参数
     * @return 结果
     */
    int updateProduct(ProductVO productVO);

    /**
     * 删除客房;商品信息相关：分类，商品图片，商品规格，商品参数信息
     *
     * @param id 客房;商品信息相关：分类，商品图片，商品规格，商品参数主键
     * @return 结果
     */
    int deleteProductById(Integer id);
}
