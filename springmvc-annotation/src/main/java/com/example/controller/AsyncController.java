package com.example.controller;

import com.example.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author: dengzm
 * @date: 2021-08-15 14:29:11
 */
@Controller
public class AsyncController {
    /**
     * 基于Callable的异步调用:
     *  1.控制器返回Callable
     *  2.Spring异步处理: 将Callable 提交到 TaskExecutor 使用一个隔离的线程进行执行
     *  3.DispatcherServlet和所有的Filter随web容器线程结束而退出,但是response保持打开状态
     *  4.Callable返回结果,SpringMVC将请求重新派发给容器,恢复之前的处理
     *  5.根据Callable返回的结果,SpringMVC继续进行视图渲染流程等(从收请求-视图渲染)
     *  6.日志记录:
     *      preHandle.../springmvc-annotation/async01
     *      主线程Thread[http-bio-8080-exec-3,5,main]开始 ==>1629010791068
     *      主线程Thread[http-bio-8080-exec-3,5,main]结束 ==>1629010791080
     *      ===========DispatcherServlet及所有的Filter退出线程==============
     *
     *      ===================等待Callable执行===================
     *      副线程Thread[MvcAsync1,5,main]开始 ==>1629010791090
     *      副线程Thread[MvcAsync1,5,main]结束 ==>1629010793104
     *      ===================Callable执行完成===================
     *
     *      ================再次收到之前重发过来的请求=================
     *      preHandle.../springmvc-annotation/async01
     *      postHandle...
     *      afterCompletion...
     *  7.在使用异步请求调用的时候,普通注册的拦截器已经不能真正地拦截到异步逻辑的整个处理,因此需要使用异步的拦截器:
     *      原生API的AsyncListener
     *      SpringMVC: 实现AsyncHandlerInterceptor的afterConcurrentHandlingStarted()方法
     *
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("主线程" + Thread.currentThread() + "开始 ==>" + System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程" + Thread.currentThread() + "开始 ==>" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("副线程" + Thread.currentThread() + "结束 ==>" + System.currentTimeMillis());
            return "Callable<String> async01()";
        };
        System.out.println("主线程" + Thread.currentThread() + "结束 ==>" + System.currentTimeMillis());
        return callable;
    }

    /**
     * 基于DeferredResult的异步调用:
     *  1.定义一个返回值为DeferredResult对象的请求方法
     *  2.请求方法中,返回DeferredResult实例对象
     *  3.当其他线程拿到上述DeferredResult实例对象并向其设置结果时,上述的请求方法会返回该设置的结果
     *
     *  此处分别依次调用"/createOrder"和"/create"请求可模拟上述流程(需要在设置的超时时间内完成,否则"/createOrder"请求就响应超时结果了)
     */
    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder() {
        // 此处设置的超时时间为3秒,请求超时返回"create fail..."
        DeferredResult<Object> deferredResult = new DeferredResult<>(3000L, "create fail...");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success ==> " + order;
    }
}
