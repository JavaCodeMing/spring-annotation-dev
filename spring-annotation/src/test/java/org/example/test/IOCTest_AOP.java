package org.example.test;

import org.example.aop.config.MainConfig;
import org.example.aop.service.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: dengzm
 * @date: 2021-07-21 20:49:24
 */
public class IOCTest_AOP {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        //1.不要自己创建对象(只有由容器管理的类才能使用上代理)
        // MathCalculator mathCalculator = new MathCalculator();
        // mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(1, 1);
        applicationContext.close();
    }
}
