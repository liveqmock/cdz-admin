package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.ChargingOrderListDTO;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.vo.api.ChargingOrderInitVo;
import com.ga.cdz.domain.vo.api.ChargingOrderPageListVo;

import java.util.List;

/**
 * @Author: liuyi
 * @Description: 订单Service
 * @Date: 2018/9/14_11:37
 */
public interface IChargingOrderService extends IService<ChargingOrder> {

    /**
     * @Author: liuyi
     * @Description: 获取全部订单的分页信息
     * @Date: 2018/9/19_11:32
     * @param vo ChargingOrderPageListVo
     * @return List<ChargingOrderListDTO>
     */
    List<ChargingOrderListDTO> getChargingOrderOfAllPageList(ChargingOrderPageListVo vo);

    /**
     * @Author: liuyi
     * @Description:  获取待使用订单的分页信息
     * @Date: 2018/9/19_14:53
     * @param vo ChargingOrderPageListVo
     * @return List<ChargingOrderListDTO>
     */
    List<ChargingOrderListDTO> getChargingOrderOfInitPageList(ChargingOrderPageListVo vo);

    /**
     * @Author: liuyi
     * @Description: 获取待支付订单的分页信息
     * @Date: 2018/9/19_14:54
     * @param vo ChargingOrderPageListVo
     * @return List<ChargingOrderListDTO>
     */
    List<ChargingOrderListDTO> getOrderListOfPayingPageList(ChargingOrderPageListVo vo);

    /**
     * @Author: liuyi
     * @Description: 获取待评价订单的分页信息
     * @Date: 2018/9/19_14:56
     * @param vo ChargingOrderPageListVo
     * @return List<ChargingOrderListDTO>
     */
    List<ChargingOrderListDTO> getOrderListOfPayedPageList(ChargingOrderPageListVo vo);

    /**
     * @Author: liuyi
     * @Description: 获取退款/售后订单的分页信息
     * @Date: 2018/9/19_14:59
     * @param vo ChargingOrderPageListVo
     * @return List<ChargingOrderListDTO>
     */
    List<ChargingOrderListDTO> getOrderListOfRefundingPageList(ChargingOrderPageListVo vo);

    /**
     * @Author: liuyi
     * @Description: 下订单，根据支付的金钱决定订单
     * @Date: 2018/9/19_11:17
     * @param vo ChargingOrderInitVo
     * @return
     */
    Integer placeOrderByPrice(ChargingOrderInitVo vo);

}
