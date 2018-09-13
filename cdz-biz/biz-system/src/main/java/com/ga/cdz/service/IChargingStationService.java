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
     * @description: 分页获取充电站信息
     * @date: 2018/9/11_15:51
     * @param:
     * @return:
     */
    List<ChargingStationPageDTO> getStationPage(ChargingStationPageVo vo);

}
