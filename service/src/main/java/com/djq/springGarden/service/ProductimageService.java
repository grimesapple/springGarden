package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Productimage;

/**
 * 客房图片Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface ProductimageService {
    /**
     * 条件查询客房图片列表
     *
     * @param productimage 客房图片
     * @return 客房图片集合
     */
    List<Productimage> select(Productimage productimage);

    /**
     * 条件查询客房图片:查询单个
     *
     * @param  productimage 客房图片
     * @return 客房图片
     */
     Productimage selectOne(Productimage productimage);


    /**
     * 新增客房图片
     *
     * @param productimage 客房图片
     * @return 结果
     */
    int insertProductimage(Productimage productimage);

    /**
     * 修改客房图片
     *
     * @param productimage 客房图片
     * @return 结果
     */
    int updateProductimage(Productimage productimage);

    /**
     * 删除客房图片信息
     *
     * @param id 客房图片主键
     * @return 结果
     */
    int deleteProductimageById(Integer id);
}
