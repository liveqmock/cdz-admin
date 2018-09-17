package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.vo.api.ChargingOrderVo;

/**
 * @Author: liuyi
 * @Description: 订单Service
 * @Date: 2018/9/14_11:37
 */
public interface IChargingOrderService extends IService<ChargingOrder> {

    Integer placeOrderByPrice(ChargingOrderVo vo);
}
