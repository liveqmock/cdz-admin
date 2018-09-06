package com.ga.cdz.controller.account;

import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.service.IMAccountService;
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
public class AccountController {

    @Resource
    IMAccountService accountService;

    /**
     * @author:luqi
     * @description: 添加管理员
     * @date:2018/9/5_16:55
     * @param: AdminInfoVo
     * @return: Result
     */
        public Result login(){
            return null;
        }

}
