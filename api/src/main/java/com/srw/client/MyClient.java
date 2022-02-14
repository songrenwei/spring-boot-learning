package com.srw.client;

import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.Request;

import java.util.Map;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/12 14:36
 */
public interface MyClient {

    @Request(url = "http://ditu.amap.com/service/regeo", dataType = "json")
    Map getLocation(@DataParam("longitude") String longitude, @DataParam("latitude") String latitude);

}
