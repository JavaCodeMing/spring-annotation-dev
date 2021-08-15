package org.example.tx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 声明式事务
 * 1.环境搭建:
 *      1.1.导入相关依赖: 数据源、数据库驱动、spring-jdbc模块
 *      1.2.配置数据源和JdbcTemplate
 *      1.3.给方法上标注 @Transactional 表示当前方法是一个事务方法
 *      1.4.在配置类上标注 @EnableTransactionManagement 用来开启基于注解的事务管理功能
 *      1.5.配置事务管理器来控制事务
 * 2.原理:
 *      2.1.使用@Import+ImportSelector(TransactionManagementConfigurationSelector)给容器中注册如下两个组件:
 *          AutoProxyRegistrar
 *          ProxyTransactionManagementConfiguration
 *      2.2.AutoProxyRegistrar: 给容器中注册一个组件->
 *          beanName: org.springframework.aop.config.internalAutoProxyCreator class: InfrastructureAdvisorAutoProxyCreator
 *          利用后置处理器机制在对象创建以后,包装对象,返回一个代理对象(增强器),代理对象执行方法利用拦截器链进行调用
 *      2.3.ProxyTransactionManagementConfiguration: 将当前组件注册到容器中,由于该组件是一个配置类,继续将其中定义的bean方法注册到容器
 *          2.3.1.给容器中注册事务增强器: BeanFactoryTransactionAttributeSourceAdvisor
 *              2.3.1.1.事务增强器要用事务注解的信息: AnnotationTransactionAttributeSource -> 解析事务注解
 *              2.3.1.2.事务拦截器: TransactionInterceptor -> 保存了事务属性信息,事务管理器
 *          2.3.2.执行的流程:
 *                  目标方法执行时 -> 执行拦截器链 -> 事务拦截器 -> 执行目标方法
 *
 * @author: dengzm
 * @date: 2021-08-08 16:23:18
 */
@ComponentScan("org.example.tx")
@EnableTransactionManagement
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://47.92.132.50:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }
}
