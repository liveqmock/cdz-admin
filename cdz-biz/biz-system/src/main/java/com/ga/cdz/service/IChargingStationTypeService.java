package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingStationType;

import java.util.List;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/11_15:43
 */
public interface IChargingStationTypeService extends IService<ChargingStationType> {

    /**
     * @author: liuyi
     * @description: 获取充电站运营类型列表
     * @date: 2018/9/11_15:51
     * @param:
     * @return:
     */
    List<ChargingStationType> getCSTList();

}
