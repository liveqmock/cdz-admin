package com.ga.cdz.domain.vo.api;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ga.cdz.domain.group.api.ICharginStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:luqi
 * @description: 根据条件获取 充电站分页列表
 * @date:2018/9/13_15:27
 */
@Data
@Accessors(chain = true)
public class ChargingStationPageVo {

    @NotNull(groups = {ICharginStationGroup.MainPage.class, ICharginStationGroup.NearPage.class}, message = "index不能为空")
    private Integer index;

    @NotNull(groups = {ICharginStationGroup.MainPage.class, ICharginStationGroup.NearPage.class}, message = "size不能为空")
    private Integer size;

    /**
     * deviceNo
     */
    @NotBlank(groups = {ICharginStationGroup.MainPage.class, ICharginStationGroup.NearPage.class}, message = "设备编号不能为空")
    private String deviceNo;

    /**
     * 城市编码
     */
    @NotNull(groups = {ICharginStationGroup.MainPage.class, ICharginStationGroup.NearPage.class}, message = "城市编码")
    private Integer cityCode;

    /**
     * 经度
     */
    @NotNull(groups = {ICharginStationGroup.MainPage.class, ICharginStationGroup.NearPage.class}, message = "经度不能为空")
    private Double lng;

    /**
     * 纬度
     */
    @NotNull(groups = {ICharginStationGroup.MainPage.class, ICharginStationGroup.NearPage.class}, message = "纬度不能为空")
    private Double lat;

    /**
     * 距离类型
     **/
    @NotNull(groups = {ICharginStationGroup.NearPage.class}, message = "距离类型不能为空")
    private DistanceType distanceType;


    /**
     * 价格类型
     **/
    @NotNull(groups = {ICharginStationGroup.NearPage.class}, message = "价格类型不能为空")
    private PriceType priceType;

    /**
     * @author:luqi
     * @description: 距离类型
     * @date:2018/9/16_15:27
     */
    public enum DistanceType implements IEnum<Double> {
        //离我最近
        NEAREST(0D, "0KM"),
        //小于等于10KM
        KM_10(10D, "10KM"),
        //小于等于20KM
        KM_20(20D, "20KM"),
        //小于等于40KM
        KM_40(40D, "40KM");

        private Double value;
        private String desc;


        DistanceType(Double value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Double getValue() {
            return this.value;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
    }

    /**
     * @author:luqi
     * @description: 价格类型
     * @date:2018/9/16_15:27
     */
    public enum PriceType implements IEnum<Integer> {
        MIN(0, "价格最低"),
        MAX(1, "价格最高");
        private Integer value;
        private String desc;


        PriceType(Integer value, String desc) {
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
