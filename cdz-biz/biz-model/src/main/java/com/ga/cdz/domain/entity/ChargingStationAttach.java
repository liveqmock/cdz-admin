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
 * @author huanghaohao
 * @date 2018-09-11 14:28
 * @desc 充电站附件表
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_station_attach")
public class ChargingStationAttach extends Model<ChargingStationAttach> {

  private static final long serialVersionUID = 1L;

  /**
   * 充电站ID
   */
  @TableId(value = "station_id", type = IdType.AUTO)
  private Integer stationId;
  /**
   * 附件编号
   */
  @TableField("attach_idx")
  private Integer attachIdx;
  /**
   * 附件图片路径
   */
  @TableField("attach_path")
  private String attachPath;
  /**
   * 附件描述
   */
  @TableField("attach_desc")
  private String attachDesc;
  /**
   * 附件状态
   */
  @TableField("attach_state")
  private Integer attachState;
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
    return this.stationId;
  }

  @Override
  public String toString() {
    return "ChargingStationAttach{" +
            ", stationId=" + stationId +
            ", attachIdx=" + attachIdx +
            ", attachPath=" + attachPath +
            ", attachDesc=" + attachDesc +
            ", attachState=" + attachState +
            ", updateDt=" + updateDt +
            ", insertDt=" + insertDt +
            "}";
  }

}
