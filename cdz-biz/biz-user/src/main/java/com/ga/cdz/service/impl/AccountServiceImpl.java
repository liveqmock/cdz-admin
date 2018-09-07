package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.base.UserInfoVo;
import com.ga.cdz.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IAccountService {

    @Override
    public UserLoginDTO login(UserInfoVo userInfoVo) {
        return null;
    }
}
