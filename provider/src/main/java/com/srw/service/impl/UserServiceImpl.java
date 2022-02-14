package com.srw.service.impl;

import com.srw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/31 17:34
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<Long> query() {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add((long) i);
        }
        return list;
    }

}
