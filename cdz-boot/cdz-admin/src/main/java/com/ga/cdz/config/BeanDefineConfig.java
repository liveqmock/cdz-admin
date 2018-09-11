package com.ga.cdz.config;

import com.ga.cdz.domain.entity.AdminPermission;
import com.ga.cdz.service.IMAdminPermissionService;
import com.ga.cdz.service.IMAdminRolePermissionService;
import com.google.common.collect.Lists;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author:luqi
 * @description: spring启动回调类，1-权限模块初始化 2-超级管理员账号权限初始化
 * @date:2018/9/3_21:36
 */
@Component
public class BeanDefineConfig implements ApplicationListener<ApplicationContextEvent> {

    @Resource
    IMAdminPermissionService mAdminPermissionService;

    @Resource
    IMAdminRolePermissionService mAdminRolePermissionService;

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
        if (applicationContextEvent.getApplicationContext().getParent() == null) {
            initAdmin();
            superAdminPermission();
        }
    }

    /**
     * @author:luqi
     * @description:  后台管理模块权限初始化
     * @date:2018/9/3_21:39
     * @param:
     * @return:
     */
    private void initAdmin(){
        //后台管理模块
        AdminPermission module=new AdminPermission().setPermId(1).setPermName("后台管理").setPermCode("system:module")
                .setPermParentId(0);
        //后台管理模块之管理员模块
        AdminPermission adminModule=new AdminPermission().setPermId(2).setPermName("管理员基本信息")
                .setPermCode("system:admin-module").setPermParentId(1);
        //管理员基本信息添加
        AdminPermission adminInsert=new AdminPermission().setPermId(3).setPermName("添加")
                .setPermCode("system:admin-Add").setPermParentId(2);
        //管理员基本信息删除
        AdminPermission adminDelete=new AdminPermission().setPermId(4).setPermName("删除")
                .setPermCode("system:admin-Delete").setPermParentId(2);
        //管理员基本信息修改
        AdminPermission adminUpdate=new AdminPermission().setPermId(5).setPermName("修改")
                .setPermCode("system:admin-Update").setPermParentId(2);
        //管理员基本信息修改
        AdminPermission adminSelect=new AdminPermission().setPermId(6).setPermName("查看")
                .setPermCode("system:admin-select").setPermParentId(2);
        //后台管理模块之管理员角色模块
        AdminPermission roleModule=new AdminPermission().setPermId(7).setPermName("管理员角色信息")
                .setPermCode("system:role-module").setPermParentId(1);
        List<AdminPermission> lists= Lists.newArrayList(module,adminModule,adminInsert,adminDelete,
                adminUpdate,adminSelect,roleModule);
       mAdminPermissionService.saveOrUpdateBatch(lists);
    }

    /**
     * @author:luqi
     * @description: 初始化超级管理员角色
     * @date:2018/9/6_14:56
     * @param:
     * @return:
     */
    private void superAdminPermission() {
        mAdminRolePermissionService.initSuperAdminRolePermission();
    }

}
