package com.srw.aspect;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/12/11/13:46
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

//    @Pointcut("execution(public * com.srw.controller.*.*(..))")
    @Pointcut("@annotation(com.srw.common.annotation.Log)")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        // 调用前
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = point.proceed();
        // 调用后
        LogInfo requestInfo = new LogInfo();
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName()));
        requestInfo.setRequest(getRequestParams(point));
        requestInfo.setResponse(result);
        requestInfo.setTimeCost((System.currentTimeMillis() - start) + " ms");
        log.info("Request Info : {}", JSONUtil.toJsonStr(requestInfo));

        return result;
    }

    /**
     * 获取入参  
     * @param proceedingJoinPoint
     *
     * @return
     * */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名  
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值  
        Object[] paramValues = proceedingJoinPoint.getArgs();

        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  // 获取文件名
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

    @Data
    public class LogInfo {
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object request;
        private Object response;
        private String timeCost;
        private RuntimeException exception;
    }
}