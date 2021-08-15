package com.example.servlet;

import com.example.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 自定义Servlet容器的初始化器
 * 1.创建自定义Servlet容器的初始化器并实现Servlet容器的初始化器中的onStartup(...)方法
 * 2.在类路径下创建文件META-INF/services/javax.servlet.ServletContainerInitializer
 * 3.将自定义Servlet容器的初始化器的全类名作为文件内容写入刚创建的文件中
 * 4.在自定义Servlet容器的初始化器类上使用注解@HandlesTypes标注需要在web容器启动时需要处理的类或接口的父类(类或接口)
 *
 * @author: dengzm
 * @date: 2021-08-14 20:53:05
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     * 应用启动的时候,会运行onStartup方法(Tomcat容器来调用的)
     * 1.在项目启动的时候,使用servletContext注册web组件(Servlet、Filter、Listener)
     * 2.在项目启动的时候,在注册好的Listener中的contextInitialized()方法中,从ServletContextEvent中获取到servletContext来注册web组件
     *
     * @param set            需要处理的类或接口(@HandlesTypes中指定的类的子类或子接口)
     * @param servletContext 代表当前Web应用的ServletContext,一个Web应用一个ServletContext
     * @throws ServletException 异常
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("需要处理的类: ");
        for (Class<?> clazz : set) {
            System.out.println(clazz);
        }

        //注册组件  ServletRegistration
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", UserServlet.class);
        //配置servlet的映射信息
        userServlet.addMapping("/user");

        //注册Listener
        servletContext.addListener(UserListener.class);

        //注册Filter  FilterRegistration
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        //配置Filter的映射信息(过滤REQUEST)
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

    }
}
