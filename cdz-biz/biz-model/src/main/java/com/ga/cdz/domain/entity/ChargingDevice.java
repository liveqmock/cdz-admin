package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huanghaohao
 * @date 2018-09-11 14:21
 * @desc 充电桩实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_device")
public class ChargingDevice extends Model<ChargingDevice> {
  private static final long serialVersionUID = 1L;

  /**
   * 充电桩ID
   */
  @TableId(value = "device_id", type = IdType.AUTO)
  private Integer deviceId;

  @TableField("station_id")
  private Integer stationId;
  /**
   * 充电桩编码
   */
  @TableField("device_code")
  private String deviceCode;
  /**
   * 充电桩名称
   */
  @TableField("device_name")
  private String deviceName;
  /**
   * 充电桩充电方式
   */
  @TableField("cgtype_id")
  private Integer cgtypeId;
  /**
   * 设备功率
   */
  @TableField("device_power")
  private Integer devicePower;
  /**
   * 设备最大电压
   */
  @TableField("device_voltage")
  private Integer deviceVoltage;
  /**
   * 设备枪个数
   */
  @TableField("device_subnum")
  private Integer deviceSubnum;

  @TableField("device_state")
  private DeviceState deviceState;
  /**
   * 更新时间
   */
  @TableField("update_dt")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateDt;
  /**
   * 插入时间
   */
  @TableField("insert_dt")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date insertDt;


  @Override
  protected Serializable pkVal() {
    return this.deviceId;
  }

  public enum DeviceState implements IEnum<Integer> {
    ERROR(0, "故障"),
    IDLE(1, "空闲"),
    USING(2, "使用中");

    private Integer code;
    private String desc;

    DeviceState(int code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    @Override
    public Integer getValue() {
      return code;
    }

    @JsonValue
    public String getDesc() {
      return desc;
    }
  }

  @Override
  public String toString() {
    return "ChargingDevice{" +
            ", deviceId=" + deviceId +
            ", stationId=" + stationId +
            ", deviceCode=" + deviceCode +
            ", deviceName=" + deviceName +
            ", cgtypeId=" + cgtypeId +
            ", devicePower=" + devicePower +
            ", deviceSubnum=" + deviceSubnum +
            ", deviceState=" + deviceState +
            ", updateDt=" + updateDt +
            ", insertDt=" + insertDt +
            "}";
  }
}
