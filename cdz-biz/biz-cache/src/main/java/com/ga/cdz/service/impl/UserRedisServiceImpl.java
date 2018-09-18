package com.ga.cdz.service.impl;

import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.service.IUserRedisService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuyi
 * @Description: 用户缓存
 * @Date: 2018/9/18_15:15
 */
@Slf4j
@Service("userRedisService")
public class UserRedisServiceImpl implements IUserRedisService {

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public void cacheUserList() {
        cacheUserInfo();
    }

    /**
     * @Author: liuyi
     * @Description: 缓存用户信息
     * @Date: 2018/9/18_15:23
     */
    private void cacheUserInfo() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_USER_INFO)) {
            List<UserInfo> list = userInfoMapper.selectList(null);
            Map<String, UserInfo> userInfoMapMap = Maps.newHashMap();
            for (UserInfo user : list) {
                userInfoMapMap.put(user.getUserId() + "", user);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_USER_INFO, userInfoMapMap);
            log.info("TABLE_USER_INFO缓存成功");
        }
    }

    @Override
    public void cacheOneUserInfo(UserInfo userInfo) {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_USER_INFO)) {
            cacheUserInfo();
        } else {
            mRedisUtil.putHash(RedisConstant.TABLE_USER_INFO, userInfo.getUserId().toString(), userInfo);
        }
    }
}
