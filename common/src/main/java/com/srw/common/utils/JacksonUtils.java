package com.srw.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/23 13:35
 */
@Slf4j
public class JacksonUtils {
    public static <T> T json2Obj(String json, TypeReference<T> tr) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.readValue(json, tr);
        } catch (IOException ex) {
            log.error(json + "转换对象异常:" + ex);
            return null;
        }
    }

    public static Map json2Map(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Map.class);
        } catch (IOException ex) {
            log.error(json + "转换对象异常:" + ex);
            return null;
        }
    }

    public static String obj2Json(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (IOException ex) {
            log.error(obj + "转换json异常:" + ex);
            return null;
        }
    }
}
