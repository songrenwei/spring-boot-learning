package com.srw.pattern.template;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2022/1/11 9:48
 */
@Service
public class Banking1 extends OnlineBanking {

    @Override
    void makeCustomerHappy(int id) {
        System.out.println("发放红利...for customer" + id);
    }

}
