package org.example.autowired.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 1.实现ApplicationContextAware接口的setApplicationContext方法,可以从方法参数中获取到容器对象
 * 2.实现BeanNameAware接口的setBeanName方法,可以从方法参数中获取到容器当前组件注册的名称
 * 3.实现EmbeddedValueResolverAware方法,可以从方法参数中获取到字符串解析器对象,能处理一系列字符串的逻辑比如:占位符解释、SpEL计算等等
 *
 * @author dengzm
 */
@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc：" + applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字：" + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolveStringValue = resolver.resolveStringValue("你好 ${os.name} 我是 #{20*18}");
        System.out.println("解析的字符串：" + resolveStringValue);
    }

}
