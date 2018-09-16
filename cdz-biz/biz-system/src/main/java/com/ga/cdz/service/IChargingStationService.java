package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.ChargingStationPageDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.api.ChargingStationPageVo;

import java.util.List;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/11_15:31
 */
public interface IChargingStationService extends IService<ChargingStation> {

    /**
     * @author: liuyi
     * @description: 获取首页的充电站信息
     * @date: 2018/9/11_15:51
     * @param:
     * @return:
     */
    List<ChargingStationPageDTO> getMainStationPage(ChargingStationPageVo vo);

    /**
     * @author: liuyi
     * @description: 获取附近的充电站信息
     * @date: 2018/9/16_09:40
     * @param:
     * @return:
     */
    List<ChargingStationPageDTO> getNearStationPage(ChargingStationPageVo vo);

}
