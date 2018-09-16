package com.ga.cdz.controller.charging;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.api.ChargingStationPageDTO;
import com.ga.cdz.domain.group.api.ICharginStationGroup;
import com.ga.cdz.domain.vo.api.ChargingStationPageVo;
import com.ga.cdz.service.IChargingStationService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:luqi
 * @description: 获取充电桩 信息 控制层
 * @date:2018/9/13_15:08
 */
@RestController
@RequestMapping("/charging")
public class ChargingInfoController extends AbstractBaseController {

    @Resource
    IChargingStationService chargingStationService;

    /**
     * @author:luqi
     * @description: 获取主页的充电桩数据
     * @date:2018/9/13_17:46
     * @param:
     * @return:
     */
    @PostMapping("/main/list")
    public Result getMainChargingPageList(@RequestBody @Validated({ICharginStationGroup.MainPage.class})
                                                  ChargingStationPageVo chargingStationPageVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingStationPageDTO> list = chargingStationService.getMainStationPage(chargingStationPageVo);
        return Result.success().data(list);
    }

    /**
     * @author:luqi
     * @description: 获取附近
     * @date:2018/9/13_17:46
     * @param:
     * @return:
     */
    @PostMapping("/near/list")
    public Result getNearChargingPageList(@RequestBody @Validated({ICharginStationGroup.NearPage.class})
                                                  ChargingStationPageVo chargingStationPageVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingStationPageDTO> list = chargingStationService.getNearStationPage(chargingStationPageVo);
        return Result.success().data(list);
    }


}
