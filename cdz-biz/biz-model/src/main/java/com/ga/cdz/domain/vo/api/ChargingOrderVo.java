package com.ga.cdz.domain.vo.api;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: liuyi
 * @Description: 充电订单详情VO
 * @Date: 2018/9/14_11:59
 */
@Data
@Accessors(chain = true)
public class ChargingOrderVo {

    /**
     * 用户ID
     */
    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    /**
     * 充电站ID
     */
    @NotNull(message = "充电站Id不能为空")
    private Integer stationId;

    /**
     * 充电桩ID
     */
    @NotNull(message = "充电桩Id不能为空")
    private Integer deviceId;

    /**
     * 枪编号
     */
    @NotNull(message = "充电枪编号不能为空")
    private Integer deviceSubId;

    /**
     * 需要充的价格
     */
    @NotNull(message = "价格不能为空")
    private BigDecimal totalPrice;

}
