package org.example.test;

import org.example.importBean.config.MainConfig;
import org.example.importBean.entity.Blue;
import org.example.importBean.entity.RainBow;
import org.example.importBean.entity.Yellow;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.测试@Import(要导入到容器中的组件)的方式注册组件
 * 2.测试@Import(ImportSelector.class)的方式注册组件
 * 4.测试@Import(ImportBeanDefinitionRegistrar.class)方式注册组件,手动使用registry注册
 * 5.测试获取工厂Bean,直接获取得到的是工厂Bean的getObject()方法返回的对象,在id前加&获取的是工厂Bean本身
 *      (工厂Bean创建的是否是单例由isSingleton()决定)
 *
 * @author: dengzm
 * @date: 2021-07-04 16:50:36
 */
public class IOCTest_Import {
    @Test
    public void testImport(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        System.out.println("-----------------------------------------------");

        Blue blue = applicationContext.getBean(Blue.class);
        System.out.println(blue);
        Yellow yellow = applicationContext.getBean(Yellow.class);
        System.out.println(yellow);
        System.out.println("-----------------------------------------------");

        RainBow rainBow = applicationContext.getBean(RainBow.class);
        System.out.println(rainBow);
        System.out.println("-----------------------------------------------");

        //工厂Bean获取的是调用getObject()创建的对象,工厂Bean创建的是否是单例由isSingleton()决定
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        Object bean3 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型："+bean2.getClass());
        System.out.println(bean2 == bean3);
        Object bean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(bean4.getClass());
    }
}
