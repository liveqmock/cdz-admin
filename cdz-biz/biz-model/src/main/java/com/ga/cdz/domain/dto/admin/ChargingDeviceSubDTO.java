package com.ga.cdz.domain.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingDeviceSub;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author:hanghaohoa
 * @description: 充电枪DTO
 * @date:2018/9/14 14:03
 */
@Data
@Accessors(chain = true)
public class ChargingDeviceSubDTO {

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
    private ChargingDeviceSub.DeviceSubState deviceSubState;

    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 充电开始时间
     */
    private Date charginBeginDt;

    /**
     * 用户名称
     */
    private String userRealName;
    /**
     * 用户ID
     */
    private Integer userId;
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
