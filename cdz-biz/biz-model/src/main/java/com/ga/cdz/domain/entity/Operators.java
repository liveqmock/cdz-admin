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
@TableName("t_operators")
public class Operators extends Model<Operators> {

    private static final long serialVersionUID = 1L;

    /**
     * 地区ID
     */
    @TableId(value = "operators_id", type = IdType.AUTO)
    private Integer operatorsId;
    /**
     * 地区名称
     */
    @TableField("operators_name")
    private String operatorsName;
    /**
     * 地区编码
     */
    @TableField("operators_code")
    private String operatorsCode;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    private LocalDateTime insertDt;


    @Override
    protected Serializable pkVal() {
        return this.operatorsId;
    }

}
