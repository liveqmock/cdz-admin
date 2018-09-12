package com.ga.cdz.controller.user;

import com.ga.cdz.annotation.UserState;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.group.api.IMyInfoGroup;
import com.ga.cdz.domain.vo.api.MyInfoVo;
import com.ga.cdz.service.IUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * @author:luqi
     * @description: 更新头像
     * @date:2018/9/12_10:01
     * @param: MultipartFile file对象
     * @param: 用户id
     * @return:
     */
    @UserState
    @PostMapping("/upload/avatar")
    public Result uploadAvatar(@RequestParam("avatar") MultipartFile file,
                               @RequestParam("userId") Integer userId) {
        /***判断文件是否存在*/
        if (ObjectUtils.isEmpty(file) || file.isEmpty()) {
            throw new BusinessException("上传头像为空");
        }
        if (ObjectUtils.isEmpty(userId)) {
            throw new BusinessException("userId为空");
        }
        UserInfo userInfo = userService.uploadAvatar(file, userId);
        return Result.success().message("头像上传成功").data(userInfo);
    }

    /**
     * @author:luqi
     * @description: 更新电话
     * @date:2018/9/12_14:14
     * @param:
     * @return:
     */
    @PostMapping("/update/tel")
    public Result updateTel(@RequestBody @Validated({IMyInfoGroup.UpdataTel.class}) MyInfoVo myInfoVo,
                            BindingResult bindingResult) {
        checkParams(bindingResult);
        userService.updateTel(myInfoVo);
        return Result.success().message("更新电话成功");
    }


    /**
     * @author:luqi
     * @description: 更新真实姓名
     * @date:2018/9/12_14:47
     * @param:
     * @return:
     */
    @PostMapping("/update/real/name")
    public Result updateRealName(@RequestBody @Validated({IMyInfoGroup.UpdateRealName.class}) MyInfoVo myInfoVo,
                                 BindingResult bindingResult) {
        checkParams(bindingResult);
        userService.updateRealName(myInfoVo);
        return Result.success().message("更新姓名成功");
    }


    /**
     * @author:luqi
     * @description: 更新昵称
     * @date:2018/9/12_14:49
     * @param:
     * @return:
     */
    @PostMapping("/update/nick/name")
    public Result updateNickName(@RequestBody @Validated({IMyInfoGroup.UpdateNickName.class}) MyInfoVo myInfoVo,
                                 BindingResult bindingResult) {
        checkParams(bindingResult);
        userService.updateNickName(myInfoVo);
        return Result.success().message("更新昵称成功");
    }

    /**
     * @author:luqi
     * @description: 更新密码
     * @date:2018/9/12_14:51
     * @param:
     * @return:
     */
    @PostMapping("/update/pwd")
    public Result updatePwd(@RequestBody @Validated({IMyInfoGroup.UpdatePwd.class}) MyInfoVo myInfoVo,
                            BindingResult bindingResult) {
        checkParams(bindingResult);
        userService.updatePwd(myInfoVo);
        return Result.success().message("更新密码成功");
    }



}
