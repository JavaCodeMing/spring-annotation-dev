package com.example.config;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异步拦截器
 *
 * @author: dengzm
 * @date: 2021-08-15 16:58:08
 */
public class AsyncInterceptor implements AsyncHandlerInterceptor {
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AsyncInterceptor...afterConcurrentHandlingStarted...");
    }
}
