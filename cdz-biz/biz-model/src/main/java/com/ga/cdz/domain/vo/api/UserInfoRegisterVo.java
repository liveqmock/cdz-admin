package com.ga.cdz.domain.vo.api;

import com.ga.cdz.domain.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: 客户端注册Vo
 * @date:2018/9/7_13:00
 */
@Data
@Accessors(chain = true)
public class UserInfoRegisterVo {
    /**
     * 用户真实姓名
     */
    private String userRealName;
    /**
     * 登录的用户名
     */
    private String userName;
    /**
     * 密码MD5加密
     */
    private String userPwd;
    /**
     * 电话
     */
    private String userTel;
    /**
     * 性别 0 女 1男
     */
    private UserInfo.UserSex userSex;
    /**
     * 短信验证码
     */
    private String smsCode;
}
