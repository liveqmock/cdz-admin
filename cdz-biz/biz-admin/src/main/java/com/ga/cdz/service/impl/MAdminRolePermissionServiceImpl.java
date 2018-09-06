package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminPermissionMapper;
import com.ga.cdz.dao.center.AdminRolePermissionMapper;
import com.ga.cdz.domain.entity.AdminPermission;
import com.ga.cdz.domain.entity.AdminRolePermission;
import com.ga.cdz.service.IMAdminRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author:luqi
 * @description: 角色与权限 关联 接口 实现类
 * @date:2018/9/6_11:53
 */
@Slf4j
@Service("mAdminRolePermissionService")
public class MAdminRolePermissionServiceImpl extends ServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements IMAdminRolePermissionService {

    @Resource
    AdminPermissionMapper adminPermissionMapper;

    @Override
    public boolean initSuperAdminRolePermission() {
        //查询所有的权限
        List<AdminPermission> adminPermissionList = adminPermissionMapper.selectList(null);
        //if (ObjectUtils.isEmpty())
        return false;
    }
}
