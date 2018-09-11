package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.UserInfoLoginVo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoRetrieverVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.domain.vo.base.UserSmsPushVo;

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
     */
    void registerSendSms(UserInfoSendSmsVo userInfoSendSmsVo);


    /**
     * @author: liuyi
     * @description: 找回密码发送验证码
     * @date: 2018/9/7_16:39
     * @param: 发送验证码Vo
     */
    void retrieveSendSms(UserInfoSendSmsVo userInfoSendSmsVo);

    /**
     * @author:luqi
     * @description: 注册
     * @date:2018/9/7_13:19
     * @param: UserInfoRegisterVo
     * @return:
     */
    void register(UserInfoRegisterVo registerVo);


    /**
     * @author:luqi
     * @description: 注册之后的回调, 上传机关推送相关信息
     * @date:2018/9/11_9:02
     * @param: UserSmsPushVo
     * @return:
     */
    @Deprecated
    void registerCallBack(UserSmsPushVo userSmsPushVo);

    /**
     * @author: liuyi
     * @description: 找回密码
     * @date: 2018/9/8_16:25
     * @param: UserInfoRetrieverVo
     */
    void retriever(UserInfoRetrieverVo retrieverVo);

    /**
     * @author:luqi
     * @description: 登陆
     * @date:2018/9/7_12:55
     * @param: UserInfoVo
     * @return: UserLoginDTO
     */
    UserLoginDTO login(UserInfoLoginVo userInfoLoginVo);


}
