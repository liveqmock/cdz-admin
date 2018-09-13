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
 * @author:wanzhongsu
 * @description: 消息表实体类
 * @date:2018/9/12 14:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_sms")
public class UserSms extends Model<UserSms> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "sms_id", type = IdType.AUTO)
    private Integer smsId;
    /**
     * 消息标题
     */
    @TableField("sms_title")
    private String smsTitle;
    /**
     * 消息图片
     */
    @TableField("sms_pic")
    private String smsPic;
    /**
     * 消息URL
     */
    @TableField("sms_url")
    private String smsUrl;
    /**
     * 消息类型 1系统消息 2 广告banner
     */
    @TableField("sms_type")
    private SmsType smsType;
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
        return this.smsId;
    }

    /**
     * @author:wanzhongsu
     * @description: 消息类型枚举
     * @date:2018/9/12 14:41
     */
    public enum SmsType implements IEnum<Integer> {
        SYSTEM(1, "系统消息"),
        BANNER(2, "广告消息");
        private int value;
        private String desc;

        SmsType(int value, String desc) {
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
