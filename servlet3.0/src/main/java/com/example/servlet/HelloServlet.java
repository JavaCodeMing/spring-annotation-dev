package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注解形式的原生Servlet开发(对应于SpringMVC中的controller中的请求方法  )
 *
 * @author: dengzm
 * @date: 2021-08-14 16:59:39
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Thread.currentThread() + " start...");
        try {
            sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().write("hello...");
        System.out.println(Thread.currentThread()+" end...");
    }

    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread() + " processing...");
        Thread.sleep(3000);
    }
}
