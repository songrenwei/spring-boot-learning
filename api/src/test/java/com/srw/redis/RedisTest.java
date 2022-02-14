package com.srw.redis;

import com.srw.common.utils.RedisUtils;
import com.srw.persistence.entity.User;
import com.srw.persistence.enums.SexEnum;
import com.srw.persistence.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/25 17:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("srw");
        user.setSex(SexEnum.FEMALE);
        redisUtils.setObject("srw1", user, 60, TimeUnit.MINUTES);
    }

    @Test
    public void test1() {
        User user = redisUtils.getObject("srw1");
        System.out.println(user);
    }

}
