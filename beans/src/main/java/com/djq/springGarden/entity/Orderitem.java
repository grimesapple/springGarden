package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 订单详情-入住人信息对象 orderitem
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("订单详情-入住人信息对象")
public class Orderitem {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 订单id */
    @ApiModelProperty("订单id" )
    @Column(name = "o_id" )
    private Integer orderId;
    /** 住户姓名 */
    @ApiModelProperty("住户姓名" )
    @Column(name = "receiver" )
    private String receiver;
    /** 身份证号码 */
    @ApiModelProperty("身份证号码" )
    @Column(name = "cardID" )
    private String cardid;
    /** 联系方式 */
    @ApiModelProperty("联系方式" )
    @Column(name = "mobile" )
    private String mobile;

}
