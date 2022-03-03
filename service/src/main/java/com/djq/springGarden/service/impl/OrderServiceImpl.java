package com.djq.springGarden.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.djq.springGarden.entity.Orderitem;
import com.djq.springGarden.mapper.OrderitemMapper;
import com.djq.springGarden.util.RandomUtils;
import com.djq.springGarden.vo.OrderSearchVo;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.OrderMapper;
import com.djq.springGarden.entity.Order;
import com.djq.springGarden.service.OrderService;


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

    @Resource
    private OrderitemMapper orderitemMapper;

    /**
     * 条件查询订单列表
     *
     * @param order 订单
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
     * 新增订单；（预定）
     *
     * @param orderSearchVo 订单
     * @return 结果
     */
    @Override
    public int insertOrder(OrderSearchVo orderSearchVo) {
        //插入订单
        Order order = orderSearchVo.getOrder();
        //校验对应房间是否已经被预定
        if (!check(order)) {
            //有冲突，不能通过预定
            return 0;
        }
        //初始化订单编号
        String businessNum = RandomUtils.businessNum();
        order.setOrderCode(businessNum);

        //订单插入
        int orderNum = orderMapper.insert(order);

        //设置入住人信息
        int itemNum = insertBatchOrderItem(orderSearchVo.getOrderitems());
        if (orderNum == 0) {
            return 0;
        }
        if (itemNum == 0) {
            //表示没有出入住户信息
            return 1;
        }
        return 2;
    }

    /**
     * 办理入住
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    public int intoHouse(OrderSearchVo orderSearchVo) {
        //对应订单:设置对应订单状态：
        Order order = orderSearchVo.getOrder();
        order.setStatus(2);
        int orderNum = orderMapper.updateByPrimaryKey(order);

        //办理入住：核对结果，添加住户信息,点击确认，办理入住,
        int orderItemNum = insertBatchOrderItem(orderSearchVo.getOrderitems());
        if (orderNum == 0) {
            return 0;
        }
        if (orderItemNum == 0) {
            return 1;
        }
        return 2;
    }


    /**
     * 新增订单
     *
     * @return 返回结果
     */
    public int addOrder(Order order) {
        //新增订单
        return orderMapper.insert(order);
    }


    /**
     * 批量新增住户信息。
     *
     * @param orderitems 住户信息列表
     * @return 插入行数
     */
    public int insertBatchOrderItem(List<Orderitem> orderitems) {
        int orderItemNum = 0;
        if (orderitems != null && !orderitems.isEmpty() && orderitems.get(0) != null) {
            for (Orderitem orderitem : orderitems) {
                orderItemNum += orderitemMapper.insert(orderitem);
            }
        }
        return orderItemNum;
    }


    /**
     * 判断当前选定时间是否可以入住是可预定
     *
     * @return true:无冲突，可以预定.false：有冲突。
     */
    public boolean check(Order order) {
        //预定或者入住的开始时间
        Date startTime = order.getStartTime();
        //预定或者入住的结束时间
        Date endTime = order.getEndTime();

        //查询对应的房间列表：预定
        Order orderForSearch = new Order();
        orderForSearch.setProductId(order.getProductId());
        orderForSearch.setStatus(1);
        List<Order> orderList = select(order);
        //查询对应的房间列表：已入住
        orderForSearch.setStatus(2);
        List<Order> list2 = select(order);
        //两种订单合并
        orderList.addAll(list2);
        //订单需要根据时间进行排序：前一个订单和后一个订单的时间不会有交叉，最多是相等。(升序)
        orderList.sort(Comparator.comparing(Order::getStartTime));

        //遍历订单在选定时间中是否有冲突
        boolean flag = false;
        for (int i = orderList.size() - 1; i > 0; i++) {
            //当前订单
            Order firstOrder = orderList.get(i);
            //下一时间段订单
            Order secondOrder = orderList.get(--i);

            //判断选定时间是否在这两个订单之间
            if (firstOrder.getEndTime().before(startTime) && secondOrder.getStartTime().after(endTime)) {
                //订单不冲突
                flag = true;
                break;
            }
        }
        return flag;
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
