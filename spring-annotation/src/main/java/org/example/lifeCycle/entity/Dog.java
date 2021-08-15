package org.example.lifeCycle.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: dengzm
 * @date: 2021-07-05 22:16:47
 */
@Component
public class Dog {

    public Dog(){
        System.out.println("dog constructor...");
    }

    @PostConstruct
    public void init(){
        System.out.println("Dog....@PostConstruct...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Dog....@PreDestroy...");
    }
}
