package com.djq.springGarden.vo;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.entity.Property;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
     * 开始时间
     */
    @ApiModelProperty("开始时间" )
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间" )
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date endTime;

}
