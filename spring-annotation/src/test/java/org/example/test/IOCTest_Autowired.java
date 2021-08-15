package org.example.test;

import org.example.autowired.config.MainConifg;
import org.example.autowired.entity.Boss;
import org.example.autowired.entity.Car;
import org.example.autowired.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.测试仅使用@Autowired注解从容器中注入组件
 * 2.测试使用@Qualifier注解配合@Autowired注解使用
 * 3.测试通过@Autowired注解注入一个容器中不存在的组件,指定与不指定required属性的区别
 * 4.测试使用@Primary注解配合@Autowired注解使用
 * 5.测试使用@Resource注解从容器中注入组件
 * 6.测试使用@Inject注解从容器中注入组件
 * 7.测试@Autowired注解的不同注入方式(方法上,构造器上,参数前)
 * 8.测试自定义组件实现xxxAware
 * 9.测试循环依赖: (自行验证)
 *      两个交由容器管理的类,在类中分别注入了对方实例,形成了循环依赖;
 *      debug查看spring源码中是如何解决循环依赖下对象注册创建的;
 *
 * @author: dengzm
 * @date: 2021-07-06 23:32:02
 */
public class IOCTest_Autowired {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        BookService1 bookService1 = applicationContext.getBean(BookService1.class);
        System.out.println(bookService1);
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        BookService2 bookService2 = applicationContext.getBean(BookService2.class);
        System.out.println(bookService2);
    }

    @Test
    public void test03(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        BookService3 bookService3 = applicationContext.getBean(BookService3.class);
        System.out.println(bookService3);
    }

    @Test
    public void test04(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        PersonService1 personService1 = applicationContext.getBean(PersonService1.class);
        System.out.println(personService1);
    }

    @Test
    public void test05(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        PersonService2 personService2 = applicationContext.getBean(PersonService2.class);
        System.out.println(personService2);
    }

    @Test
    public void test06(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        PersonService3 personService3 = applicationContext.getBean(PersonService3.class);
        System.out.println(personService3);
    }

    @Test
    public void test07(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        Boss boss = applicationContext.getBean(Boss.class);
        Car car = applicationContext.getBean(Car.class);
        System.out.println("容器中的Boss: " + boss);
        System.out.println("容器中的Car: " + car);
        applicationContext.close();
    }

    @Test
    public void test08(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifg.class);
        applicationContext.close();
    }
}
