package com.srw.common.component;

import com.srw.common.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class ZkConfigCenter {

    private final ZkClient zkClient;
    private final RedisUtils redisUtils;
    private String configPath = "/configNode";
    private String configValue;

    @PostConstruct
    private void init() {
        if (!this.zkClient.exists(configPath)) {
            try {
                this.zkClient.createPersistent(configPath, true);
            } catch (ZkNodeExistsException ignored) {

            }
        }
        // 获取配置节点
        configValue = zkClient.readData(configPath);
        log.info("初始化配置信息: configNode = {}", configValue);

        // 监听配置节点
        watchConfig();
    }

    private void watchConfig() {
        zkClient.subscribeDataChanges(configPath, new IZkDataListener() {
            @Override
            public void handleDataDeleted(String dataPath) {
                if(dataPath.equals(configPath)) {
                    log.info("配置节点：{} 被删除了！", dataPath);
                }
            }

            @Override
            public void handleDataChange(String dataPath, Object data) {
                if(dataPath.equals(configPath)) {
                    log.info("配置节点：{}, 数据：{} - 更新", dataPath, data);
                    configValue = (String) data;
                    redisUtils.setObject("configNode", configValue);
                }
            }
        });
    }

}
