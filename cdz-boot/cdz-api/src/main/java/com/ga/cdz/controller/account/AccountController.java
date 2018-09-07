package com.ga.cdz.controller.account;


import com.ga.cdz.controller.AbstractBaseController;

import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;
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
     * @description: 登陆
     * @date:2018/9/7_12:57
     * @param: UserInfoVo 传用户名与密码
     * @return:
     */

    @PostMapping("/login")
    public Result login(@RequestBody @Validated({IUserInfoGroup.Login.class}) UserInfoRegisterVo registerVo, BindingResult bindingResult) {
        return null;
    }

}
