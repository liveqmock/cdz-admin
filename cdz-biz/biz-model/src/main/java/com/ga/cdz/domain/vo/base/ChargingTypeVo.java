package com.ga.cdz.domain.vo.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.group.admin.IMChargingTypeGroup;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 充电桩充电方式vo
 * @date:2018/9/10 9:28
 */
@Data
@Accessors(chain = true)
public class ChargingTypeVo {
    /**
     * 充电方式ID
     */
    @NotNull(groups = {IMChargingTypeGroup.Delete.class}, message = "充电方式Id不能为空")
    private Integer cgtypeId;
    /**
     * 充电方式名称
     */
    @NotBlank(groups = {IMChargingTypeGroup.Add.class}, message = "充电方式名称不能为空")
    private String cgtypeName;
    /**
     * 充电快慢 1 慢 2快
     */
    @NotNull(groups = {IMChargingTypeGroup.Add.class}, message = "充电快慢不能为空")
    private ChargingType.CgtypeMode cgtypeMode;
    /**
     * 充电方式编码
     */
    @NotBlank(groups = {IMChargingTypeGroup.Add.class}, message = "充电方式编码不能为空")
    private String cgtypeCode;
    /**
     * 充电方式状态 0删除  1正常
     */
    private ChargingType.CgtypeState cgtypeState;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

}
