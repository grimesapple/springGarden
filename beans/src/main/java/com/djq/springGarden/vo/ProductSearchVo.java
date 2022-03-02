package com.djq.springGarden.vo;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.entity.Property;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 条件查询接口:人数，价格，时间，各个属性(床数)
 *
 * @author yerong
 * @date 2022/3/2 14:47
 */
@Data
public class ProductSearchVo extends Product {
    /**
     * 价格区间：开始价格
     */
    @ApiModelProperty("起始价格" )
    private BigDecimal startPrice;
    /**
     * 价格区间：结束价格
     */
    @ApiModelProperty("结束价格" )
    private BigDecimal endPrice;


    /**
     * 人数
     */
    @ApiModelProperty("人数" )
    private Integer people;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间" )
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间" )
    private Date endTime;

    /**
     * 属性
     */
    private Property property;



}
