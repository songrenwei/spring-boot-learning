package com.srw.persistence.mongodb.repository;

import com.srw.persistence.mongodb.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/12/14/18:12
 */
public interface UserInfoRepository extends MongoRepository<UserInfo, Long> {

}
