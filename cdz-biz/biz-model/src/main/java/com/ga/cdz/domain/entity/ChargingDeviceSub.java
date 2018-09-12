package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  huanghaohao
 * @date 2018-09-11 14:24
 * @desc 充电枪实体类
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_device_sub")
public class ChargingDeviceSub extends Model<ChargingDeviceSub> {

  private static final long serialVersionUID = 1L;

  /**
   * 枪编号
   */
  @TableId(value = "device_sub_id", type = IdType.NONE)
  private Integer deviceSubId;
  /**
   * 充电桩ID
   */
  @TableField("device_id")
  private Integer deviceId;
  /**
   * 枪名称
   */
  @TableField("device_sub_name")
  private String deviceSubName;
  /**
   * 0故障，1空闲，2占用
   */
  @TableField("device_sub_state")
  private Integer deviceSubState;
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

  @Override
  public String toString() {
    return "ChargingDeviceSub{" +
            ", deviceId=" + deviceId +
            ", deviceSubId=" + deviceSubId +
            ", deviceSubName=" + deviceSubName +
            ", deviceSubState=" + deviceSubState +
            ", updateDt=" + updateDt +
            ", insertDt=" + insertDt +
            "}";
  }

}
