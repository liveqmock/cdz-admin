package com.ga.cdz.controller.order;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingOrderCommentDTO;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.entity.ChargingOrderComment;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.domain.vo.admin.ChargingOrderCommentVo;
import com.ga.cdz.domain.vo.admin.ChargingOrderVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingOrderCommentService;
import com.ga.cdz.service.IMChargingOrderService;
import org.apache.shiro.SecurityUtils;
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
  IMChargingOrderService mChargingOrderService;//订单

  @Resource
  IMChargingOrderCommentService mChargingOrderCommentService;//订单评价
  /**
   * @author huanghaohao
   * @date 2018-09-12 14:07
   * @desc 订单列表
   */

  @PostMapping("/list")
  public Result getOrderListByConditionPage(@RequestBody PageVo<ChargingOrderVo> pageVo){
    String name = (String)SecurityUtils.getSubject().getPrincipal();
    Page<ChargingOrderDTO> page=mChargingOrderService.getChargingOrderListPag(pageVo,name);
    return Result.success().data(page);
  }

  /**
   * @author huanghaohao
   * @date 2018-09-12 20:04
   * @desc 订单评价列表
   * @param pageVo
   * @return
   */
  @PostMapping("/comment/list")
  public Result getChargingOrderList(@RequestBody PageVo<ChargingOrderCommentVo> pageVo){
    String name = (String)SecurityUtils.getSubject().getPrincipal();
    Page<ChargingOrderCommentDTO> page= mChargingOrderCommentService.getChargingOrderCommentListPage(pageVo,name);
    return Result.success().data(page);
  }
}
