package com.srw.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/12/29 14:23
 */
public class Thread2 implements Runnable {

    private int num = 100;

    @Override
    public void run() {
        decrease();
    }

    private void decrease() {
        while(num > 0) {
            synchronized (this) {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() +":"+ num--);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
