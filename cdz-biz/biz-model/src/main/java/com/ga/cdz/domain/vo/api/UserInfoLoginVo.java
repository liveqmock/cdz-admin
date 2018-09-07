package com.ga.cdz.domain.vo.api;

import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author:luqi
 * @description: 客户端用户登录
 * @date:2018/9/7_15:20
 */
@Data
@Accessors(chain = true)
public class UserInfoLoginVo {

    /**
     * 用户电话
     */
    @NotBlank(groups = {IUserInfoGroup.Login.class}, message = "电话不能为空")
    @Pattern(regexp = RegexConstant.REGEX_PHONE,
            groups = {IUserInfoGroup.Login.class}, message = "电话格式错误")
    private String userTel;

    /**
     * 密码
     */
    @NotBlank(groups = {IUserInfoGroup.Login.class}, message = "密码不能为空")
    @Pattern(regexp = RegexConstant.REGEX_USERNAME_PASSWORD,
            groups = {IUserInfoGroup.Login.class}, message = "密码为大小写字母，数字与下划线组合，6~12位")
    private String userPwd;
}
