package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.vo.admin.ChargingOrderVo;
import com.ga.cdz.domain.vo.base.PageVo;

/**
 * @author huanghaohao
 * @date 2018-09-12 15:48
 * @desc 订单Service
 */
public interface IMChargingOrderService extends IService<ChargingOrder> {
  Page<ChargingOrderDTO> getChargingListPag(PageVo<ChargingOrderVo> pageVo);
}