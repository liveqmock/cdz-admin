package com.ga.cdz.domain.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalTime;
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
 * @description: 计费标准表
 * @date:2018/9/11 10:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_price")
public class ChargingPrice extends Model<ChargingPrice> {

    private static final long serialVersionUID = 1L;

    /**
     * 充电站ID
     */
    @TableId(value = "station_id", type = IdType.NONE)
    private Integer stationId;
    /**
     * 价格名称
     */
    @TableField("price_name")
    private String priceName;
    /**
     * 价格类型 1 专场计费 2 非专场计费
     */
    @TableField("price_type")
    private PriceType priceType;
    /**
     * 顺序
     */
    @TableField("price_idx")
    private PriceIdx priceIdx;
    /**
     * 开始时间
     */
    @TableField("price_begin_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalTime priceBeginDt;
    /**
     * 结束时间
     */
    @TableField("price_end_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalTime priceEndDt;
    /**
     * 充电价格
     */
    @TableField("charging_price")
    private BigDecimal chargingPrice;
    /**
     * 停车场价格
     */
    @TableField("price_parking")
    private BigDecimal priceParking;
    /**
     * 服务费用
     */
    @TableField("service_price")
    private BigDecimal servicePrice;
    /**
     * 计费状态 1可用 0禁用
     */
    @TableField("price_state")
    private PriceState priceState;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime insertDt;

    public String getStationIdStr() {
        return this.stationId + "";
    }


    @Override
    protected Serializable pkVal() {
        return this.stationId;
    }

    /**
     * @author:wanzhongsu
     * @description: 充电类型枚举
     * @date:2018/9/11 10:43
     */
    public enum PriceType implements IEnum<Integer> {
        PERSONAL(1, "专场计费"),
        NONPERSONAL(2, "非专场计费");

        private int value;
        private String desc;

        PriceType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        @JsonValue
        public Integer getValue() {
            return this.value;
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 计费状态枚举
     * @date:2018/9/11 10:48
     */
    public enum PriceState implements IEnum<Integer> {
        ABLE(1, "可用"),
        DISABLE(0, "禁用");

        private int value;
        private String desc;

        PriceState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }


        @Override
        @JsonValue
        public Integer getValue() {
            return this.value;
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 顺序枚举
     * @date:2018/9/11 10:51
     */
    public enum PriceIdx implements IEnum<Integer> {
        LOW(1, "低谷"),
        MIDDLE(2, "平谷"),
        HIGH(3, "高峰");

        private int value;
        private String desc;

        PriceIdx(int value, String desc) {
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
