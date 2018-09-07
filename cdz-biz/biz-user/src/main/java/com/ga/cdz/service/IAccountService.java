package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.base.UserInfoVo;

/**
 * @author:luqi
 * @description: 登录和认证
 * @date:2018/9/7_12:36
 */
public interface IAccountService extends IService<UserInfo> {

    /**
     * @author:luqi
     * @description: 登陆
     * @date:2018/9/7_12:55
     * @param: UserInfoVo
     * @return: UserLoginDTO
     */
    UserLoginDTO login(UserInfoVo userInfoVo);

}
