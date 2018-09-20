package com.ga.cdz.controller.system;


import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingDeviceDTO;
import com.ga.cdz.domain.dto.admin.ChargingDeviceSubDTO;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.group.admin.IMChargingDeviceGroup;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.service.IMChargingDeviceService;
import com.ga.cdz.service.IMChargingDeviceSubService;
import com.ga.cdz.service.IMChargingTypeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-11 13:54
 * @desc 充电桩
 */
@RestController
@RequestMapping("/charging/device")
public class ChargingDeviceController extends AbstractBaseController {
    @Resource
    IMChargingDeviceService mChargingDeviceService;//充电桩Service
    @Resource
    IMChargingTypeService mChargingTypeService;//充电类型Service
    @Resource
    IMChargingDeviceSubService mChargingDeviceSubService;//充电枪

    /**
     * @param chargingDeviceVo
     * @param bindingResult
     * @return
     * @author huanghaohao
     * @date 2018-09-11 15:25
     * @desc 插入新的充电桩
     */
    @PostMapping("/insert")
    public Result insertDevice(@RequestBody @Validated(value = {IMChargingDeviceGroup.insert.class}) ChargingDeviceVo chargingDeviceVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        mChargingDeviceService.insertNewChargingDevice(chargingDeviceVo);
        return Result.success();
    }

    /**
     * @return
     * @author huanghaohao
     * @date 2018-09-11 15:36
     * @desc 充电桩列表查询
     */
    @PostMapping("/list")
    public Result getChargingDeviceList(@RequestBody @Validated(value = {IMChargingDeviceGroup.list.class}) ChargingDeviceVo chargingDeviceVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingDeviceDTO> list = mChargingDeviceService.getChargingDeviceList(chargingDeviceVo);
        return Result.success().data(list);
    }

    /**
     * @return
     * @author huanghaohao
     * @date 2018-09-11 17:59
     * @desc 返回所有的充电方式
     */
    @PostMapping("/charging/type/list")
    public Result getChargingType() {
        List<ChargingType> list = mChargingTypeService.getChargingTypeList();
        return Result.success().data(list);
    }

    /**
     * @param chargingDeviceVo
     * @param bindingResult
     * @return
     * @author huanghaohao
     * @date 2018-09-12 11:38
     * @desc 获取充电枪列表
     */
    @PostMapping("/sub/list")
    public Result getChargingDeviceSubListByDeviceId(@RequestBody @Validated(value = {IMChargingDeviceGroup.subList.class}) ChargingDeviceVo chargingDeviceVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<ChargingDeviceSubDTO> list = mChargingDeviceSubService.getChargingDeviceSubList(chargingDeviceVo);
        return Result.success().data(list);
    }
}
