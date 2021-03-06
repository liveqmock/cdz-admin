package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.vo.admin.ChargingOrderSelectVo;
import com.ga.cdz.domain.vo.base.PageVo;

/**
 * @author huanghaohao
 * @date 2018-09-12 15:48
 * @desc 订单Service
 */
public interface IMChargingOrderService extends IService<ChargingOrder> {
    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-12 18:52
     * @desc 获取订单列表
     */
    Page<ChargingOrderDTO> getChargingOrderListPag(PageVo<ChargingOrderSelectVo> pageVo, String name);
}
