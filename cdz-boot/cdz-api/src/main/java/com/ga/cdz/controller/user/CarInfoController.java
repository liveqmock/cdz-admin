package com.ga.cdz.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.group.api.IUserCarsInfoGroup;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserCarsInfoVo;
import com.ga.cdz.service.IUserCarsInfoService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author:luqi
 * @description: 我的—车辆管理
 * @date:2018/9/13_9:57
 */
@RestController
@RequestMapping("/car/info")
public class CarInfoController extends AbstractBaseController {


    @Resource
    IUserCarsInfoService userCarsInfoService;

    /**
     * @author:luqi
     * @description: 车辆分页
     * @date:2018/9/13_10:18
     * @param:
     * @return:
     */
    @PostMapping("/page")
    public Result getCarInfoPage(@RequestBody @Validated PageVo<UserCarsInfoVo> pageVo,
                                 BindingResult bindingResult) {
        checkParams(bindingResult);
        IPage<UserCarsInfo> page = userCarsInfoService.getUserCarsInfoPage(pageVo);
        return Result.success().data(page);
    }

    /**
     * @author:luqi
     * @description: 添加车辆
     * @date:2018/9/13_11:39
     * @param:
     * @return:
     */
    @PostMapping("/save")
    public Result saveCarInfo(@RequestBody @Validated({IUserCarsInfoGroup.Add.class}) UserCarsInfoVo userCarsInfoVo,
                              BindingResult bindingResult) {
        checkParams(bindingResult);
        userCarsInfoService.saveUserCarsInfo(userCarsInfoVo);
        return Result.success().message("添加车辆成功");
    }

    /**
     * @author:luqi
     * @description: 删除车辆
     * @date:2018/9/13_11:56
     * @param:
     * @return:
     */
    @PostMapping("/remove")
    public Result removeCarInfo(@RequestBody @Validated({IUserCarsInfoGroup.Remove.class}) UserCarsInfoVo userCarsInfoVo,
                                BindingResult bindingResult) {
        checkParams(bindingResult);
        userCarsInfoService.removeUserCarsInfoByCarNo(userCarsInfoVo);
        return Result.success().message("删除车辆成功");
    }

}
