package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 子容器(即SpringMVC容器)
 *  1.扫描"com.example"下的Controller
 *  2.useDefaultFilters = false,禁用默认过滤规则
 *
 * @author: dengzm
 * @date: 2021-08-15 00:55:03
 */
@ComponentScan(value = "com.example",includeFilters = {
        // 子容器只扫描Controller
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    /**
     * 自定义配置视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    /**
     * 自定义配置静态资源访问
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加普通拦截器
        registry.addInterceptor(new SyncInterceptor()).addPathPatterns("/**");
        // 添加异步拦截器
        registry.addInterceptor(new AsyncInterceptor()).addPathPatterns("/**");
    }
}
