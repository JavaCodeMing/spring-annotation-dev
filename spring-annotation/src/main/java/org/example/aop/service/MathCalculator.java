package org.example.aop.service;

/**
 * @author: dengzm
 * @date: 2021-07-21 20:40:46
 */
public class MathCalculator {
    public int div(int i,int j){
        System.out.println("MathCalculator...div...");
        return i/j;
    }
}
