package com.djq.springGarden.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

/**
 * 随机数生成器
 *
 * @author yerong
 * @date 2022/3/3 14:45
 */
public class RandomUtils {

    /**
     * 流水号生成器
     */
    public static String businessNum() {
        String format = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);
        return format+numbers;
    }
}
