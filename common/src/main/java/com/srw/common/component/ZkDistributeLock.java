package com.srw.common.component;

import com.srw.common.utils.SpringContextUtils;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;

public class ZkDistributeLock implements Lock {
    /*
     * 利用临时节点来实现分布式锁
     * 获取锁：争抢创建节点，谁先创建成功谁就拥有了锁，其余都监听这个节点，阻塞等待。一旦锁释放，再去争抢创建节点。。。
     * 释放锁：删除自己创建的临时节点
     * 产生惊群效应
     */

    // 我们需要一个锁的目录
    private String lockPath;
    // 我们需要一个客户端
    private ZkClient client;

    public ZkDistributeLock(String lockPath) {
        if(lockPath == null || lockPath.trim().equals("")) {
            throw new IllegalArgumentException("path不能为空字符串");
        }
        this.lockPath = lockPath;
        this.client = SpringContextUtils.getBean("zkClient");
    }

    @Override
    public boolean tryLock() { // 不会阻塞
        // 创建节点
        try {
            client.createEphemeral(lockPath);
        } catch (ZkNodeExistsException e) {
            return false;
        }
        System.out.println("...... 加锁成功 ......");
        return true;
    }

    @Override
    public void unlock() {
        client.delete(lockPath);
    }


    @Override
    public void lock() {
        // 如果获取不到锁
        if (!tryLock()) {

            // 没获得锁，阻塞监听节点
            waitForLock();

            // 从等待中唤醒，再次尝试获得锁
            lock();
        }

    }

    private void waitForLock() {
        final CountDownLatch cdl = new CountDownLatch(1);

        // 创建监听器，监听节点
        IZkDataListener listener = new IZkDataListener() {

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("...... 锁已被释放 ......");
                // 唤醒阻塞线程
                cdl.countDown();
            }

            @Override
            public void handleDataChange(String dataPath, Object data)
                    throws Exception {
            }
        };

        // 持续监听节点
        client.subscribeDataChanges(lockPath, listener);

        // 锁还存在，阻塞
        if (this.client.exists(lockPath)) {
            try {
                System.out.println("...... 未获取到锁，阻塞中 ......");
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 取消注册
        client.unsubscribeDataChanges(lockPath, listener);
    }
}

