package com.ga.cdz.domain.dto.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingOrder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class ChargingOrderListDTO {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 充电站名字
     */
    private String stationName;

    /**
     * 附件图片路径
     */
    private String attachPath;

    /**
     * 总价格
     */
    private Double totalPrice;

    /**
     * 总能量
     */
    private Double totalEnergy;

    /**
     * 订单状态
     */
    private ChargingOrder.OrderState orderState;

    public void setChargingOrder(ChargingOrder chargingOrder) {
        this.orderId = chargingOrder.getOrderId();
        this.totalPrice = chargingOrder.getTotalPrice().doubleValue();
        this.totalEnergy = chargingOrder.getTotalEnergy();
        this.orderState = chargingOrder.getOrderState();
    }

}
