package com.ga.cdz.annotation;

import java.lang.annotation.*;

/**
 * @author:luqi
 * @description: 不需要解密
 * @date:2018/9/4_15:02
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UnDecrypt {
}
