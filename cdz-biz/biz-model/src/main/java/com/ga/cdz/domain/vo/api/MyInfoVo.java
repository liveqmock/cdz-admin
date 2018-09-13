package com.ga.cdz.domain.vo.api;

import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.group.api.IMyInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author:luqi
 * @description: 我的页面请求 vo
 * @date:2018/9/11_15:41
 */
@Data
@Accessors(chain = true)
public class MyInfoVo {

    /**
     * 用户id
     */
    @NotNull(groups = {IMyInfoGroup.Get.class, IMyInfoGroup.UpdateTel.class,
            IMyInfoGroup.UpdateRealName.class, IMyInfoGroup.UpdateNickName.class, IMyInfoGroup.UpdateTelSms.class},
            message = "用户id不能为空")
    private Integer userId;

    /**
     * 用户电话
     */
    @NotBlank(groups = {IMyInfoGroup.UpdateTelSms.class, IMyInfoGroup.UpdateTel.class}, message = "用户电话不能为空")
    @Pattern(groups = {IMyInfoGroup.UpdateTelSms.class, IMyInfoGroup.UpdateTel.class},
            regexp = RegexConstant.REGEX_PHONE, message = "用户电话不能为空")
    private String userTel;

    /**
     * 用户昵称
     */
    @NotBlank(groups = {IMyInfoGroup.UpdateNickName.class}, message = "用户昵称不能为空")
    @Size(min = 1, max = 20, message = "昵称太长")
    private String userNickName;

    /**
     * 真实姓名
     */
    @NotBlank(groups = {IMyInfoGroup.UpdateRealName.class}, message = "真实姓名不能为空")
    @Size(min = 1, max = 10, message = "真实姓名太长")
    private String userRealName;

    /**
     * 密码
     */
    @NotBlank(groups = {IMyInfoGroup.UpdatePwd.class}, message = "密码不能为空")
    @Pattern(regexp = RegexConstant.REGEX_USERNAME_PASSWORD,
            groups = {IMyInfoGroup.UpdatePwd.class}, message = "密码为大小写字母，数字与下划线组合，6~12位")
    private String userPwd;

    /**
     * 老密码
     */
    @NotBlank(groups = {IMyInfoGroup.UpdatePwd.class}, message = "密码不能为空")
    @Pattern(regexp = RegexConstant.REGEX_USERNAME_PASSWORD,
            groups = {IMyInfoGroup.UpdatePwd.class}, message = "密码为大小写字母，数字与下划线组合，6~12位")
    private String oldUserPwd;


    /**
     * 用户短信
     */
    @NotBlank(groups = {IMyInfoGroup.UpdateTel.class}, message = "短信不能为空")
    @Size(groups = {IMyInfoGroup.UpdateTel.class}, min = 4, max = 4, message = "短信验证码为4位")
    private String smsCode;
}
