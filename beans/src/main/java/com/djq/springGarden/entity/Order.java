package com.djq.springGarden.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Order {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 客房id
     */
    @Column(name = "p_id")
    private Integer productId;

    /**
     * 用户账号
     */
    @Column(name = "u_id")
    private Integer userId;

    /**
     * 订单编码
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 入住人数
     */
    private Integer number;

    /**
     * 入住信息id
     */
    @Column(name = "r_id")
    private String residentId;

    /**
     * 用户备注
     */
    @Column(name = "user_message")
    private String userMessage;

    /**
     * 入住时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 离开时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_date")
    private Date payDate;

    /**
     * 提交时间
     */
    @Column(name = "delivery_date")
    private Date deliveryDate;

    /**
     * 批准时间
     */
    @Column(name = "confirm_date")
    private Date confirmDate;

    /**
     * 状态：0离店、1预定、2已入住
     */
    private Integer status;

    /**
     * 价格总数
     */
    private BigDecimal total;

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
     * 获取客房id
     *
     * @return p_id - 客房id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置客房id
     *
     * @param productId 客房id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取用户账号
     *
     * @return u_id - 用户账号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户账号
     *
     * @param userId 用户账号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取订单编码
     *
     * @return order_code - 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编码
     *
     * @param orderCode 订单编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 获取入住人数
     *
     * @return number - 入住人数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置入住人数
     *
     * @param number 入住人数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取入住信息id
     *
     * @return r_id - 入住信息id
     */
    public String getResidentId() {
        return residentId;
    }

    /**
     * 设置入住信息id
     *
     * @param residentId 入住信息id
     */
    public void setResidentId(String residentId) {
        this.residentId = residentId == null ? null : residentId.trim();
    }

    /**
     * 获取用户备注
     *
     * @return user_message - 用户备注
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * 设置用户备注
     *
     * @param userMessage 用户备注
     */
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage == null ? null : userMessage.trim();
    }

    /**
     * 获取入住时间
     *
     * @return start_time - 入住时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置入住时间
     *
     * @param startTime 入住时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取离开时间
     *
     * @return end_time - 离开时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置离开时间
     *
     * @param endTime 离开时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取支付时间
     *
     * @return pay_date - 支付时间
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置支付时间
     *
     * @param payDate 支付时间
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取提交时间
     *
     * @return delivery_date - 提交时间
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 设置提交时间
     *
     * @param deliveryDate 提交时间
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 获取批准时间
     *
     * @return confirm_date - 批准时间
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置批准时间
     *
     * @param confirmDate 批准时间
     */
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * 获取状态：0离店、1预定、2已入住
     *
     * @return status - 状态：0离店、1预定、2已入住
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0离店、1预定、2已入住
     *
     * @param status 状态：0离店、1预定、2已入住
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取价格总数
     *
     * @return total - 价格总数
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置价格总数
     *
     * @param total 价格总数
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}