package com.djq.springGarden.vo;

import com.djq.springGarden.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author duanjiaqi
 * @date 2022/3/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends User {
    /** 手机尾号 */
    @ApiModelProperty("手机尾号" )
    private String phoneTail;
}
