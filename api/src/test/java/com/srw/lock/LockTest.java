package com.srw.lock;

import com.srw.test.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {
    @Autowired
    private TestService testService;

//    @Autowired
//    private TestServiceImpl testServiceImpl;

    @Test
    public void test() throws InterruptedException {
        testService.lockTest("hello");
    }

}
