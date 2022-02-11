package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.CategoryMapper;
import com.djq.springGarden.entity.Category;
import com.djq.springGarden.service.CategoryService;




/**
 * 房间类型Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 条件查询房间类型列表
     *
     * @param category  房间类型
     * @return 房间类型
     */
    @Override
    public List<Category> select(Category category) {
        return categoryMapper.select(category);
    }

    /**
     * 条件查询房间类型单个
     *
     * @param category 房间类型
     * @return 房间类型
     */
    @Override
    public Category selectOne(Category category) {
        return categoryMapper.selectOne(category);
    }

    /**
     * 新增房间类型
     *
     * @param category 房间类型
     * @return 结果
     */
    @Override
    public int insertCategory(Category category) {
        return categoryMapper.insert(category);
    }

    /**
     * 修改房间类型
     *
     * @param category 房间类型
     * @return 结果
     */
    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 删除房间类型信息
     *
     * @param id 房间类型主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Integer id) {

        Category category = new Category();
        category.setId(id);
        return categoryMapper.delete(category);
    }

}
