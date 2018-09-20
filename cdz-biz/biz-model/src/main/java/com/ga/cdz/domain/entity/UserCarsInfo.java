package com.ga.cdz.domain.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_cars")
public class UserCarsInfo extends Model<UserCarsInfo> {

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
    private CarState carState;
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


    /**
     * @author:luqi
     * @description: 车辆状态枚举
     * @date:2018/9/13_10:05
     */
    public enum CarState implements IEnum<Integer> {
        REMOVE(0, "删除"),
        NORMAL(1, "正常");

        private Integer code;
        private String desc;

        CarState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.code;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
    }


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

