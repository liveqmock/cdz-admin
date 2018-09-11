package com.ga.cdz.domain.vo.base;

import com.ga.cdz.domain.group.api.IUserSmsPushGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author:luqi
 * @description: UserSmsPush 基类Vo
 * @date:2018/9/10_16:54
 */
@Data
@Accessors(chain = true)
public class UserSmsPushVo {
    /**
     * 用户ID
     */
    @NotNull(groups = {IUserSmsPushGroup.Register.class}, message = "userId不能为空")
    private Integer userId;

    /**
     * 设备标签
     */
    @NotBlank(groups = {IUserSmsPushGroup.Register.class}, message = "设备标签不能为空")
    @Size(min = 1, max = 50, message = "设备标签字段不合法")
    private String pushTag;

    /**
     * 设备别名
     */
    private String pushAlias;

    /**
     * 推送注册ID
     */
    @NotBlank(groups = {IUserSmsPushGroup.Register.class}, message = "推送注册ID不能为空")
    @Size(min = 1, max = 50, message = "推送注册ID字段不合法")
    private String pushRegid;


}
