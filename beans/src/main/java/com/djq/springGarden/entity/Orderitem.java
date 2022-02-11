package com.djq.springGarden.entity;

import javax.persistence.*;

public class Orderitem {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "o_id")
    private Integer orderId;

    /**
     * 住户姓名
     */
    private String receiver;

    /**
     * 身份证号码
     */
    @Column(name = "cardID")
    private String cardid;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return o_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取住户姓名
     *
     * @return receiver - 住户姓名
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置住户姓名
     *
     * @param receiver 住户姓名
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    /**
     * 获取身份证号码
     *
     * @return cardID - 身份证号码
     */
    public String getCardid() {
        return cardid;
    }

    /**
     * 设置身份证号码
     *
     * @param cardid 身份证号码
     */
    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    /**
     * 获取联系方式
     *
     * @return mobile - 联系方式
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置联系方式
     *
     * @param mobile 联系方式
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}