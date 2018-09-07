package com.ga.cdz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
 */
public interface IMChargingTypeService extends IService<ChargingType> {
    List<ChargingType> getChargingTypeList();

    Integer removeChargingTypeById(ChargingTypeVo vo);

    Integer saveChargingTypeObj(ChargingTypeVo vo);

}
