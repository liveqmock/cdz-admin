package com.ga.cdz.domain.vo.base;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author:luqi
 * @description: 角色与权限关联接口
 * @date:2018/9/6_13:54
 */
@Data
@Accessors(chain = true)
public class AdminRolePermissionVo {

    /**
     * 角色关联ID
     */
    private Integer roleId;
    /**
     * 权限关联ID
     */
    private Integer permId;
}
