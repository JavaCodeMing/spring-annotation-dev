package org.example.test;

import org.example.componentExtension.config.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.测试默认情况下,容器中实例是单例的
 * 2.测试使用@Scope注解将组件注册成多实例的
 * 3.测试使用@Lazy注解将组件的创建延迟到获取时,即懒加载
 * 4.测试使用@Conditional注解实现按条件注册组件
 *      通过VM options将系统名称改为linux来测试 -> -Dos.name=linux
 * @author: dengzm
 * @date: 2021-07-04 13:32:15
 */
public class IOCTest_ComponentExtension {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("ioc容器创建完成....");
        Object bean = applicationContext.getBean("zhangsan");
        Object bean2 = applicationContext.getBean("zhangsan");
        System.out.println("测试组件两次取是否相同: " + (bean == bean2));
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("ioc容器创建完成....");
        Object bean = applicationContext.getBean("lisi");
        Object bean2 = applicationContext.getBean("lisi");
        System.out.println("测试组件两次取是否相同: " + (bean == bean2));
    }

    @Test
    public void test03(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("ioc容器创建完成....");
        Object bean = applicationContext.getBean("wangwu");
    }

    @Test
    public void test04(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("ioc容器创建完成....");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}
