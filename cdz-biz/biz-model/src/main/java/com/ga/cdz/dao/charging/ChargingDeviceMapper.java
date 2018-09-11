package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ga.cdz.domain.dto.admin.ChargingDeviceDTO;
import com.ga.cdz.domain.entity.ChargingDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author huanghaohao
 * @date 2018-09-11 14:55
 * @desc 充电桩Mapper
 */
public interface ChargingDeviceMapper extends BaseMapper<ChargingDevice> {
  /**
   * @author huanghaohao
   * @date 2018-09-11 16:13
   * @desc 查询充电桩列表
   * @param chargingDevice
   * @return
   */
  List<ChargingDeviceDTO> getChargingDeviceListDetail(@Param("device") ChargingDevice chargingDevice);
}
