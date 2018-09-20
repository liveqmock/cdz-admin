package com.ga.cdz.domain.dto.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingPrice;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.entity.ChargingStation;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Time;

/**
 * @param
 * @Author: liuyi
 * @Description:
 * @Date: 2018/9/18_9:11
 * @return
 */
@Data
@Accessors
public class ChargingStationDetailDTO {

    /**
     * 充电站ID
     */
    private Integer stationId;

    /**
     * 商户ID
     */
    private Integer shopId;

    /**
     * 站编码
     */
    private String stationCode;

    /**
     * 站名称
     */
    private String stationName;

    /**
     * 充电站类型 1对外开放 2不对外开放
     */
    private ChargingStation.StationType stationType;

    /**
     * 运营商类型ID
     */
    private Integer sttpeId;

    /**
     * 运营商ID
     */
    private Integer operatorsId;

    /**
     * 维度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 充电站状态 0 删除 1 正常
     */
    private ChargingStation.StationState stationState;

    /**
     * 充电站开放时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationOpendt;

    /**
     * 充电站关闭时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time stationClosedt;

    /**
     * 充电桩详细地址
     */
    private String stationAddr;

    /**
     * 充电桩省编码
     */
    private Integer province;

    /**
     * 充电桩市编码
     */
    private Integer city;

    /**
     * 充电桩区编码
     */
    private Integer county;

    /**
     * 乡镇（街道）编码
     */
    private Integer country;

    /**
     * 商户编码
     */
    private String shopCode;

    /**
     * 登录名
     */
    private String shopLogin;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 商户联系人
     */
    private String shopContact;

    /**
     * 商户联系电话
     */
    private String shopTel;

    /**
     * 商户状态 0 删除 1 正常
     */
    private ChargingShop.ShopState shopState;

    /**
     * 价格名称
     */
    private String priceName;

    /**
     * 价格类型 1 专场计费 2 非专场计费
     */
    private ChargingPrice.PriceType priceType;

    /**
     * 顺序
     */
    private ChargingPrice.PriceIdx priceIdx;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time priceBeginDt;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time priceEndDt;

    /**
     * 充电价格
     */
    private Double chargingPrice;

    /**
     * 停车场价格
     */
    private Double priceParking;

    /**
     * 服务费用
     */
    private Double servicePrice;

    /**
     * @param chargingStation
     * @return
     * @Author: liuyi
     * @Description: 赋值ChargingStation
     * @Date: 2018/9/18_9:25
     */
    public void setChargingStation(ChargingStation chargingStation) {
        this.stationId = chargingStation.getStationId();
        this.shopId = chargingStation.getShopId();
        this.stationCode = chargingStation.getStationCode();
        this.stationName = chargingStation.getStationName();
        this.stationType = chargingStation.getStationType();
        this.sttpeId = chargingStation.getSttpeId();
        this.operatorsId = chargingStation.getOperatorsId();
        this.lat = chargingStation.getLat();
        this.lng = chargingStation.getLng();
        this.stationState = chargingStation.getStationState();
        this.stationOpendt = chargingStation.getStationOpendt();
        this.stationClosedt = chargingStation.getStationClosedt();
        this.stationAddr = chargingStation.getStationAddr();
        this.province = chargingStation.getProvince();
        this.city = chargingStation.getCity();
        this.county = chargingStation.getCounty();
        this.country = chargingStation.getCountry();
    }

    /**
     * @param chargingShop
     * @return
     * @Author: liuyi
     * @Description: 赋值ChargingShop
     * @Date: 2018/9/18_9:25
     */
    public void setChargingShop(ChargingShop chargingShop) {
        this.shopCode = chargingShop.getShopCode();
        this.shopLogin = chargingShop.getShopLogin();
        this.shopName = chargingShop.getShopName();
        this.shopContact = chargingShop.getShopContact();
        this.shopTel = chargingShop.getShopTel();
        this.shopState = chargingShop.getShopState();
    }

    public void setChargingPrice(ChargingPrice chargingPrice) {
        this.priceName = chargingPrice.getPriceName();
        this.priceType = chargingPrice.getPriceType();
        this.priceIdx = chargingPrice.getPriceIdx();
        this.priceBeginDt = chargingPrice.getPriceBeginDt();
        this.priceEndDt = chargingPrice.getPriceEndDt();
        this.chargingPrice = chargingPrice.getChargingPrice().doubleValue();
        this.priceParking = chargingPrice.getPriceParking().doubleValue();
        this.servicePrice = chargingPrice.getServicePrice().doubleValue();
    }

}
