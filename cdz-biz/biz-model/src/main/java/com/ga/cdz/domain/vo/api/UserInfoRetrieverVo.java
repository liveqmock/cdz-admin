package com.ga.cdz.domain.vo.api;

import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/8_16:15
 */
@Data
@Accessors(chain = true)
public class UserInfoRetrieverVo {

    /**
     * 电话
     */
    @NotBlank(groups = {IUserInfoGroup.Retriever.class}, message = "电话不能为空")
    @Pattern(regexp = RegexConstant.REGEX_PHONE,
            groups = {IUserInfoGroup.Retriever.class}, message = "电话格式不对")
    private String userTel;

    /**
     * 密码
     */
    @NotBlank(groups = {IUserInfoGroup.Retriever.class}, message = "密码不能为空")
    @Pattern(regexp = RegexConstant.REGEX_USERNAME_PASSWORD,
            groups = {IUserInfoGroup.Retriever.class}, message = "密码为大小写字母，数字与下划线组合，6~12位")
    private String userPwd;

    /**
     * 短信验证码
     */
    @NotBlank(groups = {IUserInfoGroup.Retriever.class}, message = "短信验证码为空")
    @Size(groups = {IUserInfoGroup.Retriever.class}, min = 4, max = 4, message = "短信验证码为4位")
    private String smsCode;
}
