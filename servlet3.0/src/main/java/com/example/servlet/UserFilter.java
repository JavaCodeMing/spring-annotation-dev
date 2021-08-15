package com.example.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: dengzm
 * @date: 2021-08-14 23:35:58
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 过滤请求
        System.out.println("UserFilter...doFilter...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
