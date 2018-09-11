package com.ga.cdz.annotation;

import java.lang.annotation.*;

/**
 * @author:luqi
 * @description: 用户状态
 * @date:2018/9/11_16:20
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UserState {
}
