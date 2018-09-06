package com.ga.cdz.controller.account;


import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.ValidException;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.group.admin.IMAdminInfoGroup;
import com.ga.cdz.service.IMAdminInfoService;
import com.ga.cdz.service.IUserService;
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


    /**管理员信息服务层*/
    @Resource
    IMAdminInfoService adminInfoService;


    /**用户信息服务层*/
    @Resource
    IUserService userService;




    @GetMapping("/test/user")
    public UserInfo testUserInfo(){
        return userService.getUserInfoById(1L);
    }


    @PostMapping("/rsa")
    public UserInfo testRSA(@RequestBody @Validated(value = {IMAdminInfoGroup.Add.class}) UserInfo userInfo, BindingResult result){
        checkParams(result);
        //adminInfoService.sav
        return userInfo;
    }

}
