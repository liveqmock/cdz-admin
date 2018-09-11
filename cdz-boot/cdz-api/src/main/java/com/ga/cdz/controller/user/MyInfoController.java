package com.ga.cdz.controller.user;

import com.ga.cdz.annotation.UserState;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.group.api.IMyInfoGroup;
import com.ga.cdz.domain.vo.api.MyInfoVo;
import com.ga.cdz.service.IUserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author:luqi
 * @description: 我的模块 基础信息 控制层
 * @date:2018/9/11_13:35
 */
@RestController
@RequestMapping("/my/info")
public class MyInfoController extends AbstractBaseController {

    /**
     * 用户服务
     */
    @Resource
    IUserService userService;

    /**
     * @author:luqi
     * @description: 获取用户的信息，同界面上一致，判断是否冻结
     * @date:2018/9/11_13:38
     * @param:
     * @return:
     */
    @UserState
    @PostMapping("/get/id")
    public Result getMyInfoById(@RequestBody @Validated({IMyInfoGroup.Get.class}) MyInfoVo myInfoVo,
                                BindingResult bindingResult) {
        checkParams(bindingResult);
        MyInfoDTO myInfoDTO = userService.getMyInfoDTOById(myInfoVo.getUserId());
        return Result.success().data(myInfoDTO);
    }


}
