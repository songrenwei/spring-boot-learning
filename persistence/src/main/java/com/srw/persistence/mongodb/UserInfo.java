package com.srw.persistence.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/12/14/18:06
 */
@Data
@Document
public class UserInfo {

    @Id
    private Long id;

    private String name;

    private String password;

    protected Date createTime;

    protected Date updateTime;

}
