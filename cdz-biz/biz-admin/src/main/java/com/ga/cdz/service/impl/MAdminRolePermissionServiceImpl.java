package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminRolePermissionMapper;
import com.ga.cdz.domain.entity.AdminRolePermission;
import com.ga.cdz.service.IMAdminRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author:luqi
 * @description: 角色与权限 关联 接口 实现类
 * @date:2018/9/6_11:53
 */
@Slf4j
@Service("mAdminRolePermissionService")
public class MAdminRolePermissionServiceImpl extends ServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements IMAdminRolePermissionService {
}
