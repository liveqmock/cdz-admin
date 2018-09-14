package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:wanzhongsu
 * @description: 地区信息表
 * @date:2018/9/10 10:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_district")
public class District extends Model<District> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编码
     */
    @TableId(value = "district_code", type = IdType.AUTO)
    private Integer districtCode;
    /**
     * 地区名称
     */
    @TableField("district_name")
    private String districtName;
    /**
     * 上一级区域code
     */
    @TableField("district_parent_code")
    private Integer districtParentCode;
    /**
     * 区域等级
     */
    @TableField("district_level")
    private DistrictLevel districtLevel;

    @Override
    protected Serializable pkVal() {
        return this.districtCode;
    }

    /**
     * @author:wanzhongsu
     * @description: 区域等级枚举
     * @date:2018/9/14 14:14
     */
    public enum DistrictLevel implements IEnum<Integer> {
        SHENG(0, "省"),
        SHI(1, "市"),
        QU(2, "区"),
        ZHEN(3, "镇"),
        JD(4, "街道");
        private int value;
        private String desc;

        DistrictLevel(int value, String desc) {
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
