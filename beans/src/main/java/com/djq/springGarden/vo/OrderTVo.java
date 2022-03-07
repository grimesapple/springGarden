package com.djq.springGarden.vo;

import com.djq.springGarden.entity.OrderT;
import com.djq.springGarden.entity.Orderitem;
import com.djq.springGarden.entity.Product;
import com.djq.springGarden.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单完整信息格式
 *
 * @author yerong
 * @date 2022/3/3 15:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTVo extends OrderT {
    /**
     * 客房信息
     */
    private Product product;

    /**
     * 用户账号信息
     */
    private User user;

    /**
     * 入住信息
     */
    private List<Orderitem>  orderitems;



}