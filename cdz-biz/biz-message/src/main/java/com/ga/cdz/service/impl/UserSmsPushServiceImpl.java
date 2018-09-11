package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserSmsPushMapper;
import com.ga.cdz.domain.entity.UserSmsPush;
import com.ga.cdz.service.IUserSmsPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userSmsPushService")
public class UserSmsPushServiceImpl extends ServiceImpl<UserSmsPushMapper, UserSmsPush> implements IUserSmsPushService {

    @Override
    public boolean registerCallBack() {
        return false;
    }

}
