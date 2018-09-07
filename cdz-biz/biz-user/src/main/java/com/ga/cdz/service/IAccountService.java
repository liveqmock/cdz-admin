package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;

/**
 * @author:luqi
 * @description: 登录和认证
 * @date:2018/9/7_12:36
 */
public interface IAccountService extends IService<UserInfo> {

    /**
     * @author:luqi
     * @description: 注册发送验证码
     * @date:2018/9/7_13:25
     * @param: 发送验证码Vo
     * @return: 是否发送成功
     */
    String registerSendSms(UserInfoSendSmsVo userInfoSendSmsVo);


    /**
     * @author:luqi
     * @description: 注册
     * @date:2018/9/7_13:19
     * @param: UserInfoRegisterVo
     * @return: 返回是否注册成功
     */
    boolean register(UserInfoRegisterVo registerVo);



    /**
     * @author:luqi
     * @description: 登陆
     * @date:2018/9/7_12:55
     * @param: UserInfoVo
     * @return: UserLoginDTO
     */
    UserLoginDTO login(UserInfoVo userInfoVo);

}
