package com.ga.cdz.domain.vo.admin;

import com.ga.cdz.domain.entity.ChargingPrice;
import com.ga.cdz.domain.group.admin.IMChargingPriceGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * @author:wanzhongsu
 * @description: 计费价格vo
 * @date:2018/9/11 10:56
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class ChargingPriceVo {
    /**
     * 充电站ID
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class, IMChargingPriceGroup.update.class}, message = "充电站Id不能为空")
    private Integer stationId;
    /**
     * 价格名称
     */
    @NotBlank(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class, IMChargingPriceGroup.update.class}, message = "策略名称不能为空")
    private String priceName;
    /**
     * 价格类型 1 专场计费 2 非专场计费
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class, IMChargingPriceGroup.update.class}, message = "价格类型不能为空")
    private ChargingPrice.PriceType priceType;
    /**
     * 低谷
     */
    private ChargingPrice.PriceIdx low;
    /**
     * 低谷开始时间
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "低谷开始时间不能为空")
    private LocalTime lowStart;
    /**
     * 低谷结束时间
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "低谷结束时间不能为空")
    private LocalTime lowEnd;
    /**
     * 低谷充电价格
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "低谷充电价格不能为空")
    private BigDecimal lowPrice;
    /**
     * 低谷停车场价格
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "低谷停车场价格不能为空")
    private BigDecimal lowParking;
    /**
     * 低谷服务费用
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "低谷服务费用不能为空")
    private BigDecimal lowService;
    /**
     * 低谷计费状态 1可用 0禁用
     */
    private ChargingPrice.PriceState lowState;

    /**
     * 平谷
     */
    private ChargingPrice.PriceIdx middle;
    /**
     * 平谷开始时间
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "平谷开始时间不能为空")
    private LocalTime middleStart;
    /**
     * 平谷结束时间
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "平谷结束时间不能为空")
    private LocalTime middleEnd;
    /**
     * 平谷充电价格
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "平谷充电价格不能为空")
    private BigDecimal middlePrice;
    /**
     * 平谷停车场价格
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "平谷停车场价格不能为空")
    private BigDecimal middleParking;
    /**
     * 平谷服务费用
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "平谷服务费用不能为空")
    private BigDecimal middleService;
    /**
     * 平谷计费状态 1可用 0禁用
     */
    private ChargingPrice.PriceState middleState;


    /**
     * 高峰
     */
    private ChargingPrice.PriceIdx high;
    /**
     * 高峰开始时间
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "高峰开始时间不能为空")
    private LocalTime highStart;
    /**
     * 高峰结束时间
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "高峰结束时间不能为空")
    private LocalTime highEnd;
    /**
     * 高峰充电价格
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "高峰充电价格不能为空")
    private BigDecimal highPrice;
    /**
     * 高峰停车场价格
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "高峰停车场价格不能为空")
    private BigDecimal highParking;
    /**
     * 高峰服务费用
     */
    @NotNull(groups = {IMChargingPriceGroup.add.class, IMChargingPriceGroup.update.class}, message = "高峰服务费用不能为空")
    private BigDecimal highService;
    /**
     * 高峰计费状态 1可用 0禁用
     */
    private ChargingPrice.PriceState highState;
}
