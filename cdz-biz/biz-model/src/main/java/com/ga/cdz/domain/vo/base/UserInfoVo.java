package com.ga.cdz.domain.vo.base;

import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.group.admin.IMUserInfoGroup;
import com.ga.cdz.domain.group.api.IUserInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author:luqi
 * @description: UserInfo vo基类
 * @date:2018/9/7_12:46
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 用户id
     */
    @NotNull(groups = {IMUserInfoGroup.updateMemSate.class},message ="更新会员用户状态时会员用户ID 必填" )
    private Integer userId;
    /**
     * 类型 1个人用户 2 单位
     */
    private UserInfo.UserType userType;
    /**
     * 用户真实姓名
     */
    private String userRealName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 电话
     */
    private String userTel;
    /**
     * 昵称
     */
    private String userNickName;
    /**
     * 性别 0 女 1男
     */
    private UserInfo.UserSex userSex;
    /**
     * 省级编码
     */
    private String province;
    /**
     * 城市编码
     */
    private String city;
    /**
     * 县编码
     */
    private String country;

    /**
     * 用户状态
     */
    @NotNull(groups = {IMUserInfoGroup.updateMemSate.class},message = "更新状态时 userState必填")
    private UserInfo.UserState userState;

    /**
     * 插入时间
     */
    private Date insertDt;

}
