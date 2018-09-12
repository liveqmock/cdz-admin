package com.ga.cdz.domain.redis;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ChargingDeviceSubRD {

    /**
     * 枪编号
     */
    private Integer deviceSubId;

    /**
     * 充电桩ID
     */
    private Integer deviceId;

    /**
     * 枪名称
     */
    private String deviceSubName;

    /**
     * 0故障，1空闲，2占用
     */
    private Integer deviceSubState;

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
