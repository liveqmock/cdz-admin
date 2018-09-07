package com.ga.cdz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingStationType;
import com.ga.cdz.domain.vo.base.ChargingStationTypeVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
 */
public interface IMChargingStationTypeService extends IService<ChargingStationType> {
    List<ChargingStationType> getCSTList();

    Integer removeCSTById(ChargingStationTypeVo vo);

    Integer saveCST(ChargingStationTypeVo vo);
}
