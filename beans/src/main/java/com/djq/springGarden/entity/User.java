package com.djq.springGarden.entity;

import javax.persistence.*;

public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户账号
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 权限表id
     */
    @Column(name = "r_id")
    private Integer roleId;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号码
     */
    private String telphone;

    /**
     * 邮箱
     */
    private String eamil;

    /**
     * 身份证号码
     */
    @Column(name = "cardID")
    private String cardid;

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
     * 获取用户账号
     *
     * @return name - 用户账号
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户账号
     *
     * @param name 用户账号
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取权限表id
     *
     * @return r_id - 权限表id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置权限表id
     *
     * @param roleId 权限表id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取真实姓名
     *
     * @return realname - 真实姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置真实姓名
     *
     * @param realname 真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取手机号码
     *
     * @return telphone - 手机号码
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 设置手机号码
     *
     * @param telphone 手机号码
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * 获取邮箱
     *
     * @return eamil - 邮箱
     */
    public String getEamil() {
        return eamil;
    }

    /**
     * 设置邮箱
     *
     * @param eamil 邮箱
     */
    public void setEamil(String eamil) {
        this.eamil = eamil == null ? null : eamil.trim();
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
}