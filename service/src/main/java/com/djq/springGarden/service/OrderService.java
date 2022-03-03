package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Order;
import com.djq.springGarden.vo.OrderSearchVo;

/**
 * 订单Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface OrderService {
    /**
     * 条件查询订单列表
     *
     * @param order 订单
     * @return 订单集合
     */
    List<Order> select(Order order);

    /**
     * 条件查询订单:查询单个
     *
     * @param  order 订单
     * @return 订单
     */
     Order selectOne(Order order);


    /**
     * 新增订单
     *
     * @param orderSearchVo 订单信息
     * @return 结果
     */
    int insertOrder(OrderSearchVo orderSearchVo);

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    int updateOrder(Order order);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    int deleteOrderById(Integer id);
}
