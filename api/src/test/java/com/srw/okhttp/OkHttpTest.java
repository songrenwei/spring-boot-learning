package com.srw.okhttp;

import com.srw.common.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/8/4 9:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OkHttpTest {

    @Autowired
    private HttpUtils httpUtils;

    @Test
    public void test() {
        String res = httpUtils.doGet("http://106.14.119.197:8081/user/findList", 1);
        System.out.println(res);
    }

    @Test
    public void test2() {
        String res = httpUtils.request("http://106.14.119.197/image/baby.jpg");
        System.out.println(res);
    }

}
