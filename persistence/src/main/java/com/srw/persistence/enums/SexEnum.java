package com.srw.persistence.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 使用注解@EnumValue
 * @Author: renwei.song
 * @Date: 2021/5/10 16:43
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    FEMALE("female", "女性"),
    MALE("male", "男性");

    @EnumValue // 标记数据库存的值是code
    private final String code;

    private final String desc;

}
