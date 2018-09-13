package com.ga.cdz.domain.vo.api;

import com.ga.cdz.domain.group.api.ICharginStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
     * deviceNo
     */
    @NotBlank(groups = {ICharginStationGroup.MainPage.class}, message = "设备编号不能为空")
    private String deviceNo;

    /**
     * 城市编码
     */
    @NotNull(groups = {ICharginStationGroup.MainPage.class}, message = "城市编码")
    private Integer cityCode;

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
