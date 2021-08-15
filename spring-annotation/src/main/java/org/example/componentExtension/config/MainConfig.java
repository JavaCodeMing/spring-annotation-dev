package org.example.componentExtension.config;

import org.example.componentExtension.condition.LinuxCondition;
import org.example.componentExtension.condition.WindowsCondition;
import org.example.componentExtension.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * 1.使用@Scope注解设置组件作用域
 *      1.1.Spring容器中注册的组件默认是单实例
 *      1.2.使用@Scope注解可以调整容器中组件的作用域
 *          1.2.1.prototype:多实例的,ioc容器启动并不会去调用方法创建对象放在容器中,在每次获取的时候才会调用方法创建对象。
 *          1.2.2.singleton:单实例的(默认),ioc容器启动会调用方法创建对象并将该对象放到ioc容器中,每次获取都是获取到同一对象。
 *          1.2.3.request: 同一次请求创建一个实例。
 *          1.2.4.session: 同一个session创建一个实例。
 * 2.使用@Lazy注解指定组件懒加载
 *      1.1.单实例bean默认在容器启动的时候创建对象
 *      1.2.懒加载：容器启动不创建对象,第一次使用(获取)Bean时才创建对象,并初始化。
 * 3.使用@Conditional注解实现按条件注册组件
 *      按照一定的条件进行判断,满足条件才将该组件注册到容器中
 * @author: dengzm
 * @date: 2021-07-04 13:19:27
 */
public class MainConfig {

    @Bean("zhangsan")
    public Person person(){
        System.out.println("给容器中添加Person....");
        return new Person("张三", 25);
    }

    @Scope("prototype")
    @Bean("lisi")
    public Person person01(){
        System.out.println("给容器中添加Person....");
        return new Person("李四", 27);
    }

    @Lazy
    @Bean("wangwu")
    public Person person02(){
        System.out.println("给容器中添加Person....");
        return new Person("李四", 27);
    }

    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person03(){
        return new Person("Bill Gates",62);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person04(){
        return new Person("Linus", 48);
    }
}
