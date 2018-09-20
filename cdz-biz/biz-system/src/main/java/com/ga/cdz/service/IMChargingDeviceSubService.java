package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingDeviceSubDTO;
import com.ga.cdz.domain.entity.ChargingDeviceSub;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;

import java.util.List;

/**
 * @author:luqi
 * @description: 充电桩枪接口
 * @date:2018/9/11_20:12
 */
public interface IMChargingDeviceSubService extends IService<ChargingDeviceSub> {


    /**
     * @param chargingDeviceVo
     * @return
     * @author huanghaohao
     * @date 2018-09-12 11:29
     * @desc 获取充电枪列表
     */
    List<ChargingDeviceSubDTO> getChargingDeviceSubList(ChargingDeviceVo chargingDeviceVo);


}
