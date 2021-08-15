package org.example.test;

import org.example.profile.config.MainConfig;
import org.example.profile.entity.Yellow;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * 1.测试指定不同环境下加载是分别是哪个或哪些组件(分别指定dev环境、test环境、prod环境)
 *
 * @author: dengzm
 * @date: 2021-07-20 22:59:03
 */
public class IOCTest_Profile {
    @Test
    public void test01(){
        //1、创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2、设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        //3、注册主配置类
        applicationContext.register(MainConfig.class);
        //4、启动刷新容器
        applicationContext.refresh();
        //5、获取容器中的数据源
        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String string : namesForType) {
            System.out.println(string);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);
        applicationContext.close();
    }
}
