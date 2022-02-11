package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.PropertyvalueMapper;
import com.djq.springGarden.entity.Propertyvalue;
import com.djq.springGarden.service.PropertyvalueService;


import javax.annotation.Resource;


/**
 * 房间属性情况Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class PropertyvalueServiceImpl implements PropertyvalueService {
    @Resource
    private PropertyvalueMapper propertyvalueMapper;

    /**
     * 条件查询房间属性情况列表
     *
     * @param propertyvalue  房间属性情况
     * @return 房间属性情况
     */
    @Override
    public List<Propertyvalue> select(Propertyvalue propertyvalue) {
        return propertyvalueMapper.select(propertyvalue);
    }

    /**
     * 条件查询房间属性情况单个
     *
     * @param propertyvalue 房间属性情况
     * @return 房间属性情况
     */
    @Override
    public Propertyvalue selectOne(Propertyvalue propertyvalue) {
        return propertyvalueMapper.selectOne(propertyvalue);
    }

    /**
     * 新增房间属性情况
     *
     * @param propertyvalue 房间属性情况
     * @return 结果
     */
    @Override
    public int insertPropertyvalue(Propertyvalue propertyvalue) {
        return propertyvalueMapper.insert(propertyvalue);
    }

    /**
     * 修改房间属性情况
     *
     * @param propertyvalue 房间属性情况
     * @return 结果
     */
    @Override
    public int updatePropertyvalue(Propertyvalue propertyvalue) {
        return propertyvalueMapper.updateByPrimaryKey(propertyvalue);
    }

    /**
     * 删除房间属性情况信息
     *
     * @param id 房间属性情况主键
     * @return 结果
     */
    @Override
    public int deletePropertyvalueById(Integer id) {

        Propertyvalue propertyvalue = new Propertyvalue();
        propertyvalue.setId(id);
        return propertyvalueMapper.delete(propertyvalue);
    }

}
