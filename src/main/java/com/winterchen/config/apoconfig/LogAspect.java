package com.winterchen.config.apoconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * 作者：齐文杰
 * 时间：2018/9/16
 */

@Component
@Aspect
public class LogAspect {
    Logger log = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private CounterService counterService;
    @Autowired
    private GaugeService gaugeService;

    @org.aspectj.lang.annotation.Before("logPointcut()")
    public void countServiceInvoke(JoinPoint joinPoint) {
        counterService.increment("meter.method.count." + joinPoint.getSignature().getName());
    }
    /**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 定义在web包或者子包
     * ~ 第三个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.winterchen.controller..*.*(..))")
    public void logPointcut(){}
    @org.aspectj.lang.annotation.Around("logPointcut()")

    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            long duration = end - start;
            gaugeService.submit("method.gauge."+joinPoint.getSignature().getName(), duration);
            log.info("【调用方法】 " + joinPoint + "\t【耗时】 : " + duration + " ms!");
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.info("【调用方法】 " + joinPoint + "\t【耗时】 : " + (end - start) + " ms with exception : " + e.getMessage());
            throw e;
        }
    }

}