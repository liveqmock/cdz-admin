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
import java.sql.Time;
import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 充电站列表
 * @date:2018/9/10 15:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_station")
public class ChargingStation extends Model<ChargingStation> {

    private static final long serialVersionUID = 1L;

    /**
     * 充电站ID
     */
    @TableId(value = "station_id", type = IdType.AUTO)
    private Integer stationId;
    /**
     * 商户ID
     */
    @TableField("shop_id")
    private Integer shopId;
    /**
     * 站编码
     */
    @TableField("station_code")
    private String stationCode;
    /**
     * 站名称
     */
    @TableField("station_name")
    private String stationName;
    /**
     * 充电站类型 1对外开放 2不对外开放
     */
    @TableField("station_type")
    private StationType stationType;
    /**
     * 运营商类型ID
     */
    @TableField("sttpe_id")
    private Integer sttpeId;
    /**
     * 运营商ID
     */
    @TableField("operators_id")
    private Integer operatorsId;

    /**
     * 维度
     */
    @TableField("lat")
    private Double lat;
    /**
     * 经度
     */
    @TableField("lng")
    private Double lng;
    /**
     * 充电站状态 0 删除 1 正常
     */
    @TableField("station_state")
    private StationState stationState;
    /**
     * 充电站开放时间
     */
    @TableField("station_opendt")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationOpendt;
    /**
     * 充电站关闭时间
     */
    @TableField("station_closedt")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationClosedt;

    /**
     * 充电桩详细地址
     */
    @TableField("station_addr")
    private String stationAddr;

    /**
     * 充电桩省编码
     */
    @TableField("province")
    private Integer province;

    /**
     * 充电桩市编码
     */
    @TableField("city")
    private Integer city;

    /**
     * 充电桩区编码
     */
    @TableField("county")
    private Integer county;
    /**
     * 乡镇（街道）编码
     */
    @TableField("country")
    private Integer country;
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
        return this.stationId;
    }

    /**
     * @author:wanzhongsu
     * @description: 充电站状态枚举
     * @date:2018/9/10 15:11
     */
    public enum StationState implements IEnum<Integer> {
        DELETE(0, "删除"),
        NORMAL(1, "正常");

        private int value;
        private String desc;

        StationState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.value;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 充电站类型枚举
     * @date:2018/9/10 15:17
     */
    public enum StationType implements IEnum<Integer> {
        OUTSIDE(1, "对外开放"),
        INSIDE(2, "不对外开放");

        private int value;
        private String desc;

        StationType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.value;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
    }
}

