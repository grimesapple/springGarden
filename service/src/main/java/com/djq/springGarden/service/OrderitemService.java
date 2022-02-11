package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Orderitem;

/**
 * 订单详情-入住人信息Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface OrderitemService {
    /**
     * 条件查询订单详情-入住人信息列表
     *
     * @param orderitem 订单详情-入住人信息
     * @return 订单详情-入住人信息集合
     */
    List<Orderitem> select(Orderitem orderitem);

    /**
     * 条件查询订单详情-入住人信息:查询单个
     *
     * @param  orderitem 订单详情-入住人信息
     * @return 订单详情-入住人信息
     */
     Orderitem selectOne(Orderitem orderitem);


    /**
     * 新增订单详情-入住人信息
     *
     * @param orderitem 订单详情-入住人信息
     * @return 结果
     */
    int insertOrderitem(Orderitem orderitem);

    /**
     * 修改订单详情-入住人信息
     *
     * @param orderitem 订单详情-入住人信息
     * @return 结果
     */
    int updateOrderitem(Orderitem orderitem);

    /**
     * 删除订单详情-入住人信息信息
     *
     * @param id 订单详情-入住人信息主键
     * @return 结果
     */
    int deleteOrderitemById(Integer id);
}
