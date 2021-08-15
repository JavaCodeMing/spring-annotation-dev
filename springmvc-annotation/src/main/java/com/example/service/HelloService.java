package com.example.service;

import org.springframework.stereotype.Service;

/**
 * @author: dengzm
 * @date: 2021-08-15 01:04:02
 */
@Service
public class HelloService {
    public String sayHello(String name){
        return "Hello," + name;
    }
}
