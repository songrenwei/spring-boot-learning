package com.srw.ioc;

import org.junit.AfterClass;
import org.junit.Test;

public class DefaultBeanFactoryTest {

    static DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();

    /**
     * 构造方法方式
     *
     * @throws Exception
     */
    @Test
    public void testRegister() throws Exception {
        // bean定义
        GeneralBeanDefinition generalBeanDefinition = new GeneralBeanDefinition();
        generalBeanDefinition.setBeanClass(Bean1.class);
        generalBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        generalBeanDefinition.setInitMethodName("init");
        generalBeanDefinition.setDestroyMethodName("destroy");
        // 注册bean定义
        defaultBeanFactory.registerBeanDefinition("bean1",generalBeanDefinition);
    }

    /**
     * 静态工厂方法方式
     *
     * @throws Exception
     */
    @Test
    public void testRegisterStaticFactoryMethod() throws Exception {
        GeneralBeanDefinition generalBeanDefinition = new GeneralBeanDefinition();
        generalBeanDefinition.setBeanClass(Bean1Factory.class);
        generalBeanDefinition.setFactoryMethodName("getBean1");
        defaultBeanFactory.registerBeanDefinition("staticBean1",generalBeanDefinition);
    }

    /**
     * 工厂方法方式
     *
     * @throws Exception
     */
    @Test
    public void testRegisterFactoryMethod() throws Exception {
        GeneralBeanDefinition generalBeanDefinition = new GeneralBeanDefinition();
        generalBeanDefinition.setBeanClass(Bean1Factory.class);
        String factoryBeanName = "factory";
        defaultBeanFactory.registerBeanDefinition(factoryBeanName,generalBeanDefinition);

        generalBeanDefinition = new GeneralBeanDefinition();
        generalBeanDefinition.setFactoryBeanName(factoryBeanName);
        generalBeanDefinition.setFactoryMethodName("getOtherBean1");
        generalBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        defaultBeanFactory.registerBeanDefinition("factoryBean",generalBeanDefinition);
    }

    @AfterClass
    public static void testGetBean() throws Exception {
        System.out.println("构造方法方式···");
        for (int i = 0;i<3;i++){
            Bean1 bean1 = (Bean1) defaultBeanFactory.getBean("bean1");
            bean1.doSomething();
        }

        System.out.println("静态工厂方法方式···");
        for (int i = 0;i<3;i++){
            Bean1 bean1 = (Bean1) defaultBeanFactory.getBean("staticBean1");
            bean1.doSomething();
        }

        System.out.println("工厂方法方式···");
        for (int i = 0;i<3;i++){
            Bean1 bean1 = (Bean1) defaultBeanFactory.getBean("factoryBean");
            bean1.doSomething();
        }

        defaultBeanFactory.close();
    }
}

