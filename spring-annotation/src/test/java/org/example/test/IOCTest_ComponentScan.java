package org.example.test;

import org.example.componentScan.config.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.测试@Bean注解给容器中注册组件
 * 2.测试@ComponentScan注解的使用
 *      测试扫描指定包下的类,并注册到容器
 *      测试按注解类型过滤注册组件
 *      测试按类类型过滤注册组件
 *      测试按自定义过滤器过滤注册组件
 * @author: dengzm
 * @date: 2021-07-04 11:39:01
 */
public class IOCTest_ComponentScan {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
