package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
import com.ga.cdz.domain.entity.ChargingShop;

import java.util.List;
import java.util.Map;

/**
 * @author:wanzhongsu
 * @description: 商户管理表mapper
 * @date:2018/9/10 12:37
 */
public interface ChargingShopMapper extends BaseMapper<ChargingShop> {
    List<ChargingShopDTO> getShopList(Page<ChargingShopDTO> page);
}
