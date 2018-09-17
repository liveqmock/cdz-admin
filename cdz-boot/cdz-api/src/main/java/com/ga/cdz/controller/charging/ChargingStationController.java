package com.ga.cdz.controller.charging;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.group.api.IChargingStationGroup;
import com.ga.cdz.domain.vo.api.ChargingStationVo;
import com.ga.cdz.service.IChargingStationService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/charging/station")
public class ChargingStationController extends AbstractBaseController {

    @Resource
    IChargingStationService iChargingStationService;

    @PostMapping("/detail")
    public Result getChargingStationDetail(@RequestBody @Validated(value = {IChargingStationGroup.Detail.class}) ChargingStationVo vo,
                                           BindingResult bindingResult) {

        checkParams(bindingResult);
        ChargingStation chargingStation = iChargingStationService.getChargingStationDetail(vo);
        return Result.success().data(chargingStation);
    }

}
