package com.srw.schedule.client;

import com.dtflys.forest.annotation.Request;
import com.srw.common.bean.JsonResult;

/**
 * @Description: 执行
 * @Author: renwei.song
 * @Date: 2021/3/31 16:16
 */
public interface ExecuteClient {

    @Request(url = "http://localhost:8083/consumer/execute?userId=${0}", dataType = "json")
    JsonResult<?> execute(Long userId);

}
