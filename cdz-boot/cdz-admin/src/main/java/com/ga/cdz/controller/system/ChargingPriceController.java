package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingPriceDTO;
import com.ga.cdz.domain.entity.ChargingPrice;
import com.ga.cdz.domain.group.admin.IMChargingPriceGroup;
import com.ga.cdz.domain.vo.admin.ChargingPriceAddVo;
import com.ga.cdz.domain.vo.admin.ChargingPriceSelectVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingPriceService;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getPrivateChargingPage(@RequestBody @Validated PageVo<ChargingPriceSelectVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        if (ObjectUtils.isEmpty(vo.getData())) {
            vo.setData(new ChargingPriceSelectVo());
        }
        vo.getData().setPriceType(ChargingPrice.PriceType.PERSONAL);
        Page<ChargingPriceDTO> page = mChargingPriceService.getPageByType(vo);
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
    public Result getNonPrivateChargingPage(@RequestBody @Validated PageVo<ChargingPriceSelectVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        if (ObjectUtils.isEmpty(vo.getData())) {
            vo.setData(new ChargingPriceSelectVo());
        }
        vo.getData().setPriceType(ChargingPrice.PriceType.NONPERSONAL);
        Page<ChargingPriceDTO> page = mChargingPriceService.getPageByType(vo);
        return Result.success().data(page);
    }

    /**
     * @author:wanzhongsu
     * @description: 添加计费保存
     * @date: 2018/9/11 17:15
     * @param: ChargingPriceAddVo
     * @return: Result
     */
    @PostMapping("/list/save")
    public Result saveChargingPrice(@RequestBody @Validated(value = IMChargingPriceGroup.Add.class) ChargingPriceAddVo vo, BindingResult bindingResult) {
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
     * @param: ChargingPriceAddVo
     * @return: Result
     */
    @PostMapping("/list/update")
    public Result updateChargingPrice(@RequestBody @Validated(value = IMChargingPriceGroup.Update.class) ChargingPriceAddVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingPriceService.updateChargingPriceByKeys(vo);
        if (result) {
            return Result.success().message("修改成功");
        }
        return Result.success().message("修改失败");
    }
}

