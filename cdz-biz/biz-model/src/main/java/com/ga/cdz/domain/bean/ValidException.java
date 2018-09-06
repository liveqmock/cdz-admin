package com.ga.cdz.domain.bean;

import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author:luqi
 * @description: 参数验证异常
 * @date:2018/9/4_15:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ValidException extends BaseException {
    private String detail;

    public ValidException(String detail) {
        super(ResultEnum.PARAM_VALID_ERROR);
        this.detail = detail;
    }
}
