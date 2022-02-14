package com.srw.common.component;

import com.srw.common.utils.SpringContextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@RequiredArgsConstructor
public class ZkLock {
    /*
     * 利用临时顺序节点来实现分布式锁
     * 获取锁：取排队号（创建自己的临时顺序节点），然后判断自己是否是最小号，如是，则获得锁；不是，则注册前一节点的watcher,阻塞等待
     * 释放锁：删除自己创建的临时顺序节点
     * 避免惊群效应
     */

    private String lockNode;
    private ZkClient zkClient;

    /**
     * 初始化
     *
     * @param lockNode
     */
    public ZkLock(String lockNode) {
        if(lockNode == null || lockNode.trim().equals("")) {
            throw new IllegalArgumentException("path不能为空字符串");
        }
        this.lockNode = lockNode;
        this.zkClient = SpringContextUtils.getBean("zkClient");
        if (!this.zkClient.exists(lockNode)) {
            try {
                this.zkClient.createPersistent(lockNode, true);
            } catch (ZkNodeExistsException e) {

            }
        }
    }

    public String getLock() {
        try {
            // 1。在Zookeeper指定节点下创建临时顺序节点
            String childNode = "/task_";
            String lockName = zkClient.createEphemeralSequential(lockNode + childNode, "");
            // 尝试获取锁
            acquireLock(lockName);
            return lockName;
        } catch(Exception e) {
            log.error("加锁异常: {}", e);
        }

        return null;
    }

    /**
     * 获取锁
     * @throws InterruptedException
     */
    public void acquireLock(String lockName) throws InterruptedException {
        // 2.获取lock节点下的所有子节点
        List<String> childrenList = zkClient.getChildren(lockNode);
        // 3.对子节点进行排序，获取最小值
        childrenList.sort(Comparator.comparingInt(o -> Integer.parseInt(o.split("_")[1])));
        // 4.判断当前创建的节点是否在第一位
        String currentChildNode = lockName.split("/")[lockName.split("/").length - 1];
        int currentIndex = childrenList.indexOf(currentChildNode);
        if(currentIndex < 0) {
            // 不存在该节点
            throw new ZkNodeExistsException("不存在的节点：" + lockName);
        } else if (currentIndex == 0) {
            // 获取到锁
            System.out.println("...... 获取到锁: "+ lockName +" ......");
        } else {
            // 未获取到锁，阻塞
            System.out.println("...... 未获取到锁，阻塞等待 ......");
            // 5.如果未获取得到锁，监听当前创建的节点前一位的节点
            final CountDownLatch latch = new CountDownLatch(1);
            IZkDataListener listener = new IZkDataListener() {
                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    // 6.前一个节点被删除,当不保证轮到自己
                    System.out.println("...... 前一个节点已释放锁 ......");
                    acquireLock(lockName);
                    latch.countDown();
                }

                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    // 不用理会
                }
            };
            try {
                // 监听前一个节点
                zkClient.subscribeDataChanges(lockNode + "/" + childrenList.get(currentIndex - 1), listener);
                latch.await();
            } finally {
                zkClient.unsubscribeDataChanges(lockNode + "/" + childrenList.get(currentIndex - 1), listener);
            }
        }
    }

    /**
     * 释放锁（删除节点）
     *
     * @param lockName
     */
    public void releaseLock(String lockName) {
        zkClient.delete(lockName);
    }

}
