package com.djq.springGarden.entity;

import javax.persistence.*;

public class Propertyvalue {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 房间id
     */
    @Column(name = "p_id")
    private Integer productId;

    /**
     * 房间属性id
     */
    @Column(name = "pt_id")
    private Integer propertyId;

    /**
     * 对应的属性情况：1正常、0损坏
     */
    private Integer status;

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
     * 获取房间id
     *
     * @return p_id - 房间id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置房间id
     *
     * @param productId 房间id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取房间属性id
     *
     * @return pt_id - 房间属性id
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 设置房间属性id
     *
     * @param propertyId 房间属性id
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 获取对应的属性情况：1正常、0损坏
     *
     * @return status - 对应的属性情况：1正常、0损坏
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置对应的属性情况：1正常、0损坏
     *
     * @param status 对应的属性情况：1正常、0损坏
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}