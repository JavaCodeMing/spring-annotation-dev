package org.example.lifeCycle.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: dengzm
 * @date: 2021-07-05 22:12:57
 */
@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("cat...constructor...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("cat...destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat...afterPropertiesSet...");
    }
}
