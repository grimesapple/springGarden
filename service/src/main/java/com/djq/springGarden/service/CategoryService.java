package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Category;

/**
 * 房间类型Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface CategoryService {
    /**
     * 条件查询房间类型列表
     *
     * @param category 房间类型
     * @return 房间类型集合
     */
    List<Category> select(Category category);

    /**
     * 条件查询房间类型:查询单个
     *
     * @param  category 房间类型
     * @return 房间类型
     */
     Category selectOne(Category category);


    /**
     * 新增房间类型
     *
     * @param category 房间类型
     * @return 结果
     */
    int insertCategory(Category category);

    /**
     * 修改房间类型
     *
     * @param category 房间类型
     * @return 结果
     */
    int updateCategory(Category category);

    /**
     * 删除房间类型信息
     *
     * @param id 房间类型主键
     * @return 结果
     */
    int deleteCategoryById(Integer id);
}
