package com.srw.pattern.chain;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/12 15:47
 */
public class ProcessingObject3 extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text + "->" + getClass().getSimpleName() + "->" + "end";
    }

    @Override
    protected void handleWork2() {
        System.out.println("ProcessingObject3");
    }

}
