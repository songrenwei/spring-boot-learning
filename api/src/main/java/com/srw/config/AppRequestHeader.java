package com.srw.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/23 13:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRequestHeader {

    private String accessToken;

    private String newAccessToken;

    private String timestamp;

    private String context;

    private String requestIp;

    private String traceId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"accessToken\":\"").append(accessToken).append('\"');
        sb.append(",\"newAccessToken\":\"").append(newAccessToken).append('\"');
        sb.append(",\"timestamp\":\"").append(timestamp).append('\"');
        sb.append(",\"context\":\"").append(context).append('\"');
        sb.append(",\"requestIp\":\"").append(requestIp).append('\"');
        sb.append(",\"traceId\":\"").append(traceId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}