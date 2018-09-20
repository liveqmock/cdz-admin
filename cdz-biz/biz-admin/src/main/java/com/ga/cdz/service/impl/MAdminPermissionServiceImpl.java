package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminPermissionMapper;
import com.ga.cdz.domain.entity.AdminPermission;
import com.ga.cdz.service.IMAdminPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author:luqi
 * @description: 管理员权限接口具体实现类
 * @date:2018/9/4_10:54
 */
@Slf4j
@Service("mAdminPermissionService")
public class MAdminPermissionServiceImpl extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements IMAdminPermissionService {
}
