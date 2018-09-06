package com.ga.cdz.controller;

import com.ga.cdz.domain.bean.ValidException;
import org.springframework.validation.BindingResult;

/**
 * @author:luqi
 * @description: controller 抽象基类 这里可以写一些公用的方法
 * @date:2018/9/5_11:11
 */
public abstract class AbstractBaseController  {

     /**
      * @author:luqi
      * @description: 参数检查，如果异常会抛出参数异常
      * @date:2018/9/5_11:13
      * @param:
      * @return:
      */
    protected void checkParams(BindingResult result){
        if (result.hasErrors()) {
            throw new ValidException(result.getFieldError().getDefaultMessage());
        }
    }

}
