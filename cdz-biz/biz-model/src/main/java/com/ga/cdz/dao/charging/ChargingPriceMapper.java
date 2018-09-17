package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.dto.admin.ChargingPriceDTO;
import com.ga.cdz.domain.entity.ChargingPrice;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.admin.ChargingPriceSelectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 计费标准mapper
 * @date:2018/9/11 13:17
 */
public interface ChargingPriceMapper extends BaseMapper<ChargingPrice> {
    /**
     * @author:wanzhongsu
     * @description: 根据价格类型查询充电计费商户充电站信息
     * @date: 2018/9/11 13:32
     * @param: Map
     * @return: List
     */
    List<ChargingPriceDTO> getChargingPricePage(Page<ChargingPriceDTO> page, @Param("param") ChargingPriceSelectVo vo, @Param("shop") ChargingShop chargingShop);
}
