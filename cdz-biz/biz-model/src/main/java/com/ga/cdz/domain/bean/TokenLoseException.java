package com.ga.cdz.domain.bean;

import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: token 失效异常
 * @date:2018/9/4_15:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class TokenLoseException extends BaseException {
    public TokenLoseException() {
        super(ResultEnum.TOKEN_LOSE_ERROR);
    }

}
