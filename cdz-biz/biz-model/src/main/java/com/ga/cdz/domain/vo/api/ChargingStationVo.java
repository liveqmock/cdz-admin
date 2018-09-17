package com.ga.cdz.domain.vo.api;

import com.ga.cdz.domain.group.api.IChargingStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: liuyi
 * @Description: 充电订单详情Vo
 * @Date: 2018/9/17_15:21
 */
@Data
@Accessors(chain = true)
public class ChargingStationVo {

    /**
     * 用户ID
     */
    @NotNull(groups = {IChargingStationGroup.Detail.class}, message = "用户Id不能为空")
    private Integer userId;

    /**
     * 充电站ID
     */
    @NotNull(groups = {IChargingStationGroup.Detail.class}, message = "充电站Id不能为空")
    private Integer stationId;

}
