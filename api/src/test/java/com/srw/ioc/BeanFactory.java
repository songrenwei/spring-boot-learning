package com.srw.ioc;

/**
 * bean的工厂
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}

