package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_cars")
  public class UserCarsInfo extends Model<UserCarsInfo>{

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Integer userId;
    /**
     * 车辆号码
     */
    @TableField("car_no")
    private String carNo;
    /**
     * 车辆名称
     */
    @TableField("car_name")
    private String carName;
    /**
     * 车辆型号
     */
    @TableField("car_model")
    private String carModel;
    /**
     * 发动机编号
     */
    @TableField("car_engine")
    private String carEngine;
    /**
     * 车架号
     */
    @TableField("car_vin")
    private String carVin;
    /**
     * 电池编号
     */
    @TableField("car_battery")
    private String carBattery;
    @TableField("car_state")
    private Integer carState;
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
      return this.userId;
    }

    @Override
    public String toString() {
      return "UserCars{" +
              ", userId=" + userId +
              ", carNo=" + carNo +
              ", carName=" + carName +
              ", carModel=" + carModel +
              ", carEngine=" + carEngine +
              ", carVin=" + carVin +
              ", carBattery=" + carBattery +
              ", carState=" + carState +
              ", updateDt=" + updateDt +
              ", insertDt=" + insertDt +
              "}";
    }
  }

