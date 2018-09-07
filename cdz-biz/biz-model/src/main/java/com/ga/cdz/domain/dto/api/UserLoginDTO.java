package com.ga.cdz.domain.dto.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: 登陆认证返回dto
 * @date:2018/9/7_12:38
 */
@Data
@Accessors(chain = true)
public class UserLoginDTO {

    /**
     * userId
     **/
    private Integer userId;

    /***用户电话**/
    private String userTel;

    /***token**/
    private String token;

}
