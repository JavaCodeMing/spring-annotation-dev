package org.example.importBean.config;

import org.example.importBean.entity.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: dengzm
 * @date: 2021-07-04 20:06:27
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 注册Bean定义信息
     * @param importingClassMetadata 当前类的注解信息
     * @param registry BeanDefinition注册类,用于手动注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition("org.example.importBean.entity.Red");
        boolean definition2 = registry.containsBeanDefinition("org.example.importBean.entity.Blue");
        if(definition && definition2){
            //指定Bean定义信息(Bean的类型,Bean。。。)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个Bean,指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
