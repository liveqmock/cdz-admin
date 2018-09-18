package com.ga.cdz.domain.dto.api;

import com.ga.cdz.domain.entity.ChargingDevice;
import com.ga.cdz.domain.entity.ChargingDeviceSub;
import com.ga.cdz.domain.entity.ChargingType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: liuyi
 * @Description: 充电站终端DTO
 * @Date: 2018/9/17_16:03
 */
@Data
@Accessors(chain = true)
public class ChargingStationTerminalDTO {

    /**
     * 充电桩ID
     */
    private Integer deviceId;

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

    /**
     * 设备状态
     */
    private ChargingDevice.DeviceState deviceState;

    /**
     * 充电方式名称
     */
    private String cgtypeName;

    /**
     * 充电模式 1慢 2快
     */
    private ChargingType.CgtypeMode cgtypeMode;

    /**
     * 充电方式编码
     */
    private String cgtypeCode;

    /**
     * 充电方式状态 0删除 1正常
     */
    private ChargingType.CgtypeState cgtypeState;

    /**
     * @Author: liuyi
     * @Description: 赋值ChargingDevice
     * @Date: 2018/9/17_16:32
     * @param chargingDevice
     * @return
     */
    public void setChargingDevice(ChargingDevice chargingDevice) {
        this.deviceId = chargingDevice.getDeviceId();
        this.deviceCode = chargingDevice.getDeviceCode();
        this.deviceName = chargingDevice.getDeviceName();
        this.cgtypeId = chargingDevice.getCgtypeId();
        this.devicePower = chargingDevice.getDevicePower();
        this.deviceSubnum = chargingDevice.getDeviceSubnum();
        this.deviceState = chargingDevice.getDeviceState();
    }

    /**
     * @Author: liuyi
     * @Description: 赋值ChargingType
     * @Date: 2018/9/17_16:33
     * @param chargingType
     * @return
     */
    public void setChargingType(ChargingType chargingType) {
        this.cgtypeName = chargingType.getCgtypeName();
        this.cgtypeMode = chargingType.getCgtypeMode();
        this.cgtypeCode = chargingType.getCgtypeCode();
        this.cgtypeState = chargingType.getCgtypeState();
    }
}
