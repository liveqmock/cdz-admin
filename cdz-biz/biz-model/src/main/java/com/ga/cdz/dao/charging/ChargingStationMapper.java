package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.ChargingStation;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站列表mapper接口
 * @date:2018/9/10 15:04
 */
public interface ChargingStationMapper extends SuperMapper<ChargingStation> {
    List<ChargingStationDTO> getStationList();
}
