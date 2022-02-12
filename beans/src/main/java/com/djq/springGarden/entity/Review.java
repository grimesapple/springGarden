package com.djq.springGarden.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 评论对象 review
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("评论对象")
public class Review {

    /** 主键 */
    @ApiModelProperty("主键" )
    @Id
    @GeneratedValue(generator = "JDBC" )
    private Integer id;
    /** 评论内容 */
    @ApiModelProperty("评论内容" )
    @Column(name = "content" )
    private String content;
    /** 商家回复 */
    @ApiModelProperty("商家回复" )
    @Column(name = "reply" )
    private String reply;
    /** 用户id */
    @ApiModelProperty("用户id" )
    @Column(name = "u_id" )
    private Integer userId;
    /** 评论的产品 */
    @ApiModelProperty("评论的产品" )
    @Column(name = "p_id" )
    private Integer productId;

}
