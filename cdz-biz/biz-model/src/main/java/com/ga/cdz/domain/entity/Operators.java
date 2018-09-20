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
 * @description: 运营商信息表
 * @date:2018/9/10 10:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_operators")
public class Operators extends Model<Operators> {

    private static final long serialVersionUID = 1L;

    /**
     * 运营商ID
     */
    @TableId(value = "operators_id", type = IdType.AUTO)
    private Integer operatorsId;
    /**
     * 运营商名称
     */
    @TableField("operators_name")
    private String operatorsName;
    /**
     * 运营商编码
     */
    @TableField("operators_code")
    private String operatorsCode;
    /**
     * 运营商状态 0 删除  1 正常
     */
    @TableField("operators_state")
    private OperatorsState operatorsState;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;


    @Override
    protected Serializable pkVal() {
        return this.operatorsId;
    }

    /**
     * @author:wanzhongsu
     * @description: 运营商信息状态枚举
     * @date:2018/9/10 10:42
     */
    public enum OperatorsState implements IEnum<Integer> {
        DELETE(0, "删除"),
        NORMAL(1, "正常");
        private int value;
        private String desc;

        OperatorsState(final int value, final String desc) {
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
