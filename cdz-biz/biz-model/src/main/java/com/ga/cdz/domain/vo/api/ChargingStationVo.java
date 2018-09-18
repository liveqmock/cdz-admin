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

    @NotNull(groups = {IChargingStationGroup.Comment.class}, message = "pageIndex不能为空")
    private Integer pageIndex;

    @NotNull(groups = {IChargingStationGroup.Comment.class}, message = "pageSize不能为空")
    private Integer pageSize;

    /**
     * 用户ID
     */
    @NotNull(groups = {IChargingStationGroup.Detail.class, IChargingStationGroup.Terminal.class, IChargingStationGroup.Comment.class}, message = "用户Id不能为空")
    private Integer userId;

    /**
     * 充电站ID
     */
    @NotNull(groups = {IChargingStationGroup.Detail.class, IChargingStationGroup.Terminal.class, IChargingStationGroup.Comment.class}, message = "充电站Id不能为空")
    private Integer stationId;

}
