package com.djq.springGarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 订单对象 order
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("订单对象")
public class OrderT {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 客房id */
    @ApiModelProperty("客房id" )
    @Column(name = "p_id" )
    private Integer productId;
    /** 用户账号 */
    @ApiModelProperty("用户账号" )
    @Column(name = "u_id" )
    private Integer userId;
    /** 订单编码 */
    @ApiModelProperty("订单编码" )
    @Column(name = "order_code" )
    private String orderCode;
    /** 入住人数 */
    @ApiModelProperty("入住人数" )
    @Column(name = "number" )
    private Integer number;
    /** 入住信息id */
    @ApiModelProperty("入住信息id" )
    @Column(name = "r_id" )
    private Integer residentId;
    /** 用户备注 */
    @ApiModelProperty("用户备注" )
    @Column(name = "user_message" )
    private String userMessage;
    /** 入住时间 */
    @ApiModelProperty("入住时间" )
    @Column(name = "start_time" )
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date startTime;
    /** 离开时间 */
    @ApiModelProperty("离开时间" )
    @Column(name = "end_time" )
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date endTime;
    /** 支付时间 */
    @ApiModelProperty("支付时间" )
    @Column(name = "pay_date" )
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date payDate;
    /** 提交时间 */
    @ApiModelProperty("提交时间" )
    @Column(name = "delivery_date" )
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date deliveryDate;
    /** 提交时间 */
    @ApiModelProperty("提交时间" )
    @Column(name = "creat_time" )
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date createTime;
    /** 批准时间 */
    @ApiModelProperty("批准时间" )
    @Column(name = "confirm_date" )
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date confirmDate;
    /** 状态：0离店、1预定、2已入住 */
    @ApiModelProperty("状态：0离店、1预定、2已入住" )
    @Column(name = "status" )
    private Integer status;
    /** 价格总数 */
    @ApiModelProperty("价格总数" )
    @Column(name = "total" )
    private BigDecimal total;

}
