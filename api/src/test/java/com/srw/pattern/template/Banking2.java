package com.srw.pattern.template;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2022/1/11 9:49
 */
@Service
public class Banking2 extends OnlineBanking {

    @Override
    void makeCustomerHappy(int id) {
        System.out.println("减少广告...for customer" + id);
    }

}
