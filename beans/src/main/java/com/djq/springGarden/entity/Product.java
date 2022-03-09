package com.djq.springGarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class Product implements Serializable {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 房间名称 */
    @ApiModelProperty("房间号" )
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
    @ApiModelProperty("床的数量" )
    @Column(name = "bed_number" )
    private Integer bedNumber;
    /** 最大入住人数 */
    @ApiModelProperty("最大入住人数" )
    @Column(name = "people_num" )
    private Integer people;
    /** 房间的类型 */
    @ApiModelProperty("房间类型" )
    @Column(name = "c_id" )
    private Integer categoryId;
    /** 房间描述 */
    @ApiModelProperty("房间描述" )
    @Column(name = "content" )
    private String content;
    @ApiModelProperty("创建时间" )
    @Column(name = "create_time" )
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    /**图片列表*/
    private List<String> imgList;
    /**房间的属性列表*/
    private List<Integer> properties;


}
