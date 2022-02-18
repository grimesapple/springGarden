package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

/**
 * 客房;商品信息相关：分类，商品图片，商品规格，商品参数对象 product
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("客房;商品信息相关：分类，商品图片，商品规格，商品参数对象")
public class Product {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 房间名称 */
    @ApiModelProperty("房间名称" )
    @Column(name = "name" )
    private String name;
    /** 标题 */
    @ApiModelProperty("标题" )
    @Column(name = "sub_title" )
    private String subTitle;
    /** 原价格 */
    @ApiModelProperty("原价格" )
    @Column(name = "orignal_price" )
    private BigDecimal orignalPrice;
    /** 促销价格 */
    @ApiModelProperty("促销价格" )
    @Column(name = "promote_price" )
    private BigDecimal promotePrice;
    /** 库存 */
    @ApiModelProperty("库存" )
    @Column(name = "stock" )
    private Integer stock;
    /** 房间类型 */
    @ApiModelProperty("房间类型" )
    @Column(name = "c_id" )
    private Integer categoryId;

    private List<String> fileList;

}
