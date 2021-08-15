package org.example.autowired.config;

import org.example.autowired.dao.BookDao;
import org.example.autowired.dao.PersonDao;
import org.example.autowired.entity.Car;
import org.example.autowired.entity.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配: Spring利用依赖注入(DI)完成对IOC容器中各个组件的依赖关系赋值
 * 1.使用@Autowired注解注入:
 *      1.1.默认优先按照类型去容器中容器中找对应T的组件
 *          applicationContext.getBean(BookDao.class);
 *      1.2.如果找到多个相同类型的组件,再将属性的名称作为组件的id去容器中查找
 *          applicationContext.getBean("bookDao")
 *      1.3.@Qualifier注解的配合使用: 使用@Qualifier指定需要装配的组件的id,而不是使用属性名
 *          @Qualifier("bookDao")
 *      1.4.自动装配默认一定要将属性赋值好,若未找到对应的组件,则在注入时就会报错,可通过@Autowired注解的required属性来允许为空
 *          @Autowired(required=false)
 *      1.5.@Primary注解的配合使用: @Primary可以指定需要装配的bean
 *          让Spring进行自动装配时,默认使用首选的bean,但可通过@Primary指定需要装配的bean
 * 2.使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]注入:
 *      2.1.@Resource注解: 和@Autowired一样可以实现自动装配功能,
 *          2.1.1.默认是按照组件名称进行装配的
 *          2.1.2.不支持@Primary功能,不支持@Autowired(reqiured=false)功能
 *      2.2.@Inject注解: 需要导入javax.inject的包,和Autowired的功能一样
 *          2.2.1.没有required=false的功能
 *  注解@Autowired是Spring定义的, 注解@Resource和@Inject都是java规范
 *  AutowiredAnnotationBeanPostProcessor: 解析完成自动装配功能
 * 3.使用@Autowired进行自动注入的方式:
 *      3.1.标注在方法上: @Bean+方法参数,方法执行时方法的参数对象会从容器中注入
 *          默认不写@Autowired效果是一样的,都能自动装配
 *      3.2.标在构造器上: 容器在创建该类的实例时,调用其构造方法时,构造方法的参数对象会从容器中注入
 *          如果组件只有一个有参构造器,这个有参构造器的@Autowired可以省略,参数位置的组件还是可以自动从容器中获取
 *      3.3.标注在方法参数前: 方法执行时方法的参数对象会从容器中注入
 * 4.自定义组件来使用Spring底层的一些组件:
 *      4.1.自定义组件实现xxxAware: 在创建对象的时候,会调用接口规定的方法注入相关组件
 *      4.2.xxxAware实现原理: 功能使用xxxProcessor来实现
 *      4.3.案例: ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware
 *
 * @author: dengzm
 * @date: 2021-07-06 23:15:50
 */
@Configuration
@ComponentScan({"org.example.autowired"})
public class MainConifg {

    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    @Primary
    @Bean("personDao2")
    public PersonDao personDao(){
        PersonDao personDao = new PersonDao();
        personDao.setLable("2");
        return personDao;
    }

    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
