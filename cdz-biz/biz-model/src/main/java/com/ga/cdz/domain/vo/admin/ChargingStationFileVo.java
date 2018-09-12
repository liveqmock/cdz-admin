package com.ga.cdz.domain.vo.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.group.admin.IMChargingStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalTime stationOpendt;
    /**
     * 充电站关闭时间
     */
    private LocalTime stationClosedt;
    /**
     * 更新时间
     */
    private LocalDateTime updateDt;
    /**
     * 插入时间
     */
    private LocalDateTime insertDt;

}
