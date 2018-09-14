package com.ga.cdz.domain.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingPrice;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Time;

/**
 * @author:wanzhongsu
 * @description: 查询返回的实体类
 * @date:2018/9/11 10:36
 */

@Data
@Accessors(chain = true)
public class ChargingPriceDTO {

    /**
     * 商户ID
     */
    private Integer shopId;
    /**
     * 商户编码
     */
    private String shopCode;
    /**
     * 商户名称
     */
    private String shopName;
    /**
     * 站编码
     */
    private String stationCode;
    /**
     * 站名称
     */
    private String stationName;


    /**
     * 充电站ID
     */
    private Integer stationId;
    /**
     * 价格名称
     */
    private String priceName;
    /**
     * 价格类型 1 专场计费 2 非专场计费
     */
    private ChargingPrice.PriceType priceType;
    /**
     * 低谷
     */
    private ChargingPrice.PriceIdx low;
    /**
     * 低谷开始时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time lowStart;
    /**
     * 低谷结束时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time lowEnd;
    /**
     * 低谷充电价格
     */
    private BigDecimal lowPrice;
    /**
     * 低谷停车场价格
     */
    private BigDecimal lowParking;
    /**
     * 低谷服务费用
     */
    private BigDecimal lowService;

    /**
     * 平谷
     */
    private ChargingPrice.PriceIdx middle;
    /**
     * 平谷开始时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time middleStart;
    /**
     * 平谷结束时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time middleEnd;
    /**
     * 平谷充电价格
     */
    private BigDecimal middlePrice;
    /**
     * 平谷停车场价格
     */
    private BigDecimal middleParking;
    /**
     * 平谷服务费用
     */
    private BigDecimal middleService;

    /**
     * 高峰
     */
    private ChargingPrice.PriceIdx high;
    /**
     * 高峰开始时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time highStart;
    /**
     * 高峰结束时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Time highEnd;
    /**
     * 高峰充电价格
     */
    private BigDecimal highPrice;
    /**
     * 高峰停车场价格
     */
    private BigDecimal highParking;
    /**
     * 高峰服务费用
     */
    private BigDecimal highService;
}
