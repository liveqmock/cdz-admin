package com.ga.cdz.controller.charging;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.api.ChargingStationCommentDTO;
import com.ga.cdz.domain.dto.api.ChargingStationDetailDTO;
import com.ga.cdz.domain.dto.api.ChargingStationPageDTO;
import com.ga.cdz.domain.dto.api.ChargingStationTerminalDTO;
import com.ga.cdz.domain.group.api.IChargingStationGroup;
import com.ga.cdz.domain.vo.api.ChargingStationDetailVo;
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

@RestController
@RequestMapping("/charging/station")
public class ChargingStationController extends AbstractBaseController {

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
    public Result getMainChargingPageList(@RequestBody @Validated({IChargingStationGroup.MainPage.class})
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
    public Result getNearChargingPageList(@RequestBody @Validated({IChargingStationGroup.NearPage.class})
                                                  ChargingStationPageVo chargingStationPageVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingStationPageDTO> list = chargingStationService.getNearStationPage(chargingStationPageVo);
        return Result.success().data(list);
    }

    /**
     * @Author: liuyi
     * @Description: 充电站详情
     * @Date: 2018/9/18_9:41
     * @param vo ChargingStationDetailVo
     * @return Result
     */
    @PostMapping("/detail")
    public Result getChargingStationDetail(@RequestBody @Validated(value = {IChargingStationGroup.Detail.class}) ChargingStationDetailVo vo,
                                           BindingResult bindingResult) {
        checkParams(bindingResult);
        ChargingStationDetailDTO chargingStationDetailDTO = chargingStationService.getChargingStationDetail(vo);
        return Result.success().data(chargingStationDetailDTO);
    }

    /**
     * @Author: liuyi
     * @Description: 充电站终端
     * @Date: 2018/9/18_9:41
     * @param vo ChargingStationDetailVo
     * @return Result
     */
    @PostMapping("/terminal")
    public Result getChargingStationTerminal(@RequestBody @Validated(value = {IChargingStationGroup.Terminal.class}) ChargingStationDetailVo vo,
                                             BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingStationTerminalDTO> ChargingStationTerminal = chargingStationService.getChargingStationTerminal(vo);
        return Result.success().data(ChargingStationTerminal);
    }

    /**
     * @Author: liuyi
     * @Description: 充电站评价
     * @Date: 2018/9/18_9:41
     * @param vo ChargingStationDetailVo
     * @return Result
     */
    @PostMapping("/comment")
    public Result getChargingStationComment(@RequestBody @Validated(value = {IChargingStationGroup.Comment.class}) ChargingStationDetailVo vo,
                                            BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingStationCommentDTO> chargingStationComment = chargingStationService.getChargingStationComment(vo);
        return Result.success().data(chargingStationComment);
    }

}
