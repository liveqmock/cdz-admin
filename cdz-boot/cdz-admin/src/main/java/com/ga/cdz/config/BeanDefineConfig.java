package com.ga.cdz.config;

import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.service.*;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


/**
 * @author:luqi
 * @description: spring启动回调类，1-权限模块初始化 2-超级管理员账号权限初始化
 * @date:2018/9/3_21:36
 */
@Slf4j
@Component
public class BeanDefineConfig implements ApplicationListener<ApplicationContextEvent> {

    /**
     * 启动线程
     **/
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Resource
    IMAdminPermissionService mAdminPermissionService;

    @Resource
    IMAdminRolePermissionService mAdminRolePermissionService;

    @Resource
    IMChargingStationService mChargingStationService;

    @Resource
    IMChargingStationAttachService mChargingStationAttachService;

    @Resource
    IMChargingTypeService mChargingTypeService;

    @Resource
    IMChargingPriceService mChargingPriceService;

    @Resource
    IMChargingDeviceService mChargingDeviceService;

    @Resource
    IMChargingDeviceSubService mChargingDeviceSubService;

    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
        if (applicationContextEvent.getApplicationContext().getParent() == null) {
            initAdmin();
            superAdminPermission();
            redisCacheTable();
        }
    }

    /**
     * @author:luqi
     * @description: 后台管理模块权限初始化
     * @date:2018/9/3_21:39
     * @param:
     * @return:
     */
    private void initAdmin() {
        //后台管理模块
        AdminPermission module = new AdminPermission().setPermId(1).setPermName("后台管理").setPermCode("system:module")
                .setPermParentId(0);
        //后台管理模块之管理员模块
        AdminPermission adminModule = new AdminPermission().setPermId(2).setPermName("管理员基本信息")
                .setPermCode("system:admin-module").setPermParentId(1);
        //管理员基本信息添加
        AdminPermission adminInsert = new AdminPermission().setPermId(3).setPermName("添加")
                .setPermCode("system:admin-insert").setPermParentId(2);
        //管理员基本信息删除
        AdminPermission adminDelete = new AdminPermission().setPermId(4).setPermName("删除")
                .setPermCode("system:admin-delete").setPermParentId(2);
        //管理员基本信息修改
        AdminPermission adminUpdate = new AdminPermission().setPermId(5).setPermName("修改")
                .setPermCode("system:admin-update").setPermParentId(2);
        //管理员基本信息修改
        AdminPermission adminSelect = new AdminPermission().setPermId(6).setPermName("查看")
                .setPermCode("system:admin-select").setPermParentId(2);
        //后台管理模块之管理员角色模块
        AdminPermission roleModule = new AdminPermission().setPermId(7).setPermName("管理员角色信息")
                .setPermCode("system:role-module").setPermParentId(1);
        List<AdminPermission> lists = Lists.newArrayList(module, adminModule, adminInsert, adminDelete,
                adminUpdate, adminSelect, roleModule);
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

    /**
     * @author:luqi
     * @description: 执行cache任务
     * @date:2018/9/11_19:22
     * @param:
     * @return:
     */
    private void redisCacheTable() {
        executorService.execute(() -> mChargingStationService.getRedisListAll());
        executorService.execute(() -> mChargingStationAttachService.getRedisListAll());
        executorService.execute(() -> mChargingTypeService.getRedisListAll());
        executorService.execute(() -> mChargingPriceService.getRedisListAll());
        executorService.execute(() -> mChargingDeviceService.getRedisListAll());
        executorService.execute(() -> mChargingDeviceSubService.getRedisListAll());
    }

}
