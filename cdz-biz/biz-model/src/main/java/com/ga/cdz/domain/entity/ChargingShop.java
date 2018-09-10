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
 * @description: 商户管理表
 * @date:2018/9/10 10:26
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
    /**
     * 登录密码
     */
    @TableField("shop_pwd")
    private String shopPwd;
    /**
     * 商户状态 0 删除 1 正常
     */
    @TableField("shop_state")
    private ShopState shopState;
    /**
     * 更新时间
     */
    @TableField("update_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDt;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime insertDt;


    @Override
    protected Serializable pkVal() {
        return this.shopId;
    }

    /**
     * @author:wanzhongsu
     * @description: 商户状态枚举
     * @date:2018/9/10 12:48
     */
    public enum ShopState implements IEnum<Integer> {
        DELETE(0, "删除"),
        NORMAL(1, "正常");

        private String desc;
        private int value;

        ShopState(int value, String desc) {
            this.desc = desc;
            this.value = value;
        }

        @JsonValue
        @Override
        public Integer getValue() {
            return this.value;
        }
    }
}
