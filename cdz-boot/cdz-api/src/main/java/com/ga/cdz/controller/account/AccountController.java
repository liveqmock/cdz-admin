package com.ga.cdz.controller.account;


import com.ga.cdz.controller.AbstractBaseController;

import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import com.ga.cdz.domain.vo.api.UserInfoLoginVo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.service.IAccountService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description:
 * @date:2018/9/5_10:52
 */
@RestController
@RequestMapping("/account")
public class AccountController extends AbstractBaseController {

    @Resource
    private IAccountService accountService;

    /**
     * @author:luqi
     * @description: 发送验证码
     * @date:2018/9/7_13:36
     * @param: UserInfoSendSmsVo 传手机号
     */
    @PostMapping("/register/send/sms")
    public void registerSendSms(@RequestBody @Validated(value = {IUserInfoGroup.SendSms.class}) UserInfoSendSmsVo userInfoSendSmsVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.registerSendSms(userInfoSendSmsVo);
    }

    /**
     * @author:luqi
     * @description: 注册
     * @date:2018/9/7_15:08
     * @param: registerVo 注册的Vo对象
     * @param: bindingResult 验证对象
     * @return: Result
     */
    @PostMapping("/register")
    public Result register(@RequestBody @Validated({IUserInfoGroup.Register.class}) UserInfoRegisterVo registerVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.register(registerVo);
        return Result.success().message("注册成功");
    }

    /**
     * @author:luqi
     * @description: 登陆
     * @date:2018/9/7_12:57
     * @param: UserInfoVo 传用户名与密码
     * @return:
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Validated({IUserInfoGroup.Login.class}) UserInfoLoginVo userInfoLoginVo,
                        BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.login(userInfoLoginVo);
        return null;
    }

}
