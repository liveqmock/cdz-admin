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
     * 充电桩详细地址
     */
    private String stationAddr;

    /**
     * 充电桩省编码
     */
    private Integer province;

    /**
     * 充电桩市编码
     */
    private Integer city;
    /**
     * 充电站市名称
     */
    private String scity;
    /**
     * 充电站乡街道名称
     */
    private String scountry;
    /**
     * 充电柱省名称
     */
    private String sprovince;
    /**
     * 充电桩县名称
     */
    private String scounty;
    /**
     * 充电桩区编码
     */
    private Integer county;
    /**
     * 乡镇（街道）编码
     */
    private Integer country;
    /**
     * 维度
     */
    private Double lat;
    /**
     * 设备桩数
     */
    private Integer deviceNums;
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
