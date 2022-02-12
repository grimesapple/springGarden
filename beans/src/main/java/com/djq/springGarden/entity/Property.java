package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 房间属性字典对象 property
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("房间属性字典对象")
public class Property {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 房间类型id */
    @ApiModelProperty("房间类型id" )
    @Column(name = "c_id" )
    private Integer categoryId;
    /** 属性 */
    @ApiModelProperty("属性" )
    @Column(name = "name" )
    private String name;

}
