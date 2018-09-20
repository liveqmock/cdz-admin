package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingDeviceDTO;
import com.ga.cdz.domain.entity.ChargingDevice;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-11 15:01
 * @desc 充电桩Service
 */
public interface IMChargingDeviceService extends IService<ChargingDevice> {
    /**
     * @author huanghaohao
     * @date 2018-09-11 15:10
     * @desc 插入新的充电桩
     */
    Integer insertNewChargingDevice(ChargingDeviceVo chargingDeviceVo);

    /**
     * @param chargingDeviceVo
     * @return
     * @author huanghaohao
     * @date 2018-09-11 15:42
     * @desc 查询充电桩列表
     */
    List<ChargingDeviceDTO> getChargingDeviceList(ChargingDeviceVo chargingDeviceVo);

}
