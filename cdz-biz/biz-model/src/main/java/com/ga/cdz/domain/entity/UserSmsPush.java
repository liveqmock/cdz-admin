package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户推送表
 * </p>
 *
 * @author lq
 * @since 2018-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_sms_push")
public class UserSmsPush extends Model<UserSmsPush> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.NONE)
    private Integer userId;

    /**
     * 设备标签
     */
    @TableField("push_tag")
    private String pushTag;

    /**
     * 设备别名
     */
    @TableField("push_alias")
    private String pushAlias;

    /**
     * 推送注册ID
     */
    @TableField("push_regid")
    private String pushRegid;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
