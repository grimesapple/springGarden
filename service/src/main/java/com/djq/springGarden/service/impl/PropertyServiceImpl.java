package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.PropertyMapper;
import com.djq.springGarden.entity.Property;
import com.djq.springGarden.service.PropertyService;


import javax.annotation.Resource;


/**
 * 房间属性字典Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Resource
    private PropertyMapper propertyMapper;

    /**
     * 条件查询房间属性字典列表
     *
     * @param property  房间属性字典
     * @return 房间属性字典
     */
    @Override
    public List<Property> select(Property property) {
        return propertyMapper.select(property);
    }

    /**
     * 条件查询房间属性字典单个
     *
     * @param property 房间属性字典
     * @return 房间属性字典
     */
    @Override
    public Property selectOne(Property property) {
        return propertyMapper.selectOne(property);
    }

    /**
     * 新增房间属性字典
     *
     * @param property 房间属性字典
     * @return 结果
     */
    @Override
    public int insertProperty(Property property) {
        return propertyMapper.insert(property);
    }

    /**
     * 修改房间属性字典
     *
     * @param property 房间属性字典
     * @return 结果
     */
    @Override
    public int updateProperty(Property property) {
        return propertyMapper.updateByPrimaryKey(property);
    }

    /**
     * 删除房间属性字典信息
     *
     * @param id 房间属性字典主键
     * @return 结果
     */
    @Override
    public int deletePropertyById(Integer id) {

        Property property = new Property();
        property.setId(id);
        return propertyMapper.delete(property);
    }

}
