package com.ga.cdz.domain.vo.api;

import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author:luqi
 * @description: 客户端注册Vo
 * @date:2018/9/7_13:00
 */
@Data
@Accessors(chain = true)
public class UserInfoRegisterVo {

    /**
     * 电话
     */
    @NotBlank(groups = {IUserInfoGroup.Register.class}, message = "电话不能为空")
    @Pattern(regexp = RegexConstant.REGEX_PHONE,
            groups = {IUserInfoGroup.Register.class}, message = "电话格式不对")
    private String userTel;

    /**
     * 密码
     */
    @NotBlank(groups = {IUserInfoGroup.Register.class}, message = "密码不能为空")
    @Pattern(regexp = RegexConstant.REGEX_USERNAME_PASSWORD,
            groups = {IUserInfoGroup.Register.class}, message = "密码为大小写字母，数字与下划线组合，6~12位")
    private String userPwd;


    /**
     * 性别 0 女 1男
     */
    @NotNull(groups = {IUserInfoGroup.Register.class}, message = "性别为空")
    private UserInfo.UserSex userSex;

    /**
     * 短信验证码
     */
    @NotBlank(groups = {IUserInfoGroup.Register.class}, message = "短信验证码为空")
    @Size(groups = {IUserInfoGroup.Register.class}, min = 4, max = 4, message = "短信验证码为4位")
    private String smsCode;
}
