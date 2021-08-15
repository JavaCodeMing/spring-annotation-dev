package org.example.profile.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.example.profile.entity.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * 1.Profile: Spring为我们提供的可以根据当前环境,动态的激活和切换一系列组件的功能
 * 2.@Profile注解的作用: 指定组件在哪个环境的情况下才能被注册到容器中,不指定则任何环境下都能注册这个组件
 *      2.1.加了环境标识的bean,只有这个环境被激活的时候才能注册到容器中;默认是default环境;
 *      2.2.写在配置类上,只有是指定的环境的时候,整个配置类里面的所有配置才能开始生效;
 *      2.3.没有标注环境标识的bean在,任何环境下都是加载的;
 * 3.测试案例: 以数据源配置开发环境、测试环境、生产环境为例
 *
 * @author: dengzm
 * @date: 2021-07-20 21:44:56
 */
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfig implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;
    private String  driverClass;

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}")String pwd) throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}")String pwd) throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}")String pwd) throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Bean
    public Yellow yellow(){
        return new Yellow();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        driverClass = resolver.resolveStringValue("${db.driverClass}");
    }

}
