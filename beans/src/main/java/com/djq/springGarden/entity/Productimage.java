package com.djq.springGarden.entity;

import javax.persistence.*;

public class Productimage {
    /**
     * 主键;
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 客房id;
     */
    @Column(name = "p_id")
    private Integer productId;

    /**
     * 图片链接;
     */
    private String url;

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
     * 获取客房id;
     *
     * @return p_id - 客房id;
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置客房id;
     *
     * @param productId 客房id;
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取图片链接;
     *
     * @return url - 图片链接;
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片链接;
     *
     * @param url 图片链接;
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}