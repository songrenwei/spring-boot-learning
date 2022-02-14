package com.srw.schedule.client;

import com.dtflys.forest.annotation.Request;
import com.srw.common.bean.JsonResult;

import java.util.List;

/**
 * @Description: 查询
 * @Author: renwei.song
 * @Date: 2021/3/31 16:16
 */
public interface QueryClient {

    @Request(url = "http://localhost:8082/provider/query", dataType = "json")
    JsonResult<List<Long>> query();

    @Request(url = "http://106.14.119.197:8081/metrics/online", dataType = "json")
    JsonResult<?> metrics();

}
