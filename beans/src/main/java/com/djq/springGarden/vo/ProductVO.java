package com.djq.springGarden.vo;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.entity.Productimage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

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
public class ProductVO extends Product {


    /** 房间类型 */
    @ApiModelProperty("房间类型" )
    private String typeName;
    /** 房间类型 */
    @ApiModelProperty("图片列表" )
    private List<Productimage> imgs;



}
