package com.ga.cdz.domain.redis;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ChargingStationAttachRD {
    /**
     * 充电站ID
     */
    private Integer stationId;
    /**
     * 附件编号
     */
    private Integer attachIdx;
    /**
     * 附件图片路径
     */
    private String attachPath;
    /**
     * 附件描述
     */
    private String attachDesc;
    /**
     * 附件状态
     */
    private Integer attachState;
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
