package com.srw.mock;

import lombok.Data;

/**
 * @Description:
 * @Author: admin
 * @Date: 2020/12/25 16:31
 */
@Data
public class UserInfo {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机
     */
    private String cellPhone;
    /**
     * 大学
     */
    private String universityName;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String street;

}
