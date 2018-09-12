package com.ga.cdz.domain.redis;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ChargingDeviceRD {
    /**
     * 充电桩ID
     */
    private Integer deviceId;

    private Integer stationId;
    /**
     * 充电桩编码
     */
    private String deviceCode;
    /**
     * 充电桩名称
     */
    private String deviceName;
    /**
     * 充电桩充电方式
     */
    private Integer cgtypeId;
    /**
     * 设备功率
     */
    private Integer devicePower;
    /**
     * 设备枪个数
     */
    private Integer deviceSubnum;

    private Integer deviceState;
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
