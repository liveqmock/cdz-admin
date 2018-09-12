package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站列表mapper接口
 * @date:2018/9/10 15:04
 */
public interface ChargingStationMapper extends SuperMapper<ChargingStation> {
    List<ChargingStationDTO> getStationList(Page<ChargingStationDTO> page);
}
