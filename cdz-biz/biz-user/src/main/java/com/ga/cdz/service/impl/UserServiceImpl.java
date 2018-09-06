package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description: 用户接口具体实现类
 * @date:2018/9/4_11:02
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements IUserService {

    @Override
    public UserInfo getUserInfoById(Long id) {
        return getById(id);
    }
}
