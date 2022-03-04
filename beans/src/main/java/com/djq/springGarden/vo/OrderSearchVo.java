package com.djq.springGarden.vo;

import com.djq.springGarden.entity.OrderT;
import com.djq.springGarden.entity.Orderitem;
import lombok.Data;

import java.util.List;

/**
 * 订单交互实体类
 *
 * @author yerong
 * @date 2022/3/2 16:09
 */
@Data
public class OrderSearchVo {
    /**
     * 订单
     */
    private OrderT orderT;

    /**
     * 订单信息
     */
    private List<Orderitem> orderitems;
}
