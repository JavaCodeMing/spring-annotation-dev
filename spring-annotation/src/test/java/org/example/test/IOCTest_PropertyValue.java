package org.example.test;

import org.example.propertyValue.config.MainConfig;
import org.example.propertyValue.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 1.测试@Value注解实现属性值注入
 * 2.@PropertySource注解+@Value注解实现将属性文件读取到运行环境变量,再从运行环境变量中获取注入到对象属性上
 *
 * @author: dengzm
 * @date: 2021-07-05 23:32:51
 */
public class IOCTest_PropertyValue {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
        applicationContext.close();
    }
}
