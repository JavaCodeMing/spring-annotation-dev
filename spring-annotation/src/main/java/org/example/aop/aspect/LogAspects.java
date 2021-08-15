package org.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author: dengzm
 * @date: 2021-07-21 20:38:01
 */
@Aspect
public class LogAspects {
    //抽取公共的切入点表达式
    //1、本类引用: pointCut()
    //2、其他的切面引用: org.example.aop.aspect.pointCut()
    @Pointcut("execution(public int org.example.aop.service.MathCalculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是: {" + Arrays.asList(args) + "}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "结束。。。@After");
    }

    //JoinPoint一定要出现在参数表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常返回。。。@AfterReturning:运行结果：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "异常。。。异常信息：{" + exception + "}");
    }
}
