package com.ga.cdz.domain.bean;

import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: 异常基类
 * @date:2018/9/4_15:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public abstract class BaseException extends RuntimeException {
    private Integer code;

    public BaseException() {
    }

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}