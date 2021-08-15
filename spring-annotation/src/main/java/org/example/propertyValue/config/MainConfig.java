package org.example.propertyValue.config;

import org.example.propertyValue.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 1.使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中
 * 2.加载完外部的配置文件以后使用${}取出配置文件的值
 *
 * @author: dengzm
 * @date: 2021-07-05 23:26:03
 */
@PropertySource(value={"classpath:/person.properties"})
@Configuration
public class MainConfig {
    @Bean
    public Person person(){
        return new Person();
    }
}
