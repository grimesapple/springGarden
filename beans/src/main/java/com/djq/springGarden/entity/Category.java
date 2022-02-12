package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 房间类型对象 category
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("房间类型对象")
public class Category {

    /** 主键; */
    @ApiModelProperty("主键;" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 类型 */
    @ApiModelProperty("类型" )
    @Column(name = "type_name" )
    private String typeName;

}
