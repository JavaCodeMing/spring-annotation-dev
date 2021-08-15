package com.example.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author: dengzm
 * @date: 2021-08-15 16:05:54
 */
public class DeferredResultQueue {
    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedDeque<>();

    public static void save(DeferredResult<Object> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get(){
        return queue.poll();
    }
}
