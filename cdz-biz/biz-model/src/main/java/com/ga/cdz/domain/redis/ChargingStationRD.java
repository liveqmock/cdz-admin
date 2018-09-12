package com.ga.cdz.domain.redis;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author:luqi
 * @description: chargingStation
 * @date:2018/9/12_18:20
 */
@Data
@Accessors(chain = true)
public class ChargingStationRD {

    /**
     * 充电站ID
     */
    private Integer stationId;
    /**
     * 商户ID
     */
    private Integer shopId;
    /**
     * 站编码
     */
    private String stationCode;
    /**
     * 站名称
     */
    private String stationName;
    /**
     * 充电站类型 1对外开放 2不对外开放
     */
    private Integer stationType;
    /**
     * 运营商类型ID
     */
    private Integer sttpeId;
    /**
     * 运营商ID
     */
    private Integer operatorsId;
    /**
     * 设备数
     */
    private Integer deviceNum;
    /**
     * 维度
     */
    private Double lat;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 充电站状态 0 删除 1 正常
     */
    private Integer stationState;
    /**
     * 充电站开放时间
     */
    private Date stationOpendt;
    /**
     * 充电站关闭时间
     */
    private Date stationClosedt;
    /**
     * 更新时间
     */
    private Date updateDt;
    /**
     * 插入时间
     */
    private Date insertDt;

}
