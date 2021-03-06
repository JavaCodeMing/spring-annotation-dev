package com.example;

import com.example.config.AppConfig;
import com.example.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web容器启动的时候创建对象,调用方法来初始化容器以前前端控制器
 *
 * @author: dengzm
 * @date: 2021-08-15 00:42:55
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取根容器的配置类(等效于读取Spring的配置文件来创建父容器)
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类(等效于读取SpringMVC的配置文件来创建子容器)
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     *  /: 拦截所有请求(包括静态资源(xx.js,xx.png)),但是不包括*.jsp
     *  /*: 拦截所有请求,连*.jsp页面都拦截,jsp页面是tomcat的jsp引擎解析的
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
