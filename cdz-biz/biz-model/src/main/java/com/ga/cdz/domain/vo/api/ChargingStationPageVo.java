package com.ga.cdz.domain.vo.api;

import com.ga.cdz.domain.group.api.ICharginStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author:luqi
 * @description: 根据条件获取 充电站分页列表
 * @date:2018/9/13_15:27
 */
@Data
@Accessors(chain = true)
public class ChargingStationPageVo {

    @NotNull(groups = {ICharginStationGroup.MainPage.class}, message = "index不能为空")
    private Integer index;

    @NotNull(groups = {ICharginStationGroup.MainPage.class}, message = "size不能为空")
    private Integer size;

    /**
     * 经度
     */
    @NotNull(groups = {ICharginStationGroup.MainPage.class}, message = "经度不能为空")
    private Double lng;

    /**
     * 纬度
     */
    @NotNull(groups = {ICharginStationGroup.MainPage.class}, message = "纬度不能为空")
    private Double lat;

}
