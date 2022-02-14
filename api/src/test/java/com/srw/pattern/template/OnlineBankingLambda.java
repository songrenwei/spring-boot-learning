package com.srw.pattern.template;

import java.util.function.Consumer;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2022/1/11 9:47
 */
public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Integer> consumer){
        consumer.accept(id);
    }

}
