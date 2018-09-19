package com.ga.cdz.service;

import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.entity.ChargingOrderComment;

/**
 * @author:luqi
 * @description: 充电桩相关缓存接口
 * @date:2018/9/13_16:12
 */
public interface IChargingRedisService {


    /**
     * 缓冲首页与附近充电站列表相关
     */
    void cacheMainAndNearChargingPageList();

    /**
     * @Author: liuyi
     * @Description: 缓存一条订单信息
     * @Date: 2018/9/18_11:03
     */
    void cacheOneChargingOrder(ChargingOrder chargingOrder);

    /**
     * @Author: liuyi
     * @Description: 缓存一条订单评论
     * @Date: 2018/9/18_11:03
     * @param
     * @return
     */
    void cacheOneChargingOrderComment(ChargingOrderComment chargingOrderComment);

}
