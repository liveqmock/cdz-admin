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

/**
 * @author:wanzhongsu
 * @description: 充电站充电方式控制层
 * @date:2018/9/10 9:58
 */
@RestController
@RequestMapping("/charging/type")
public class ChargingTypeController extends AbstractBaseController {
    /**
     * 充电站充电方式服务
     */
    @Resource
    IMChargingTypeService mChargingTypeService;

    /**
     * @author:wanzhongsu
     * @description: 获取充电站充电方式列表
     * @date: 2018/9/10 9:59
     * @param:
     * @return: Result
     */
    @PostMapping("/list")
    public Result getChargingTypeList() {
        List<ChargingType> list = mChargingTypeService.getChargingTypeList();
        return Result.success().data(list);
    }

    /**
     * @author:wanzhongsu
     * @description: 充电站充电方式删除
     * @date: 2018/9/10 10:00
     * @param: ChargingTypeVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result deleteChargingType(@RequestBody @Validated(value = {IMChargingTypeGroup.Delete.class}) ChargingTypeVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        mChargingTypeService.removeChargingTypeById(vo);
        return Result.success().message("删除成功");
    }

    /**
     * @author:wanzhongsu
     * @description: 充电站充电方式保存
     * @date: 2018/9/10 10:01
     * @param: ChargingTypeVo
     * @return: Result
     */
    @PostMapping("/save")
    public Result saveChargingTypeObj(@RequestBody @Validated(value = {IMChargingTypeGroup.Add.class}) ChargingTypeVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        mChargingTypeService.saveChargingTypeObj(vo);
        return Result.success().message("保存成功");
    }
}
