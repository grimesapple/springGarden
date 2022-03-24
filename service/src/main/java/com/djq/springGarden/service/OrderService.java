package com.djq.springGarden.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.db.sql.Order;
import com.djq.springGarden.entity.OrderT;
import com.djq.springGarden.vo.OrderSearchVo;
import com.djq.springGarden.vo.OrderTVo;
import com.djq.springGarden.vo.ResultVO;

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
     * @param orderTVo 查询条件
     * @return 订单集合
     */
    List<Map<String, Object>> select(OrderTVo orderTVo);

    /**
     * 条件查询订单列表
     *
     * @param order 查询条件
     * @return 订单集合
     */
    List<OrderT> search(OrderT order);


    /**
     * 条件查询订单:查询单个
     *
     * @param  orderT 订单
     * @return 订单
     */
     OrderT selectOne(OrderT orderT);


    /**
     * 新增订单
     *
     * @param orderSearchVo 订单信息
     * @return 结果
     */
    int insertOrder(OrderSearchVo orderSearchVo);

    /**
     * 办理入住
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    int intoHouse(OrderSearchVo orderSearchVo);

    /**
     * 办理退房
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    int stayOUt(OrderSearchVo orderSearchVo);

    /**
     * 办理续住
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    int continueIn(OrderSearchVo orderSearchVo);


    /**
     * 取消预订
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    int cancelPre(OrderSearchVo orderSearchVo);



    /**
     * 修改订单
     *
     * @param orderT 订单
     * @return 结果
     */
    int updateOrder(OrderT orderT);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    int deleteOrderById(Integer id);

    /**
     * 判断当前选定时间是否可以入住是可预定
     *
     * @param endTime 预定或者入住的结束时间
     * @param startTime 预定或者入住的开始时间
     * @param productId 房间id
     * @return true:无冲突，可以预定.false：有冲突。
     */
    boolean check(Date startTime, Date endTime, Integer productId);

}
