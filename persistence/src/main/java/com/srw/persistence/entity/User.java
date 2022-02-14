package com.srw.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.srw.persistence.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/10/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_user", autoResultMap = true)
public class User extends BaseEntity {

    private String username;

    private String password;

    private Integer age;

    private SexEnum sex;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private OtherInfo otherInfo;

    @Data
    public static class OtherInfo {

        /**
         * 居住城市
         */
        private String city;

        /**
         * 爱好
         */
        private String hobby;

    }

}
