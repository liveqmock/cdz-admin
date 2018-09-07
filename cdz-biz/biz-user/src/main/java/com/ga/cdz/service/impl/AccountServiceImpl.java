package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;
import com.ga.cdz.service.IAccountService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description: 客户端认证与登录实现类
 * @date:2018/9/7_13:26
 */
@Slf4j
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IAccountService {

    /**
     * redis工具类
     **/
    @Resource
    MRedisUtil mRedisUtil;

    /**
     * sms工具类
     **/
    @Resource
    MSmsUtil mSmsUtil;


    @Override
    public String registerSendSms(UserInfoSendSmsVo userInfoSendSmsVo) {
        //结果，结果为空字符串代表短信发送成功，如果有错误，把错误提示放在rs上
        String rs = "";
        String userTel = userInfoSendSmsVo.getUserTel();
        String smsRedisKey = RedisConstant.USER_REGISTER_SMS + userTel;
        /**判断用户是否已经发送过短信**/
        /**调用 sms 工具类发送短信，并判断短信是否发送成功，可参照以前项目**/
        /**发送成功后把短信存入到redis 中 设置 其时效性 **/
        return rs;
    }

    @Override
    public boolean register(UserInfoRegisterVo registerVo) {
        return false;
    }

    @Override
    public UserLoginDTO login(UserInfoVo userInfoVo) {
        return null;
    }
}
