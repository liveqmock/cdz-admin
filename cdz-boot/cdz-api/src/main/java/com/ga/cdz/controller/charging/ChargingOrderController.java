package com.ga.cdz.controller.charging;

import com.ga.cdz.annotation.UserState;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.api.ChargingOrderListDTO;
import com.ga.cdz.domain.vo.api.ChargingOrderInitVo;
import com.ga.cdz.domain.vo.api.ChargingOrderPageListVo;
import com.ga.cdz.service.IChargingOrderService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/list")
    public Result getOrderListPageList(@RequestBody ChargingOrderPageListVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderService.getChargingOrderPageList(vo);
        return Result.success().data(chargingOrderListDTOList);
    }

    @UserState
    @PostMapping("/price")
    public Result placeOrderByPrice(@RequestBody ChargingOrderInitVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer result = chargingOrderService.placeOrderByPrice(vo);
        if (result > 0) {
            return Result.success().message("下单成功");
        }
        return Result.fail().message("下单失败");
    }

}
