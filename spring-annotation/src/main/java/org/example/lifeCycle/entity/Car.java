package org.example.lifeCycle.entity;

/**
 * @author: dengzm
 * @date: 2021-07-05 21:26:25
 */
public class Car {

    public Car(){
        System.out.println("car constructor...");
    }

    public void init(){
        System.out.println("car ... init...");
    }

    public void destroy(){
        System.out.println("car ... destroy...");
    }

}
