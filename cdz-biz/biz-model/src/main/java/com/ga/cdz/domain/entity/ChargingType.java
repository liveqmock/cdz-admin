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
 * @description: 充电方式
 * @date:2018/9/10 10:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_type")
public class ChargingType extends Model<ChargingType> {

    private static final long serialVersionUID = 1L;

    /**
     * 充电方式ID
     */
    @TableId(value = "cgtype_id", type = IdType.AUTO)
    private Integer cgtypeId;
    /**
     * 充电方式名称
     */
    @TableField("cgtype_name")
    private String cgtypeName;
    /**
     * 充电快慢 1 慢 2快
     */
    @TableField("cgtype_mode")
    private CgtypeMode cgtypeMode;
    /**
     * 充电方式编码
     */
    @TableField("cgtype_code")
    private String cgtypeCode;
    /**
     * 充电方式状态 0删除  1正常
     */
    @TableField("cgtype_state")
    private CgtypeState cgtypeState;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;


    @Override
    protected Serializable pkVal() {
        return this.cgtypeId;
    }

    /**
     * @author:wanzhongsu
     * @description: 运营商状态枚举
     * @date:2018/9/10 10:51
     */
    public enum CgtypeState implements IEnum<Integer> {
        DELETE(0, "删除"),
        NORMAL(1, "正常");

        private int value;
        private String desc;

        CgtypeState(int value, String desc) {
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
     * @author:wanzhongsu
     * @description: 充电快慢枚举
     * @date:2018/9/12 18:52
     */
    public enum CgtypeMode implements IEnum<Integer> {
        FAST(1, "慢"),
        SLOW(2, "快");
        private int value;
        private String desc;

        CgtypeMode(int value, String desc) {
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
