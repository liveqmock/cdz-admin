package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingPriceDTO;
import com.ga.cdz.domain.group.admin.IMChargingPriceGroup;
import com.ga.cdz.domain.vo.admin.ChargingPriceVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingPriceService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author:wanzhongsu
 * @description: 充电费用计费标准控制层
 * @date:2018/9/11 10:26
 */
@RestController
@RequestMapping("/charging/price")
public class ChargingPriceController extends AbstractBaseController {
    /**
     * 计费标准服务
     */
    @Resource
    IMChargingPriceService mChargingPriceService;

    /**
     * @author:wanzhongsu
     * @description: 获取专用场费用列表
     * @date: 2018/9/11 17:14
     * @param: PageVo
     * @return: Result
     */
    @PostMapping("/list/private")
    public Result getPrivateChargingPage(@RequestBody @Validated PageVo<ChargingPriceVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer myPriceType = 1;
        Page<ChargingPriceDTO> page = mChargingPriceService.getPageByType(vo, myPriceType);
        return Result.success().data(page);
    }

    /**
     * @author:wanzhongsu
     * @description: 获取非专用场计费列表信息
     * @date: 2018/9/11 17:15
     * @param: PageVo
     * @return: Result
     */
    @PostMapping("/list/noprivate")
    public Result getNonPrivateChargingPage(@RequestBody @Validated PageVo<ChargingPriceVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer myPriceType = 2;
        Page<ChargingPriceDTO> page = mChargingPriceService.getPageByType(vo, myPriceType);
        return Result.success().data(page);
    }

    /**
     * @author:wanzhongsu
     * @description: 添加计费保存
     * @date: 2018/9/11 17:15
     * @param: ChargingPriceVo
     * @return: Result
     */
    @PostMapping("/list/save")
    public Result saveChargingPrice(@RequestBody @Validated(value = IMChargingPriceGroup.Add.class) ChargingPriceVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingPriceService.saveChargingPriceByKeys(vo);
        if (result) {
            return Result.success().message("保存成功");
        }
        return Result.success().message("保存失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 修改计费
     * @date: 2018/9/11 17:16
     * @param: ChargingPriceVo
     * @return: Result
     */
    @PostMapping("/list/update")
    public Result updateChargingPrice(@RequestBody @Validated(value = IMChargingPriceGroup.Update.class) ChargingPriceVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingPriceService.updateChargingPriceByKeys(vo);
        if (result) {
            return Result.success().message("修改成功");
        }
        return Result.success().message("修改失败");
    }
}

