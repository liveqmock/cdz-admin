package com.ga.cdz.domain.vo.base;


import com.ga.cdz.domain.group.admin.IMAdminRoleGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author:luqi
 * @description: AdminRole的BaseVo
 * @date:2018/9/6_11:27
 */
@Data
@Accessors(chain = true)
public class AdminRoleVo {
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色姓名
     */
    @NotBlank(groups = {IMAdminRoleGroup.Add.class}, message = "角色姓名必填")
    private String roleName;
}
