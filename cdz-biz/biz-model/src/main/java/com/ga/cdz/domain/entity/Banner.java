package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author: liuyi
 * @description: Banner实体
 * @date: 2018/9/11_11:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_sms")
public class Banner extends Model<Banner> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableField("sms_id")
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
     * 消息类型 1 系统消息 2 广告banner
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

    public enum SmsType implements IEnum<Integer> {

        SYSTEM_SMS(1, "系统消息"),
        AD_SMS(2, "广告banner");

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
