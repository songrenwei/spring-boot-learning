package com.srw.ioc;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class GeneralBeanDefinition implements BeanDefinition {
    private Class<?> beanClass;
    private String scope = BeanDefinition.SCOPE_SINGLETON;
    private String factoryBeanName;
    private String factoryMethodName;
    private String initMethodName;
    private String destroyMethodName;

    public void setScope(String scope) {
        if (StringUtils.isNotBlank(scope)){
            this.scope = scope;
        }
    }

    @Override
    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public boolean isSingleton() {
        return BeanDefinition.SCOPE_SINGLETON.equals(this.scope);
    }

    @Override
    public boolean isPrototype() {
        return BeanDefinition.SCOPE_PROTOTYPE.equals(this.scope);
    }

    @Override
    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    @Override
    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    @Override
    public String getInitMethodName() {
        return this.initMethodName;
    }

    @Override
    public String getDestoryMethodName() {
        return this.destroyMethodName;
    }
}

