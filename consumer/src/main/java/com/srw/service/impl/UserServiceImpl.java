package com.srw.service.impl;

import com.srw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/31 17:33
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void execute(Long userId) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("userId:{}已执行", userId);
    }

}
