package com.ga.cdz.dao.charging;

import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.ChargingDeviceSubDTO;
import com.ga.cdz.domain.entity.ChargingDeviceSub;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2018-09-11
 */
public interface ChargingDeviceSubMapper extends SuperMapper<ChargingDeviceSub> {
  /**
   * @author huanghaohao
   * @date 2018-09-12 10:51
   * @desc 获取充电枪列表
   */
    List<ChargingDeviceSubDTO> getChargingDeviceSubByDeviceId(@Param("device")ChargingDeviceVo chargingDeviceVo);


}
