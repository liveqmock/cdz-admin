package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
   * 充电桩ID
   */
  @TableId(value = "device_id", type = IdType.AUTO)
  private Integer deviceId;
  /**
   * 枪编号
   */
  @TableField("device_sub_id")
  private Integer deviceSubId;
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
  private Date updateDt;
  /**
   * 插入时间
   */
  @TableField("insert_dt")
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
