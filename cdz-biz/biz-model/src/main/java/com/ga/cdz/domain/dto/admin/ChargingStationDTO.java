package com.ga.cdz.domain.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingStation;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author:wanzhongsu
 * @description: 充电站返回实体
 * @date:2018/9/11 20:07
 */
@Data
@Accessors(chain = true)
public class ChargingStationDTO {

    /**
     * 充电站ID
     */
    private Integer stationId;
    /**
     * 商户编码
     */
    private String shopCode;
    /**
     * 商户名称
     */
    private String shopName;
    /**
     * 运营商类型名称
     */
    private String sttpeName;
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
    private ChargingStation.StationType stationType;
    /**
     * 维度
     */
    private Double lat;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;
}
