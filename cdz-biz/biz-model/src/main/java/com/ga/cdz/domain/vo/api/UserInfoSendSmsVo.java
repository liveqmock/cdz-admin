package com.ga.cdz.domain.vo.api;

import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author:luqi
 * @description: 发送验证码Vo，用于注册时发送短信与找回密码发送短信
 * @date:2018/9/7_13:21
 */
@Data
@Accessors(chain = true)
public class UserInfoSendSmsVo {

    @NotBlank(groups = {IUserInfoGroup.SendSms.class}, message = "用户电话不能为空")
    @Pattern(regexp = RegexConstant.REGEX_PHONE, message = "电话格式不对")
    private String userTel;

}
