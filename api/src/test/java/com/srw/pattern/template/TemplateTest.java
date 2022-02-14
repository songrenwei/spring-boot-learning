package com.srw.pattern.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 模板方法模式
 * @Author: songrenwei
 * @Date: 2022/1/11 9:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {

    @Autowired
    private Banking1 banking1;
    @Autowired
    private Banking2 banking2;

    @Test
    public void test() {
        banking1.processCustomer(1);
        banking2.processCustomer(2);
    }

    @Test
    public void test2() {
        new OnlineBankingLambda().processCustomer(1, id -> System.out.println("减少广告...for lambda" + id));
    }

}
