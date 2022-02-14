package com.srw.common.config;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/27 15:31
 */
@Configuration
public class LockConfiguration {

    @Value("${zookeeper.connect-string}")
    private String connectString;

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "redis-lock");
    }

    @Bean
    public CuratorFrameworkFactoryBean curatorFramework() {
        return new CuratorFrameworkFactoryBean(connectString);
    }

    @Bean
    public ZookeeperLockRegistry zookeeperLockRegistry(CuratorFramework client) {
        return new ZookeeperLockRegistry(client);
    }

}
