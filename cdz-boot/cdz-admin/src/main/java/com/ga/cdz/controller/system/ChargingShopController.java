package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingShopService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author:wanzhongsu
 * @description: 商户管理控制层
 * @date:2018/9/10 9:57
 */
@RestController
@RequestMapping("/chargingShop")
public class ChargingShopController extends AbstractBaseController {
    @Resource
    IMChargingShopService mChargingShopService;

    @PostMapping("/list")
    public Result getShopList(@RequestBody @Validated PageVo<ChargingShopVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        IPage<ChargingShop> iPage = mChargingShopService.getChargingShopList(vo);
        return Result.success().data(iPage);
    }
}

