package com.ga.cdz.controller;

import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
import com.ga.cdz.domain.group.admin.IMChargingShopGroup;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.service.IMChargingShopService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author:luqi
 * @description: 商家端登陆与认证
 * @date:2018/9/19_11:35
 */
@RestController
@RequestMapping("/account")
public class AccountController extends AbstractBaseController {
    @Resource
    IMChargingShopService mChargingShopService;


  /**
   * @author huanghaohao
   * @date 2018年9月19日 13点52分
   * @
   * @param chargingShopVo
   * @param bindingResult
   * @return
   */

  @PostMapping("/login")
  public Result login(@RequestBody @Validated({IMChargingShopGroup.login.class})ChargingShopVo chargingShopVo , BindingResult bindingResult){
    checkParams(bindingResult);
    ChargingShopDTO chargingShopDTO=mChargingShopService.login(chargingShopVo);
    return Result.success().message("登陆成功").data(chargingShopDTO);
  }
}
