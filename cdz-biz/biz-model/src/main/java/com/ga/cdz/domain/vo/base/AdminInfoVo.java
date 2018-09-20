package com.ga.cdz.domain.vo.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.group.admin.IMAdminInfoGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * @author:luqi
 * @description: AdminInfo的BaseVo
 * @date:2018/9/5_11:31
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class AdminInfoVo {
    /**
     * ID
     */
    @NotNull(groups = {IMAdminInfoGroup.Update.class, IMAdminInfoGroup.Remove.class}, message = "adminId不能为空")
    private Integer adminId;

    /**
     * 管理员姓名
     */
    @NotBlank(groups = {IMAdminInfoGroup.Add.class}, message = "姓名不能为空")
    @Size(groups = {IMAdminInfoGroup.Add.class}, min = 2, max = 10, message = "姓名，字符串长度2~10")
    private String adminName;

    /**
     * 管理员的账号
     */
    @NotBlank(groups = {IMAdminInfoGroup.Login.class, IMAdminInfoGroup.Add.class}, message = "管理员的账号不能为空")
    @Size(groups = {IMAdminInfoGroup.Login.class, IMAdminInfoGroup.Add.class}, min = 6, max = 12, message = "账号，字符串长度6~12")
    private String adminAccount;

    /**
     * 管理员密码MD5加密
     */
    @NotBlank(groups = {IMAdminInfoGroup.Login.class, IMAdminInfoGroup.Add.class, IMAdminInfoGroup.Login.class}, message = "登陆密码不能为空")
    @Size(groups = {IMAdminInfoGroup.Login.class, IMAdminInfoGroup.Add.class, IMAdminInfoGroup.Login.class}, min = 6, max = 12, message = "登陆密码，字符串长度6~12")
    private String adminPwd;

    /**
     * 电话
     */
    @NotBlank(groups = {IMAdminInfoGroup.Add.class}, message = "电话不能为空")
    @Pattern(groups = {IMAdminInfoGroup.Add.class}, regexp = RegexConstant.REGEX_PHONE, message = "电话格式不对")
    private String adminTel;

    /**
     * 性别 0 女 1男
     */
    @NotNull(groups = {IMAdminInfoGroup.Add.class}, message = "性别不能为空")
    private AdminInfo.AdminSex adminSex;

    /**
     * 平台状态 0 可用 1禁用
     */
    private AdminInfo.AdminState adminState;

    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

}
