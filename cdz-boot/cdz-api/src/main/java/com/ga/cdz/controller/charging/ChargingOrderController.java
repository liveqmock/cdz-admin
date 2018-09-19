package com.ga.cdz.controller.charging;

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
@RequestMapping("/order")
public class ChargingOrderController extends AbstractBaseController {

    @Resource
    IChargingOrderService chargingOrderService;

    /**
     * @Author: liuyi
     * @Description: 获取全部订单的分页信息
     * @Date: 2018/9/19_13:43
     * @param vo ChargingOrderPageListVo
     * @return
     */
    @PostMapping("/all/list")
    public Result getOrderListOfAllPageList(@RequestBody ChargingOrderPageListVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderService.getChargingOrderOfAllPageList(vo);
        return Result.success().data(chargingOrderListDTOList);
    }

    /**
     * @Author: liuyi
     * @Description: 获取待使用订单的分页信息
     * @Date: 2018/9/19_14:55
     * @param vo ChargingOrderPageListVo
     * @return
     */
    @PostMapping("/init/list")
    public Result getOrderListOfInitPageList(@RequestBody ChargingOrderPageListVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderService.getChargingOrderOfInitPageList(vo);
        return Result.success().data(chargingOrderListDTOList);
    }

    /**
     * @Author: liuyi
     * @Description: 获取待支付订单的分页信息
     * @Date: 2018/9/19_14:55
     * @param vo ChargingOrderPageListVo
     * @return
     */
    @PostMapping("/paying/list")
    public Result getOrderListOfPayingPageList(@RequestBody ChargingOrderPageListVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderService.getOrderListOfPayingPageList(vo);
        return Result.success().data(chargingOrderListDTOList);
    }

    /**
     * @Author: liuyi
     * @Description: 获取待评价订单的分页信息
     * @Date: 2018/9/19_14:57
     * @param vo ChargingOrderPageListVo
     * @return
     */
    @PostMapping("/payed/list")
    public Result getOrderListOfPayedPageList(@RequestBody ChargingOrderPageListVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderService.getOrderListOfPayedPageList(vo);
        return Result.success().data(chargingOrderListDTOList);
    }

    /**
     * @Author: liuyi
     * @Description: 获取退款/售后订单的分页信息
     * @Date: 2018/9/19_15:00
     * @param vo ChargingOrderPageListVo
     * @return
     */
    @PostMapping("/refunding/list")
    public Result getOrderListOfRefundingPageList(@RequestBody ChargingOrderPageListVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderService.getOrderListOfRefundingPageList(vo);
        return Result.success().data(chargingOrderListDTOList);
    }

    /**
     * @Author: liuyi
     * @Description: 下单
     * @Date: 2018/9/19_13:44
     * @param vo ChargingOrderInitVo
     * @return
     */
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
