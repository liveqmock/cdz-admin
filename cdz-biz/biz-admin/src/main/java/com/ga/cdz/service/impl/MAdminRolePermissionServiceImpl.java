package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminPermissionMapper;
import com.ga.cdz.dao.center.AdminRolePermissionMapper;
import com.ga.cdz.domain.entity.AdminPermission;
import com.ga.cdz.domain.entity.AdminRolePermission;
import com.ga.cdz.service.IMAdminRolePermissionService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void initSuperAdminRolePermission() {
        //查询所有的权限
        List<AdminPermission> adminPermissionList = adminPermissionMapper.selectList(null);
        if (!ObjectUtils.isEmpty(adminPermissionList)) {
            /**删除原有的权限*/
            baseMapper.delete(new QueryWrapper<AdminRolePermission>().lambda()
                    .eq(AdminRolePermission::getRoleId, 1));
            List<AdminRolePermission> rsList = Lists.newArrayList();
            for (AdminPermission adminPermission : adminPermissionList) {
                AdminRolePermission item = new AdminRolePermission().setRoleId(1)
                        .setPermId(adminPermission.getPermId());
                /***关联表中没有主键ID，不能使用批量方法*/
                rsList.add(item);
            }
            baseMapper.insertBatch(rsList);
            log.info("超级管理员权限绑定初始化");
        }

    }
}
