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
 * 房间属性情况对象 propertyvalue
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("房间属性情况对象")
public class Propertyvalue {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 房间id */
    @ApiModelProperty("房间id" )
    @Column(name = "p_id" )
    private Integer productId;
    /** 房间属性id */
    @ApiModelProperty("房间属性id" )
    @Column(name = "pt_id" )
    private Integer ptId;
    /** 对应的属性情况：1正常、0损坏 */
    @ApiModelProperty("对应的属性情况：1正常、0损坏" )
    @Column(name = "status" )
    private Integer status;

}
