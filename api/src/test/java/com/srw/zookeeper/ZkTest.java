package com.srw.zookeeper;

import com.srw.common.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkTest {

    @Autowired
    private ZkUtils zkUtils;
    @Autowired
    private ZkClient zkClient;

    @Test
    public void test() {
        zkUtils.create("/test", "test01");

        String config = zkClient.readData("/test");
        System.out.println(config);
    }

    @Test
    public void test2() {
        String zkData = zkUtils.getData("/config", event -> {
            log.info("【Watcher监听事件】={}", event.getState());
            log.info("【监听路径为】={}", event.getPath());
            log.info("【监听的类型为】={}", event.getType()); //  三种监听类型： 创建，删除，更新
        });

        log.info("zkData = {}", zkData);
    }

    @Test
    public void test3() {
        zkUtils.update("/config", "test02");
    }

    @Test
    public void test4() {
        zkUtils.delete("/test");
    }

}
