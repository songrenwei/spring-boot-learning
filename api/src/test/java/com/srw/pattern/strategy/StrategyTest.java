package com.srw.pattern.strategy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description: 策略模式
 * @Author: renwei.song
 * @Date: 2020/12/31 17:21
 */
@SpringBootTest
public class StrategyTest {

    @Autowired
    private OrderService orderService;

    /**
     * 一般实现
     */
    @Test
    public void test() {
        orderService.handle(new Order("pc"));

    }

    /**
     * 方法引用（意义不大，主要是练习方法引用）
     */
    @Test
    public void test2() {
        orderService.handle(new Order("pc"), orderService::handle);
    }

    /**
     * lambda表达式
     */
    @Test
    public void test3() {
        OrderHandler orderHandler = order -> System.out.println("来自： "+order.getSource()+" 订单");
        orderHandler.handle(new Order("pc"));
    }

}
