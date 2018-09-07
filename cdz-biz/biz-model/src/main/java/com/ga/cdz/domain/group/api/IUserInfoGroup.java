package com.ga.cdz.domain.group.api;

/**
 * @author:luqi
 * @description: 前台客户端用户规则组
 * @date:2018/9/5_11:48
 */
public interface IUserInfoGroup {

    /**
     * @author:luqi
     * @description: 登录或找回密码发送短信规则组
     * @date:2018/9/5_11:49
     */
    interface SendSms {
    }

    /**
     * @author:luqi
     * @description: 注册规则组
     * @date:2018/9/5_11:49
     */
    interface Register {
    }

    /**
     * @author:luqi
     * @description: 登陆规则组
     * @date:2018/9/5_11:49
     */
    interface Login{}


}
