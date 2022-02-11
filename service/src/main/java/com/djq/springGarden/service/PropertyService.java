package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Property;

/**
 * 房间属性字典Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface PropertyService {
    /**
     * 条件查询房间属性字典列表
     *
     * @param property 房间属性字典
     * @return 房间属性字典集合
     */
    List<Property> select(Property property);

    /**
     * 条件查询房间属性字典:查询单个
     *
     * @param  property 房间属性字典
     * @return 房间属性字典
     */
     Property selectOne(Property property);


    /**
     * 新增房间属性字典
     *
     * @param property 房间属性字典
     * @return 结果
     */
    int insertProperty(Property property);

    /**
     * 修改房间属性字典
     *
     * @param property 房间属性字典
     * @return 结果
     */
    int updateProperty(Property property);

    /**
     * 删除房间属性字典信息
     *
     * @param id 房间属性字典主键
     * @return 结果
     */
    int deletePropertyById(Integer id);
}
