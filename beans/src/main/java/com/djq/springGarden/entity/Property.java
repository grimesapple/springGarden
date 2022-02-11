package com.djq.springGarden.entity;

import javax.persistence.*;

public class Property {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 房间类型id
     */
    @Column(name = "c_id")
    private Integer categoryId;

    /**
     * 属性
     */
    private String name;

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
     * 获取房间类型id
     *
     * @return c_id - 房间类型id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置房间类型id
     *
     * @param categoryId 房间类型id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取属性
     *
     * @return name - 属性
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性
     *
     * @param name 属性
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}