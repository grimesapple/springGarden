package com.djq.springGarden.vo;

import com.djq.springGarden.entity.OrderT;
import com.djq.springGarden.entity.Orderitem;
import com.djq.springGarden.entity.Product;
import com.djq.springGarden.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * 订单完整信息格式
 *
 * @author yerong
 * @date 2022/3/3 15:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTVo extends OrderT {

    /** 入住时间筛选-开始 */
    @ApiModelProperty("入住时间筛选-开始" )
    private Date startTime;
    /** 入住时间筛选-结束 */
    @ApiModelProperty("入住时间筛选-结束" )
    private Date entTime;

    /** 手机尾号 */
    @ApiModelProperty("手机尾号" )
    private String phone ;

    /** 账号名 */
    @ApiModelProperty("账号名" )
    private String username ;


    /** 支付时间 */
    @ApiModelProperty("支付时间-开始" )
    private Date startTimePay;

    /** 支付时间 */
    @ApiModelProperty("支付时间-结束" )
    private Date endTimePay;

}
