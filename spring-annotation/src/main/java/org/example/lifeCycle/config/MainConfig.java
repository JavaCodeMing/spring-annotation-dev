package org.example.lifeCycle.config;

import org.example.lifeCycle.entity.Car;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 1.bean的生命周期: bean创建---初始化----销毁的过程
 * 2.容器管理bean的生命周期
 * 3.自定义初始化和销毁方法: 容器在bean进行到当前生命周期的时候来调用自定义的初始化和销毁方法
 * 4.构造(对象创建):
 *      单实例: 在容器启动的时候创建对象
 *   	多实例: 在每次获取的时候创建对象
 *      BeanPostProcessor.postProcessBeforeInitialization
 * 5.初始化: 对象创建完成，并赋值好，调用初始化方法
 *      BeanPostProcessor.postProcessAfterInitialization
 * 6.销毁:
 *      单实例: 容器关闭的时候
 *      多实例: 容器不会管理这个bean,容器不会调用销毁方法
 * 7.在bean生命周期阶段做额外操作:
 *      7.1.指定初始化和销毁方法:
 *          通过@Bean指定initMethod和destroyMethod,相当于xml中<bean>标签的init-method和destroy-method
 *      7.2.通过让Bean实现InitializingBean(定义初始化逻辑)和DisposableBean(定义销毁逻辑)
 *      7.3.可以使用JSR250中的@PostConstruct和@PreDestroy: (这两个注解是Java EE的一部分,在Java9中被弃用,在Java11中被删除)
 *          注解@PostConstruct: 在bean创建完成并且属性赋值完成后执行初始化方法
 *          注解@PreDestroy: 在容器销毁bean之前通知我们进行清理工作
 *          使用这两个注解需要额外导包:
 *              <dependency>
 *                  <groupId>javax.annotation</groupId>
 *                  <artifactId>javax.annotation-api</artifactId>
 *                  <version>1.3.2</version>
 *              </dependency>
 *      7.4.使用BeanPostProcessor接口中的两个处理方法在bean初始化前后做额外操作:
 *          postProcessBeforeInitialization:在初始化之前工作
 *          postProcessAfterInitialization:在初始化之后工作
 *          内部原理:
 *              populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 *              initializeBean
 *              {
 *                  applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *                  invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 *                  applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *              }
 *          注: 遍历得到容器中所有的BeanPostProcessor,挨个执行postProcessorsBeforeInitialization,一但返回null,跳出for循环,
 *              不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 * 8.Spring底层对 BeanPostProcessor 的使用:
 *      bean赋值,注入其他组件,@Autowired,生命周期注解功能,@Async,xxx BeanPostProcessor
 *
 * @author: dengzm
 * @date: 2021-07-05 20:48:09
 */
@Configurable
@ComponentScan("org.example.lifeCycle.entity")
public class MainConfig {
    //@Scope("prototype")
    @Bean(initMethod="init",destroyMethod="destroy")
    public Car car(){
        return new Car();
    }
}
