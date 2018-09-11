package com.ga.cdz.controller.account;


import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import com.ga.cdz.domain.group.api.IUserSmsPushGroup;
import com.ga.cdz.domain.vo.api.UserInfoLoginVo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoRetrieverVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.domain.vo.base.UserSmsPushVo;
import com.ga.cdz.service.IAccountService;
import com.ga.cdz.util.MPushUtil;
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

    @Resource
    private MPushUtil mPushUtil;

    /**
     * @author:luqi
     * @description: 发送验证码
     * @date:2018/9/7_13:36
     * @param: UserInfoSendSmsVo 传手机号
     * @return: Result
     */
    @PostMapping("/register/send/sms")
    public Result registerSendSms(@RequestBody @Validated(value = {IUserInfoGroup.SendSms.class}) UserInfoSendSmsVo userInfoSendSmsVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.registerSendSms(userInfoSendSmsVo);
        return Result.success().message("发送成功");
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
     * @description: 注册回调 (暂时不用)
     * @date:2018/9/11_9:35
     * @param: UserSmsPushVo vo
     * @return: Result
     */
    @Deprecated
    @PostMapping("/register/callback")
    public Result registerCallBack(@RequestBody @Validated({IUserSmsPushGroup.Register.class}) UserSmsPushVo userSmsPushVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.registerCallBack(userSmsPushVo);
        return Result.success().message("操作成功");
    }

    /**
     * @author: liuyi
     * @description:
     * @date: 2018/9/8_16:34
     * @param: retrieverVo 找回密码的Vo对象
     * @param: bindingResult 验证对象
     * @return: Result
     */
    @PostMapping("/retriever")
    public Result retriever(@RequestBody @Validated({IUserInfoGroup.Retriever.class})UserInfoRetrieverVo retrieverVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.retriever(retrieverVo);
        return Result.success().message("找回密码成功");
    }

    /**
     * @author: liuyi
     * @description: 找回密码发送验证码
     * @date: 2018/9/7_17:43
     * @param: UserInfoSendSmsVo 传手机号
     * @return: Result
     */
    @PostMapping("/retrieve/send/sms")
    public Result retrieveSendSms(@RequestBody @Validated(value = {IUserInfoGroup.SendSms.class}) UserInfoSendSmsVo userInfoSendSmsVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        accountService.retrieveSendSms(userInfoSendSmsVo);
        return Result.success().message("发送成功");
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
        UserLoginDTO userLoginDTO = accountService.login(userInfoLoginVo);
        return Result.success().message("登录成功").data(userLoginDTO);
    }

    /**
     * @author:luqi
     * @description: 激光测试方法
     * @date:2018/9/11_11:32
     * @param:
     * @return:
     */
    @GetMapping("/jpush/test")
    public Result jpushAliasDefault() {
        try {
            mPushUtil.sendAlias("DEFAULT", "来自接口的消息");
        } catch (Exception e) {
            return Result.fail().message("推送失败");
        }

        return Result.success().message("消息推送成功");
    }


    @GetMapping("/jpush/tag")
    public Result jpushTagDefault() {
        try {
            mPushUtil.sendTag("ddd", "来自TAG的消息");
        } catch (Exception e) {
            return Result.fail().message("推送失败");
        }

        return Result.success().message("消息推送成功");
    }
}
