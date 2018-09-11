package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.UserSmsPush;

/**
 * @author:luqi
 * @description: 客户端推送接口
 * @date:2018/9/10_16:48
 */
public interface IUserSmsPushService extends IService<UserSmsPush> {

    /**
     * @author:luqi
     * @description: 注册成功后回调，上传rid alias tag（设备型号）
     * @date:2018/9/10_16:49
     * @param:
     * @return:
     */
    boolean registerCallBack();
}
