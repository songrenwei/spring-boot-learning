package com.srw.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.srw.common.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

/**
 * @Description: 实体类转化
 * @Author: songrenwei
 * @Date: 2020/10/14/15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = -113486665877014274L;
    private static String successMsg = "success";

    @JsonProperty("code")
    private String status;

    @JsonProperty("msg")
    private String message;

    private T data;

    public JsonResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> JsonResult<T> success() {
        return new JsonResult<>(Constants.RESULT_SUCCESS_CODE, successMsg);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(Constants.RESULT_SUCCESS_CODE, successMsg, data);
    }

    public static <T> JsonResult<T> error(String errorMessage) {
        return new JsonResult<>(Constants.RESULT_ERROR_CODE, errorMessage, null);
    }

    public static <T> JsonResult<T> error(String status, String errorMessage) {
        return new JsonResult<>(status, errorMessage, null);
    }

    public static boolean isSuccess(@Nullable JsonResult<?> result) {
        return Optional.ofNullable(result)
                .map(JsonResult::getStatus)
                .map(code -> StringUtils.equals(Constants.RESULT_SUCCESS_CODE, code))
                .orElse(Boolean.FALSE);
    }

}
