package com.srw.pattern.chain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Description: 责任链模式
 * @Author: renwei.song
 * @Date: 2021/4/12 15:06
 */
@SpringBootTest
public class ChainTest {

    @Test
    void test() {
        ProcessingObject<String> p1 = new ProcessingObject1();
        ProcessingObject<String> p2 = new ProcessingObject2();
        ProcessingObject<String> p3 = new ProcessingObject3();
        p1.setSuccessor(p2).setSuccessor(p3);
        String result = p1.handle("begin");
        System.out.println(result);
        System.out.println("-----------------------------------");
        p1.handle2();
    }

    @Test
    void test2() {
        UnaryOperator<String> p1 = (String text) -> text + "->" + "p1";
        UnaryOperator<String> p2 = (String text) -> text + "->" + "p2";
        UnaryOperator<String> p3 = (String text) -> text + "->" + "p3" + "->" + "end";
        Function<String, String> p = p1.andThen(p2).andThen(p3);
        String r = p.apply("begin");
        System.out.println(r);
    }

}
