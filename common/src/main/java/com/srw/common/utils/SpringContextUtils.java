package com.srw.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Spring容器会检测容器中的所有Bean，如果发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
 * 自动调用该Bean的setApplicationContext方法。调用该方法时，会将容器本身作为参数传给该方法——该方法中的实现部分将
 * Spring传入的参数（容器本身）赋给该类对象的applicationContext实例变量，因此接下来可以通过该applicationContext
 * 实例变量来访问容器本身
 *
 */
@Component
@SuppressWarnings("unchecked")
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context = null;

    /**
     * @param applicationContext 上下文
     * @throws BeansException
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }

    /**
     * 获取当前环境
     *
     * @return String
     */
    public static String getActiveProfile() {
        String[] array = context.getEnvironment().getActiveProfiles();
        return array[array.length - 1];
    }
}
