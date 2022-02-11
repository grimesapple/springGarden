package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.OrderMapper;
import com.djq.springGarden.entity.Order;
import com.djq.springGarden.service.OrderService;


import javax.annotation.Resource;


/**
 * 订单Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    /**
     * 条件查询订单列表
     *
     * @param order  订单
     * @return 订单
     */
    @Override
    public List<Order> select(Order order) {
        return orderMapper.select(order);
    }

    /**
     * 条件查询订单单个
     *
     * @param order 订单
     * @return 订单
     */
    @Override
    public Order selectOne(Order order) {
        return orderMapper.selectOne(order);
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Integer id) {

        Order order = new Order();
        order.setId(id);
        return orderMapper.delete(order);
    }

}
