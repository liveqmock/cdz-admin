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
import java.util.Date;

/**
 * @author:luqi
 * @description: 管理员信息表
 * @date:2018/9/5_9:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_admin_info")
public class AdminInfo extends Model<AdminInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;
    /**
     * 管理员姓名
     */
    @TableField("admin_name")
    private String adminName;
    /**
     * 管理员的账号
     */
    @TableField("admin_account")
    private String adminAccount;
    /**
     * 管理员密码MD5加密
     */
    @TableField("admin_pwd")
    private String adminPwd;
    /**
     * 电话
     */
    @TableField("admin_tel")
    private String adminTel;
    /**
     * 性别 0 女 1男
     */
    @TableField("admin_sex")
    private AdminSex adminSex;
    /**
     * 平台状态 0 可用 1禁用
     */
    @TableField("admin_state")
    private AdminState adminState;

    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;


    @Override
    protected Serializable pkVal() {
        return this.adminId;
    }


    /**
     * @author:luqi
     * @description: 管理员性别枚举
     * @date:2018/9/5_9:46
     */
   public enum AdminSex implements IEnum<Integer> {

        WOMEN(0,"女"),
        MAN(1,"男");

        private int value;
        private String desc;

        AdminSex(final int value, final String desc){
            this.value=value;
            this.desc=desc;
        }

        @Override
        @JsonValue
        public Integer getValue() {
            return this.value;
        }
    }


    /**
     * @author:luqi
     * @description: 管理员平台状态枚举
     * @date:2018/9/5_9:46
     */
    public enum AdminState implements IEnum<Integer> {
        ABLE(0,"可用"),
        DISABLE(1,"禁用");
        private int value;
        private String desc;

        AdminState(final int value, final String desc){
            this.value=value;
            this.desc=desc;
        }

        @Override
        @JsonValue
        public Integer getValue() {
            return this.value;
        }
    }

}
