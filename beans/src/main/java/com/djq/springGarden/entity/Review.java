package com.djq.springGarden.entity;

import java.util.Date;
import javax.persistence.*;

public class Review {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 商家回复
     */
    private String reply;

    /**
     * 用户id
     */
    @Column(name = "u_id")
    private Integer userId;

    /**
     * 评论的产品
     */
    @Column(name = "p_id")
    private Integer productId;

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
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取商家回复
     *
     * @return reply - 商家回复
     */
    public String getReply() {
        return reply;
    }

    /**
     * 设置商家回复
     *
     * @param reply 商家回复
     */
    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    /**
     * 获取用户id
     *
     * @return u_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取评论的产品
     *
     * @return p_id - 评论的产品
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置评论的产品
     *
     * @param productId 评论的产品
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
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