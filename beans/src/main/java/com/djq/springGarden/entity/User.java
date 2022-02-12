package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 用户对象 user
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户对象")
public class User {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 用户账号 */
    @ApiModelProperty("用户账号" )
    @Column(name = "name" )
    private String name;
    /** 用户密码 */
    @ApiModelProperty("用户密码" )
    @Column(name = "password" )
    private String password;
    /** 权限表id */
    @ApiModelProperty("权限表id" )
    @Column(name = "r_id" )
    private Integer residentId;
    /** 真实姓名 */
    @ApiModelProperty("真实姓名" )
    @Column(name = "realname" )
    private String realname;
    /** 手机号码 */
    @ApiModelProperty("手机号码" )
    @Column(name = "telphone" )
    private String telphone;
    /** 邮箱 */
    @ApiModelProperty("邮箱" )
    @Column(name = "eamil" )
    private String eamil;
    /** 身份证号码 */
    @ApiModelProperty("身份证号码" )
    @Column(name = "cardID" )
    private String cardid;

}
