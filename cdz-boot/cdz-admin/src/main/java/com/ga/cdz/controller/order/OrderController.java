package com.ga.cdz.controller.order;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.domain.vo.admin.ChargingOrderVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huanghaohao
 * @date 2018-09-12 14:05
 * @desc 订单controller
 */


@RestController
@RequestMapping("/charging/order")
public class OrderController extends AbstractBaseController {
  @Resource
  IMChargingOrderService mChargingOrderService;
  /**
   * @author huanghaohao
   * @date 2018-09-12 14:07
   * @desc 订单列表
   */

  @PostMapping("/list")
  public Result getOrderListByConditionPage(@RequestBody PageVo<ChargingOrderVo> pageVo){
    Page<ChargingOrderDTO> page=mChargingOrderService.getChargingListPag(pageVo);
    return Result.success().data(page);
  }

}
