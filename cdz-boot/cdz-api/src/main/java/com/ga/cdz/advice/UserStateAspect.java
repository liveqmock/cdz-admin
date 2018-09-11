package com.ga.cdz.advice;


import com.ga.cdz.domain.bean.UserFreezeException;
import com.ga.cdz.service.IUserService;
import org.apache.shiro.SecurityUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class UserStateAspect {

    @Resource
    IUserService userService;


    @Pointcut(value = "@annotation(com.ga.cdz.annotation.UserState)")
    public void access() {
    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        String tel = (String) SecurityUtils.getSubject().getPrincipal();
        boolean isFreeze = userService.isUserFreeze(tel);
        if (isFreeze) {
            //被冻结了，抛出异常
            throw new UserFreezeException();
        }
        //没有冻结继续执行
    }


}
