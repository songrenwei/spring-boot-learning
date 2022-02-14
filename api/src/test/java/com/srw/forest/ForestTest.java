package com.srw.forest;

import com.srw.client.MyClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description: update
 * @Author: renwei.song
 * @Date: 2021/3/12 14:38
 */
@SpringBootTest
public class ForestTest {

    @Resource
    private MyClient myClient;

    @Test
    public void test() {
        myClient.getLocation("124.730329","31.463683");
    }

}
