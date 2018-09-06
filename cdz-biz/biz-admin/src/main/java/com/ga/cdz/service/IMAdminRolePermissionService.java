package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.AdminRolePermission;
import com.ga.cdz.domain.vo.base.AdminRolePermissionVo;

/**
 * @author:luqi
 * @description: 角色与权限 关联 接口
 * @date:2018/9/6_11:51
 */
public interface IMAdminRolePermissionService extends IService<AdminRolePermission> {

    /**
     * @author:luqi
     * @description: 初始化超级管理员的关联
     * @date:2018/9/6_13:57
     * @param:
     * @return: 布尔值
     */
    boolean initSuperAdminRolePermission();

}
