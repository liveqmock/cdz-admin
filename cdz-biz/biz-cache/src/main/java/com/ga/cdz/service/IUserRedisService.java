package com.ga.cdz.service;

import com.ga.cdz.domain.entity.UserInfo;

/**
 * @Author: liuyi
 * @Description: 用户缓存接口
 * @Date: 2018/9/18_15:17
 */
public interface IUserRedisService {

    /**
     * @Author: liuyi
     * @Description: 缓存与用户相关的信息
     * @Date: 2018/9/18_15:17
     */
    void cacheUserList();

    /**
     * @Author: liuyi
     * @Description: 缓存一条用户信息
     * @Date: 2018/9/18_15:25
     * @param userInfo 用户信息
     * @return
     */
    void cacheOneUserInfo(UserInfo userInfo);

}
