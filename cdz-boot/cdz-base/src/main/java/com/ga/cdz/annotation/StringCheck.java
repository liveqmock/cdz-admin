package com.ga.cdz.annotation;

import java.lang.annotation.*;

/**
 * @author:luqi
 * @description: 字符串检查，非法字符串，以及sql注入检测,xss
 * @date:2018/9/19_10:09
 */
@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface StringCheck {

}
