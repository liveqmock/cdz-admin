package com.ga.cdz.domain.vo.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 充电站列表vo
 * @date:2018/9/10 15:39
 */
@Data
@Accessors(chain = true)
public class ChargingStationFileVo {

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
     * 充电桩区编码
     */
    private Integer county;
    /**
     * 乡镇（街道）编码
     */
    private Integer country;
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
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationOpendt;
    /**
     * 充电站关闭时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationClosedt;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDt;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

}
