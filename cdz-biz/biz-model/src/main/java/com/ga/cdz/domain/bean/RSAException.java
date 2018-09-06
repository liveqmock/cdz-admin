package com.ga.cdz.domain.bean;


import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: RSA异常
 * @date:2018/9/4_15:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RSAException extends BaseException {

    public RSAException() {
        super(ResultEnum.RSA_ERROR);
    }
}
