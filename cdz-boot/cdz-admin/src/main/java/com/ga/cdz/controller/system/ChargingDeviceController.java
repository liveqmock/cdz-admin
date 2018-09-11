package com.ga.cdz.controller.system;


import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingDeviceDTO;
import com.ga.cdz.domain.group.admin.IMChargingDeviceGroup;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.service.IMChargingDeviceService;
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
  IMChargingDeviceService mChargingDeviceService;

  /**
   * @author huanghaohao
   * @date 2018-09-11 15:25
   * @desc 插入新的充电桩
   * @param chargingDeviceVo
   * @param bindingResult
   * @return
   */
  @PostMapping("/insert")
  public Result insertDevice(@RequestBody @Validated(value = {IMChargingDeviceGroup.insert.class})ChargingDeviceVo chargingDeviceVo, BindingResult bindingResult){
    checkParams(bindingResult);
    mChargingDeviceService.insertNewChargingDevice(chargingDeviceVo);
    return Result.success();
  }

  /**
   * @author huanghaohao
   * @date 2018-09-11 15:36
   * @desc 充电桩列表查询
   * @return
   */
  @PostMapping("/list")
  public Result getChargingDeviceList(@RequestBody @Validated(value = {IMChargingDeviceGroup.list.class})ChargingDeviceVo chargingDeviceVo, BindingResult bindingResult){
    checkParams(bindingResult);
    List<ChargingDeviceDTO> list=mChargingDeviceService.getChargingDeviceList(chargingDeviceVo);
    return Result.success().data(list);
  }

}
