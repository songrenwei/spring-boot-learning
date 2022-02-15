package com.srw.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2022/2/15 14:11
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获取TargetObject的class对象并获取TargetObject的实例
        Class<?> targetClass =  Class.forName("com.srw.reflect.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();

        // 获取所有类中所有定义的方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("====================================");
        // 获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "publicMethod");
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        // 为了调用private方法我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
        System.out.println("====================================");
        // 获取指定属性并对属性进行修改
        Field field = targetClass.getDeclaredField("value");
        // 为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(targetObject, "field");
        System.out.println(targetObject.getValue());
    }

}
