package com.djq.springGarden.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Product {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 标题
     */
    @Column(name = "sub_title")
    private String subTitle;

    /**
     * 原价格
     */
    @Column(name = "orignal_price")
    private BigDecimal orignalPrice;

    /**
     * 促销价格
     */
    @Column(name = "promote_price")
    private BigDecimal promotePrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 房间类型
     */
    @Column(name = "c_id")
    private Integer categoryId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取房间名称
     *
     * @return name - 房间名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置房间名称
     *
     * @param name 房间名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取标题
     *
     * @return sub_title - 标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置标题
     *
     * @param subTitle 标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    /**
     * 获取原价格
     *
     * @return orignal_price - 原价格
     */
    public BigDecimal getOrignalPrice() {
        return orignalPrice;
    }

    /**
     * 设置原价格
     *
     * @param orignalPrice 原价格
     */
    public void setOrignalPrice(BigDecimal orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    /**
     * 获取促销价格
     *
     * @return promote_price - 促销价格
     */
    public BigDecimal getPromotePrice() {
        return promotePrice;
    }

    /**
     * 设置促销价格
     *
     * @param promotePrice 促销价格
     */
    public void setPromotePrice(BigDecimal promotePrice) {
        this.promotePrice = promotePrice;
    }

    /**
     * 获取库存
     *
     * @return stock - 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存
     *
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取房间类型
     *
     * @return c_id - 房间类型
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置房间类型
     *
     * @param categoryId 房间类型
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}