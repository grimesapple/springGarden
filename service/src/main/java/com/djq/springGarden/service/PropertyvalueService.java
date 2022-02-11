package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Propertyvalue;

/**
 * 房间属性情况Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface PropertyvalueService {
    /**
     * 条件查询房间属性情况列表
     *
     * @param propertyvalue 房间属性情况
     * @return 房间属性情况集合
     */
    List<Propertyvalue> select(Propertyvalue propertyvalue);

    /**
     * 条件查询房间属性情况:查询单个
     *
     * @param  propertyvalue 房间属性情况
     * @return 房间属性情况
     */
     Propertyvalue selectOne(Propertyvalue propertyvalue);


    /**
     * 新增房间属性情况
     *
     * @param propertyvalue 房间属性情况
     * @return 结果
     */
    int insertPropertyvalue(Propertyvalue propertyvalue);

    /**
     * 修改房间属性情况
     *
     * @param propertyvalue 房间属性情况
     * @return 结果
     */
    int updatePropertyvalue(Propertyvalue propertyvalue);

    /**
     * 删除房间属性情况信息
     *
     * @param id 房间属性情况主键
     * @return 结果
     */
    int deletePropertyvalueById(Integer id);
}
