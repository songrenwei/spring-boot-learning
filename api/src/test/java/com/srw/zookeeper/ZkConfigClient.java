package com.srw.zookeeper;

import com.srw.common.utils.SpringContextUtils;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkConfigClient implements Runnable {

    private String nodePath = "/config";
    private String nodeValue;

    @Override
    public void run() {
        ZkClient zkClient = SpringContextUtils.getBean("zkClient");
        // 获取节点
        if (zkClient.exists(nodePath)) {
            nodeValue = zkClient.readData(nodePath);
            System.out.println(nodeValue);
            zkClient.subscribeDataChanges(nodePath, new IZkDataListener() {

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    if(dataPath.equals(nodePath)) {
                        System.out.println("节点：" + dataPath + "被删除了！");
                    }
                }

                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    if(dataPath.equals(nodePath)) {
                        System.out.println("节点：" + dataPath + ", 数据：" + data + " - 更新");
                        nodeValue = (String) data;
                    }
                }
            });
        }
    }

}
