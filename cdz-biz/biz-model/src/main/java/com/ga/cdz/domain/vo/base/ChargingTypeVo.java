package com.ga.cdz.domain.vo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.group.admin.IMChargingTypeGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @NotNull(groups = {IMChargingTypeGroup.delete.class}, message = "充电方式Id不能为空")
    private Integer cgtypeId;
    /**
     * 充电方式名称
     */
    @NotBlank(groups = {IMChargingTypeGroup.add.class}, message = "充电方式名称不能为空")
    private String cgtypeName;
    /**
     * 充电方式编码
     */
    @NotBlank(groups = {IMChargingTypeGroup.add.class}, message = "充电方式编码不能为空")
    private String cgtypeCode;
    /**
     * 充电方式状态 0删除  1正常
     */
    private ChargingType.CgtypeState cgtypeState;
    /**
     * 插入时间
     */
    private LocalDateTime insretDt;

}
