package com.ga.cdz.domain.vo.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ga.cdz.domain.entity.ChargingDevice;
import com.ga.cdz.domain.group.admin.IMChargingDeviceGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author huanghaohao
 * @date 2018-09-11 14:16
 * @desc 充电桩Vo
 */
@Data
@Accessors(chain = true)
public class ChargingDeviceVo {

    /**
     * 充电桩ID
     */
    @NotNull(groups = {IMChargingDeviceGroup.subList.class}, message = "桩Id 不能为空")
    private Integer deviceId;

    /**
     * 充电站ID
     */
    @NotNull(groups = {IMChargingDeviceGroup.insert.class, IMChargingDeviceGroup.list.class}, message = "站ID 不能为空")
    private Integer stationId;

    /**
     * 充电桩编码
     */
    @NotNull(groups = {IMChargingDeviceGroup.insert.class}, message = " 充电桩编码不能为空")
    private String deviceCode;

    /**
     * 充电桩名称
     */
    @NotNull(groups = {IMChargingDeviceGroup.insert.class}, message = "充电桩名称 不能为空")
    private String deviceName;

    /**
     * 充电桩充电方式
     */
    @NotNull(groups = {IMChargingDeviceGroup.insert.class,}, message = "充电方式 不能为空")
    private Integer cgtypeId;

    /**
     * 设备功率
     */
    private Integer devicePower;
    /**
     * 设备最大电压
     */
    private Integer deviceVoltage;
    /**
     * 设备枪个数
     */
    private Integer deviceSubnum;
    /**
     * 设备状态
     */
    private ChargingDevice.DeviceState deviceState;

    /**
     * 更新时间
     */
    private Date updateDt;

    /**
     * 插入时间
     */
    private Date insertDt;
}
