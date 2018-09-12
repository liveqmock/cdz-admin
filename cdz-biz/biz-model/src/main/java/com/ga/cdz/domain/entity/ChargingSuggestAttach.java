package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author:wanzhongsu
 * @description: 意见反馈附件表
 * @date:2018/9/12 20:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_suggest_attach")
public class ChargingSuggestAttach extends Model<ChargingSuggestAttach> {

    private static final long serialVersionUID = 1L;

    /**
     * 意见反馈ID
     */
    @TableId(value = "suggest_id", type = IdType.AUTO)
    private Integer suggestId;
    /**
     * 意见反馈附件路径
     */
    @TableField("suggest_path")
    private String suggestPath;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

    @Override
    public String toString() {
        return "ChargingSuggestAttach{" +
                "suggestId=" + suggestId +
                ", suggestPath='" + suggestPath + '\'' +
                ", insertDt=" + insertDt +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.suggestId;
    }

}
