package com.djq.springGarden.entity;

import javax.persistence.*;

public class Category {
    /**
     * 主键;
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 类型
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 获取主键;
     *
     * @return id - 主键;
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键;
     *
     * @param id 主键;
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型
     *
     * @return type_name - 类型
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型
     *
     * @param typeName 类型
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}