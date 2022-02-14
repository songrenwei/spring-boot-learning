package com.srw.hook;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/8/3 16:47
 */

public class HookTest {

    public static void main(String[] args) throws InterruptedException {
        HookTest test = new HookTest();
        test.test1();
    }

    public void test1() throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("执行钩子线程")));

        System.out.println("start ......");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("end ......");
    }

}
