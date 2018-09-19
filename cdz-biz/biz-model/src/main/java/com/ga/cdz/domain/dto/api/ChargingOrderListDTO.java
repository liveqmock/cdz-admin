package com.ga.cdz.domain.dto.api;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingOrder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ChargingOrderListDTO {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 充电站ID
     */
    private Integer stationId;

    /**
     * 充电桩ID
     */
    private Integer deviceId;

    /**
     * 枪编号
     */
    private Integer deviceSubId;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 总能量
     */
    private Double totalEnergy;

    /**
     * 开始充电时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date chargingBeginDt;

    /**
     * 结束充电时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date chargingEndDt;

    /**
     * 订单状态
     */
    @TableField("order_state")
    private ChargingOrder.OrderState orderState;

    public void setChargingOrder(ChargingOrder chargingOrder) {
        this.orderId = chargingOrder.getOrderId();
        this.stationId = chargingOrder.getStationId();
        this.deviceId = chargingOrder.getDeviceId();
        this.deviceSubId = chargingOrder.getDeviceSubId();
        this.totalPrice = chargingOrder.getTotalPrice();
        this.totalEnergy = chargingOrder.getTotalEnergy();
        this.chargingBeginDt = chargingOrder.getCharginBeginDt();
        this.chargingEndDt = chargingOrder.getCharginEndDt();
        this.orderState = chargingOrder.getOrderState();
    }

}
