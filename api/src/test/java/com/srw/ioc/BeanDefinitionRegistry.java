package com.srw.ioc;

/**
 * bean的定义注册
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册Bean定义
     * @param beanName
     * @param beanDefinition
     * @throws Exception
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception;

    /**
     * 获取Bean定义
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 判断Bean是否已经被注册
     * @param beanName
     * @return
     */
    Boolean containsBeanDefinition(String beanName);

}

