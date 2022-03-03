package com.djq.springGarden.vo;

import com.djq.springGarden.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;

/**
 * 条件查询订单
 *
 * @author yerong
 * @date 2022/3/3 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderConditionVo extends Order {

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 客房名字
     */
    private String productName;

    /**
     * 入住时间的范围筛选:开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Data firstTime;
    /**
     * 入住时间的范围筛选:结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Data lastTime;
}
