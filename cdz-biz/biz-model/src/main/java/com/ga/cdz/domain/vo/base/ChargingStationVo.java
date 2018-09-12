package com.ga.cdz.domain.vo.base;

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
public class ChargingStationVo {

    /**
     * 充电站ID
     */
    @NotNull(groups = {IMChargingStationGroup.Delete.class, IMChargingStationGroup.Update.class}, message = "充电站ID不能为空")
    private Integer stationId;
    /**
     * 商户ID
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "商户ID不能为空")
    private Integer shopId;
    /**
     * 站编码
     */
    @NotBlank(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "站编码不能为空")
    @Pattern(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, regexp = RegexConstant.STATION_CODE, message = "站编码格式不对")
    private String stationCode;
    /**
     * 站名称
     */
    @NotBlank(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "站名称不能为空")
    private String stationName;
    /**
     * 充电站类型 1对外开放 2不对外开放
     */
    private ChargingStation.StationType stationType;
    /**
     * 运营商类型ID
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "运营商类型ID不能为空")
    private Integer sttpeId;
    /**
     * 运营商ID
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "运营商ID不能为空")
    @TableField("operators_id")
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
    private ChargingStation.StationState stationState;
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
