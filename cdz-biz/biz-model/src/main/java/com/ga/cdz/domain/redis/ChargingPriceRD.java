package com.ga.cdz.domain.redis;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ChargingPriceRD {

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
    private Integer priceType;

    /**
     * 顺序
     */
    private Integer priceIdx;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date priceBeginDt;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date priceEndDt;

    /**
     * 充电价格
     */
    private BigDecimal chargingPrice;

    /**
     * 停车场价格
     */
    private BigDecimal priceParking;

    /**
     * 服务费用
     */
    private BigDecimal servicePrice;

    /**
     * 计费状态 1可用 0禁用
     */
    private Integer priceState;

    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;
}
