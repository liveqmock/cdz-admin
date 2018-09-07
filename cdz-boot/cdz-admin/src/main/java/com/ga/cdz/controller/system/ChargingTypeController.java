package com.ga.cdz.controller.system;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.group.admin.IMChargingTypeGroup;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;
import com.ga.cdz.service.IMChargingTypeService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chargingtype")
public class ChargingTypeController extends AbstractBaseController {
    @Resource
    IMChargingTypeService mChargingTypeService;

    @PostMapping("/list")
    public Result getChargingTypeList() {
        List<ChargingType> list = mChargingTypeService.getChargingTypeList();
        return Result.success().data(list);
    }

    @PostMapping("/delete")
    public Result deleteChargingType(@RequestBody @Validated(value = {IMChargingTypeGroup.delete.class}) ChargingTypeVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer integer = mChargingTypeService.removeChargingTypeById(vo);
        if (integer > 0) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    @PostMapping("/save")
    public Result saveChargingTypeObj(@RequestBody @Validated(value = {IMChargingTypeGroup.add.class}) ChargingTypeVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer integer = mChargingTypeService.saveChargingTypeObj(vo);
        if (integer > 0) {
            return Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
    }
}
