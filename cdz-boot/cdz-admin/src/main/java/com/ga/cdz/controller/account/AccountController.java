package com.ga.cdz.controller.account;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.AdminLoginDTO;
import com.ga.cdz.domain.group.admin.IMAdminInfoGroup;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.service.IMAccountService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author:luqi
 * @description: 认证控制层
 * @date:2018/9/5_16:17
 */
@RestController
@RequestMapping("/account")
public class AccountController extends AbstractBaseController {

    @Resource
    IMAccountService mAccountService;
    /**
     * @author:luqi
     * @description: 添加管理员
     * @date:2018/9/5_16:55
     * @param: AdminInfoVo
     * @return: Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Validated({IMAdminInfoGroup.Login.class}) AdminInfoVo adminInfoVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        AdminLoginDTO adminLoginDTO = mAccountService.login(adminInfoVo);
        return Result.success().message("登录成功").data(adminLoginDTO);
    }

}
