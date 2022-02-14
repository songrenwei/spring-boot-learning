package com.srw.pattern.template;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2022/1/11 9:47
 */
abstract class OnlineBanking {

    public void processCustomer(int id){
        makeCustomerHappy(id);
    }

    abstract void makeCustomerHappy(int id);

}
