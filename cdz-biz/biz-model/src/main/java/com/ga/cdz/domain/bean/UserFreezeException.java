package com.ga.cdz.domain.bean;

import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: 用户冻结异常
 * @date:2018/9/11_17:18
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class UserFreezeException extends BaseException {
    public UserFreezeException() {
        super(ResultEnum.USER_FREEZE);
    }
}
