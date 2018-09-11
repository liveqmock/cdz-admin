package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserSmsPushMapper;
import com.ga.cdz.domain.entity.UserSmsPush;
import com.ga.cdz.service.IMUserSmsPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author:luqi
 * @description: 后台用户推送 实现类
 * @date:2018/9/10_16:43
 */
@Slf4j
@Service("/mUserSmsPushService")
public class MUserSmsPushServiceImpl extends ServiceImpl<UserSmsPushMapper, UserSmsPush> implements IMUserSmsPushService {
}
