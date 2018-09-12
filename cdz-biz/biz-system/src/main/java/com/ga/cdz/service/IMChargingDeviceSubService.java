package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingDeviceSub;

import java.util.List;

/**
 * @author:luqi
 * @description: 充电桩枪接口
 * @date:2018/9/11_20:12
 */
public interface IMChargingDeviceSubService extends IService<ChargingDeviceSub> {

    void getRedisListAll();

}
