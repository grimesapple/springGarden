package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 客房图片对象 productimage
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("客房图片对象")
public class Productimage {

    /** 主键; */
    @ApiModelProperty("主键;" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 客房id; */
    @ApiModelProperty("客房id;" )
    @Column(name = "p_id" )
    private Integer productId;
    /** 图片链接; */
    @ApiModelProperty("图片链接;" )
    @Column(name = "url" )
    private String url;

}
