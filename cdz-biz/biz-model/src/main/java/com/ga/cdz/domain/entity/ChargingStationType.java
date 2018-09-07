package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
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
     * 插入时间
     */
    @TableField("insert_dt")
    private LocalDateTime insertDt;


    @Override
    protected Serializable pkVal() {
        return this.sttpeId;
    }

}
