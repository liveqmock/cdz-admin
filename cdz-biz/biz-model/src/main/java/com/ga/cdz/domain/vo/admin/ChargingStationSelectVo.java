package com.ga.cdz.domain.vo.admin;

import com.ga.cdz.domain.entity.ChargingStation;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author:wanzhongsu
 * @description: 充电站列表vo
 * @date:2018/9/10 15:39
 */
@Data
@Accessors(chain = true)
public class ChargingStationSelectVo {
    /**
     * 站名称
     */
    private String stationName;
    /**
     * 充电站类型 1对外开放 2不对外开放
     */
    private ChargingStation.StationType stationType;
    /**
     * 运营商类型名称
     */
    private String sttpeName;
    /**
     * 商户名称
     */
    private String shopName;
}
