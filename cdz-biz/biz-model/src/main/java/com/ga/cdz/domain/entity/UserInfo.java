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
 * @description: 用户信息表
 * @date:2018/9/5_9:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_info")
public class UserInfo extends Model<UserInfo> {


    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户编码
     */
    @TableField("user_code")
    private String userCode;

    /**
     * 类型 1个人用户 2 单位
     */
    @TableField("user_type")
    private UserType userType;
    /**
     * 用户真实姓名
     */
    @TableField("user_real_name")
    private String userRealName;
    /**
     * 密码
     */
    @TableField("user_pwd")
    private String userPwd;
    /**
     * 电话
     */
    @TableField("user_tel")
    private String userTel;
    /**
     * 昵称
     */
    @TableField("user_nick_name")
    private String userNickName;
    /**
     * 性别 0 女 1男
     */
    @TableField("user_sex")
    private UserSex userSex;

    /**
     * 用户头像
     */
    @TableField("user_avatar")
    private String userAvatar;

    /**
     * 省级编码
     */
    @TableField("province")
    private Integer province;
    /**
     * 城市编码
     */
    @TableField("city")
    private Integer city;
    /**
     * 县编码
     */
    @TableField("country")
    private Integer country;

    /**
     * 用户状态
     */
    @TableField("user_state")
    private UserState userState;

    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }


    /**
     * @author:luqi
     * @description: 用户性别枚举类
     * @date:2018/9/5_9:45
     */
    public enum UserSex implements IEnum<Integer> {
        WOMEN(0,"女"),
        MAN(1,"男");

        private int value;
        private String desc;

        UserSex(final int value, final String desc){
            this.value=value;
            this.desc=desc;
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
     * @author:luqi
     * @description: 用人单位枚举
     * @date:2018/9/7_13:07
     */
    public enum UserType implements IEnum<Integer> {
        PERSONAL(1, "个人用户"),
        COMPANY(2, "单位");

        private int value;
        private String desc;

        UserType(final int value, final String desc) {
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
     * @author:luqi
     * @description: 用户状态
     * @date:2018/9/7_13:07
     */
    public enum UserState implements IEnum<Integer> {
        FREEZE(0, "冻结"),
        NORMAL(1, "正常");

        private int value;
        private String desc;

        UserState(final int value, final String desc) {
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
