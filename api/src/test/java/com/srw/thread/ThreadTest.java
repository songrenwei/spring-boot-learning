package com.srw.thread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/23 13:35
 */
@SpringBootTest
public class ThreadTest {

    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        Thread thread21 = new Thread(thread2);
        Thread thread22 = new Thread(thread2);
        thread21.start();
        thread22.start();
    }

    @Test
    public void test3() {
        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2);
        thread.start();
    }

    @Test
    public void test2() {
        Thread1 thread1 = new Thread1();
        Thread thread = new Thread(thread1);
        thread.start();
    }

    @Test
    public void test() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName()+" complete!");
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main complete!");
    }

}
