package com.ga.cdz.controller.system;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.ChargingStationType;
import com.ga.cdz.domain.group.admin.IMChargingStationTypeGroup;
import com.ga.cdz.domain.vo.base.ChargingStationTypeVo;
import com.ga.cdz.service.IMChargingStationTypeService;
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
 * @description: 充电站运营类型控制层
 * @date:2018/9/10 10:09
 */
@RestController
@RequestMapping("/sttpe")
public class SttpeController extends AbstractBaseController {
    /**
     * 充电站运营类型服务
     */
    @Resource
    IMChargingStationTypeService mChargingStationTypeService;

    /**
     * @author:wanzhongsu
     * @description: 保存充电站运营类型
     * @date: 2018/9/10 10:09
     * @param: ChargingStationTypeVo
     * @return: Result
     */
    @PostMapping("/save")
    public Result saveStpe(@RequestBody @Validated(value = {IMChargingStationTypeGroup.add.class}) ChargingStationTypeVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer integer = mChargingStationTypeService.saveCST(vo);
        if (integer > 0) {
            return Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 充电站运营类型删除
     * @date: 2018/9/10 10:10
     * @param: ChargingStationTypeVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result deleteStpe(@RequestBody @Validated(value = {IMChargingStationTypeGroup.delete.class}) ChargingStationTypeVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Integer integer = mChargingStationTypeService.removeCSTById(vo);
        if (integer > 0) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 获取充电站运营类型列表
     * @date: 2018/9/10 10:10
     * @param:
     * @return: Result
     */
    @PostMapping("/list")
    public Result listStpe() {
        List<ChargingStationType> list = mChargingStationTypeService.getCSTList();
        return Result.success().data(list);
    }
}
