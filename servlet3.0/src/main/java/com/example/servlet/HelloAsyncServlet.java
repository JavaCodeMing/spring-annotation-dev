package com.example.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1.支持异步处理: asyncSupported=true
 * 2.开启异步模式: startAsync()
 * 3.业务逻辑进行异步处理: startAsync.start()
 * 4.获取异步上线文,并从异步上下文中获取响应对象,再写入响应数据
 *
 * @author: dengzm
 * @date: 2021-08-15 13:45:46
 */
@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("主线程" + Thread.currentThread() + "开始 ==>" + System.currentTimeMillis());
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                System.out.println("副线程" + Thread.currentThread() + "开始 ==>" + System.currentTimeMillis());
                sayHello();
                asyncContext.complete();
                // 获取到异步上下文
                AsyncContext context = req.getAsyncContext();
                ServletResponse response = context.getResponse();
                response.getWriter().write("hello async...");
                System.out.println("副线程" + Thread.currentThread() + "结束 ==>" + System.currentTimeMillis());
            } catch (Exception ignored) {
            }
        });
        System.out.println("主线程" + Thread.currentThread() + "结束 ==>" + System.currentTimeMillis());
    }

    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread() + " processing...");
        Thread.sleep(3000);
    }
}
