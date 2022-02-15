package com.srw.reflect;

import lombok.Data;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2022/2/15 14:10
 */
@Data
public class TargetObject {

    private String value;

    public TargetObject() {
        value = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }

}
