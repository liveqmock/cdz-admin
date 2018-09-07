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
@TableName("t_charging_shop")
public class ChargingShop extends Model<ChargingShop> {

    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;
    /**
     * 商户编码
     */
    @TableField("shop_code")
    private String shopCode;
    /**
     * 商户名称
     */
    @TableField("shop_name")
    private String shopName;
    /**
     * 商户联系人
     */
    @TableField("shop_contact")
    private String shopContact;
    /**
     * 商户联系电话
     */
    @TableField("shop_tel")
    private String shopTel;
    @TableField("shop_state")
    private Boolean shopState;
    /**
     * 更新时间
     */
    @TableField("update_dt")
    private LocalDateTime updateDt;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    private LocalDateTime insertDt;


    @Override
    protected Serializable pkVal() {
        return this.shopId;
    }

}
