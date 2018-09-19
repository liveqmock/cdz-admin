package com.ga.cdz.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StringCheckAspect {

    @Pointcut(value = "@annotation(com.ga.cdz.annotation.StringCheck)")
    public void access() {
    }


    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {

    }
}
