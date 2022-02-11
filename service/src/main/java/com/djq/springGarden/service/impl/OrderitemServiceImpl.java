package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.OrderitemMapper;
import com.djq.springGarden.entity.Orderitem;
import com.djq.springGarden.service.OrderitemService;


import javax.annotation.Resource;


/**
 * 订单详情-入住人信息Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class OrderitemServiceImpl implements OrderitemService {
    @Resource
    private OrderitemMapper orderitemMapper;

    /**
     * 条件查询订单详情-入住人信息列表
     *
     * @param orderitem  订单详情-入住人信息
     * @return 订单详情-入住人信息
     */
    @Override
    public List<Orderitem> select(Orderitem orderitem) {
        return orderitemMapper.select(orderitem);
    }

    /**
     * 条件查询订单详情-入住人信息单个
     *
     * @param orderitem 订单详情-入住人信息
     * @return 订单详情-入住人信息
     */
    @Override
    public Orderitem selectOne(Orderitem orderitem) {
        return orderitemMapper.selectOne(orderitem);
    }

    /**
     * 新增订单详情-入住人信息
     *
     * @param orderitem 订单详情-入住人信息
     * @return 结果
     */
    @Override
    public int insertOrderitem(Orderitem orderitem) {
        return orderitemMapper.insert(orderitem);
    }

    /**
     * 修改订单详情-入住人信息
     *
     * @param orderitem 订单详情-入住人信息
     * @return 结果
     */
    @Override
    public int updateOrderitem(Orderitem orderitem) {
        return orderitemMapper.updateByPrimaryKey(orderitem);
    }

    /**
     * 删除订单详情-入住人信息信息
     *
     * @param id 订单详情-入住人信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderitemById(Integer id) {

        Orderitem orderitem = new Orderitem();
        orderitem.setId(id);
        return orderitemMapper.delete(orderitem);
    }

}
