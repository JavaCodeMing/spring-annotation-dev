package org.example.test;

import org.example.lifeCycle.config.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.测试通过@Bean注解的initMethod和destroyMethod属性分别指定bean的初始化方法和销毁方法
 *      单例情况下: 构造方法 -> 初始化方法 -> 销毁方法
 *      多例情况下: 只有在获取bean实例时才创建并初始化,容器关闭时不会调用该实例的销毁方法
 * 2.测试通过让Bean实现InitializingBean和DisposableBean接口的方法来调用bean的初始化和销毁的逻辑
 * 3.测试使用JSR250中的@PostConstruct和@PreDestroy指定方法来进行初始化操作和销毁的清理操作
 * 4.测试使用自定义BeanPostProcessor在bean初始化前后做额外操作
 *
 * @author: dengzm
 * @date: 2021-07-05 21:28:46
 */
public class IOCTest_LifeCycle {
    @Test
    public void test01(){
        //创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("容器创建完成...");
        //关闭容器
        applicationContext.close();
    }
}
