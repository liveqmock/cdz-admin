package com.ga.cdz.domain.bean;


import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author:luqi
 * @description: 常见的业务逻辑异常
 * @date:2018/9/4_15:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BusinessException extends BaseException {
    private String detail;

    public BusinessException(String detail) {
        super(ResultEnum.FAIL);
        this.detail = detail;
    }
}
