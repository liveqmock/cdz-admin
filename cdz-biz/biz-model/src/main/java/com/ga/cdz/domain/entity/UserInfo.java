package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 登录的用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码MD5加密
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
    private Integer userSex;
    /**
     * 用户余额
     */
    @TableField("user_price")
    private BigDecimal userPrice;
    /**
     * 用户积分
     */
    @TableField("user_score")
    private UserSex userScore;
    /**
     * 省级编码
     */
    private String province;
    /**
     * 城市编码
     */
    private String city;
    /**
     * 县编码
     */
    private String country;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    private LocalDateTime insertDt;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }


    /**
     * @author:luqi
     * @description: 用户性别枚举类
     * @date:2018/9/5_9:45
     */
    enum UserSex implements IEnum<Integer> {

        WOMEN(0,"女"),
        MAN(1,"男");

        private int value;
        private String desc;

        UserSex(final int value, final String desc){
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
