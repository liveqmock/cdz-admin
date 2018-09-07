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
@TableName("t_charging_type")
public class ChargingType extends Model<ChargingType> {

    private static final long serialVersionUID = 1L;

    /**
     * 充电方式ID
     */
    @TableId(value = "cgtype_id", type = IdType.AUTO)
    private Integer cgtypeId;
    /**
     * 充电方式名称
     */
    @TableField("cgtype_name")
    private String cgtypeName;
    /**
     * 充电方式编码
     */
    @TableField("cgtype_code")
    private String cgtypeCode;
    /**
     * 插入时间
     */
    @TableField("insret_dt")
    private LocalDateTime insretDt;


    @Override
    protected Serializable pkVal() {
        return this.cgtypeId;
    }

}
