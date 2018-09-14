package com.ga.cdz.domain.vo.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.group.admin.IMChargingStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Time;
import java.util.Date;

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
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩开放类型不能为空")
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
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩设备数不能为空")
    private Integer deviceNum;
    /**
     * 维度
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩纬度不能为空")
    private Double lat;
    /**
     * 经度
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩经度不能为空")
    private Double lng;
    /**
     * 充电站状态 0 删除 1 正常
     */
    private ChargingStation.StationState stationState;
    /**
     * 充电站开放时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationOpendt;
    /**
     * 充电站关闭时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationClosedt;

    /**
     * 充电桩详细地址
     */
    @NotBlank(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩地址不能为空")
    private String stationAddr;

    /**
     * 充电桩省编码
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩省编码不能为空")
    private Integer province;

    /**
     * 充电桩市编码
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩市编码不能为空")
    private Integer city;

    /**
     * 充电桩区编码
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩县编码不能为空")
    private Integer county;
    /**
     * 乡镇（街道）编码
     */
    @NotNull(groups = {IMChargingStationGroup.Add.class, IMChargingStationGroup.Update.class}, message = "充电桩镇编码不能为空")
    private Integer country;
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
