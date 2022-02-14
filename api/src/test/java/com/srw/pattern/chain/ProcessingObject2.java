package com.srw.pattern.chain;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/12 15:04
 */
public class ProcessingObject2 extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text + "->" + getClass().getSimpleName();
    }

    @Override
    protected void handleWork2() {
        System.out.println("ProcessingObject2");
    }

}
