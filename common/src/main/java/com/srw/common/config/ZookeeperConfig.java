package com.srw.common.config;

import com.srw.common.component.CustomZkSerializer;
import com.srw.common.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.connect-string}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int timeout;

    @Bean
    public ZooKeeper zooKeeper() {
        ZooKeeper zooKeeper = null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(connectString, timeout, event -> {
                if(Watcher.Event.KeeperState.SyncConnected == event.getState()){
                    //如果收到了服务端的响应事件,连接成功
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            log.info("【初始化ZooKeeper连接状态....】 = {}", zooKeeper.getState());
        } catch (Exception e){
            log.error("初始化ZooKeeper连接异常....】 = {}", e);
        }
        return zooKeeper;
    }

    @Bean
    public ZkClient zkClient() {
        return new ZkClient(connectString, timeout, timeout, new CustomZkSerializer());
    }

    @Bean
    public ZkUtils zkUtils(ZooKeeper zooKeeper) {
        return new ZkUtils(zooKeeper);
    }

}
