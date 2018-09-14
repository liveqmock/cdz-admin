package com.ga.cdz.controller.charging;

import com.ga.cdz.annotation.UserState;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.vo.api.ChargingOrderVo;
import com.ga.cdz.service.IChargingOrderService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: liuyi
 * @Description: 充电订单
 * @Date: 2018/9/14_14:09
 */
@RestController
@RequestMapping("/charging/order")
public class ChargingOrderController extends AbstractBaseController {

    @Resource
    IChargingOrderService chargingOrderService;

    @UserState
    @PostMapping("/price")
    public Result placeOrderByPrice(@RequestBody ChargingOrderVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Object object = chargingOrderService.placeOrderByPrice(vo);
        return Result.success().data(object);
    }

}
