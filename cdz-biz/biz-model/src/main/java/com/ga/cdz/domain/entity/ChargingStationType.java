package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author:wanzhongsu
 * @description: 充电站运营类型
 * @date:2018/9/10 10:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_station_type")
public class ChargingStationType extends Model<ChargingStationType> {

    private static final long serialVersionUID = 1L;

    /**
     * 运营商类型ID
     */
    @TableId(value = "sttpe_id", type = IdType.AUTO)
    private Integer sttpeId;
    /**
     * 运营商类型名称
     */
    @TableField("sttpe_name")
    private String sttpeName;
    /**
     * 运营商状态 0删除  1正常
     */
    @TableField("sttpe_state")
    private SttpeState sttpeState;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime insertDt;


    @Override
    protected Serializable pkVal() {
        return this.sttpeId;
    }

    /**
     * @author:wanzhongsu
     * @description: 运营商状态枚举
     * @date:2018/9/10 10:57
     */
    public enum SttpeState implements IEnum<Integer> {
        DELETE(0, "删除"),
        NORMAL(1, "正常");
        private int value;
        private String desc;

        SttpeState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        @JsonValue
        public Integer getValue() {
            return this.value;
        }
    }
}
