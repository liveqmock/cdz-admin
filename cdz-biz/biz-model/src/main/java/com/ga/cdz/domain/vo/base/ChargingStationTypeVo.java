package com.ga.cdz.domain.vo.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingStationType;
import com.ga.cdz.domain.group.admin.IMChargingStationTypeGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 充电站运营类型vo
 * @date:2018/9/10 9:28
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class ChargingStationTypeVo {

    /**
     * 运营商类型ID
     */
    @NotNull(groups = {IMChargingStationTypeGroup.Delete.class}, message = "运营商ID不能为空")
    private Integer sttpeId;
    /**
     * 运营商类型名称
     */
    @NotBlank(groups = {IMChargingStationTypeGroup.Add.class}, message = "运营商名称不能为空")
    private String sttpeName;
    /**
     * 运营商状态 0删除  1正常
     */
    private ChargingStationType.SttpeState sttpeState;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;
}
