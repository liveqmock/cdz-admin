package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.group.admin.IMChargingStationGroup;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingStationService;
import io.lettuce.core.models.role.RedisSlaveInstance;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站列表管理
 * @date:2018/9/10 15:01
 */
@RestController
@RequestMapping("/chargingstation")
public class ChargingStationController extends AbstractBaseController {
    /**
     * 充电站管理服务
     */
    @Resource
    IMChargingStationService mChargingStationService;

    /**
     * @author:wanzhongsu
     * @description: 分页获取充电站列表信息
     * @date: 2018/9/10 16:26
     * @param: PageVo
     * @return: Result
     */
    @PostMapping("/list")
    public Result getStationList(@RequestBody @Validated PageVo<ChargingStationVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        IPage<ChargingStation> iPage = mChargingStationService.getStationList(vo);
        return Result.success().data(iPage);
    }

    /**
     * @author:wanzhongsu
     * @description: 添加充电站信息
     * @date: 2018/9/10 16:27
     * @param: ChargingStationVo
     * @return: Result
     */
    @PostMapping("/add")
    public Result saveStation(@RequestBody @Validated(value = IMChargingStationGroup.add.class) ChargingStationVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingStationService.saveStation(vo);

        if (result) {
         return    Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 删除充电站信息
     * @date: 2018/9/10 16:27
     * @param: ChargingStationVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result deleteStation(@RequestBody @Validated(value = IMChargingStationGroup.delete.class) ChargingStationVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingStationService.deleteStationById(vo);
        if (result) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 修改充电站信息
     * @date: 2018/9/10 16:28
     * @param: ChargingStationVo
     * @return: Result
     */
    @PostMapping("/update")
    public Result updateStation(@RequestBody @Validated(value = IMChargingStationGroup.update.class) ChargingStationVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingStationService.updateStationById(vo);
        if (result) {
            return Result.success().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

}

