package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoRoleMapper;
import com.ga.cdz.domain.entity.AdminInfoRole;
import com.ga.cdz.domain.vo.base.AdminInfoRoleVo;
import com.ga.cdz.service.IMAdminInfoRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author:luqi
 * @description: 管理员与角色关联 接口实现类
 * @date:2018/9/6_11:48
 */
@Slf4j
@Service("mAdminInfoRoleService")
public class MAdminInfoRoleServiceImpl extends ServiceImpl<AdminInfoRoleMapper, AdminInfoRole> implements IMAdminInfoRoleService {

    @Override
    public boolean saveAdminInfoRole(AdminInfoRoleVo adminInfoRoleVo) {
        AdminInfoRole adminInfoRole = new AdminInfoRole();
        BeanUtils.copyProperties(adminInfoRoleVo, adminInfoRole);
        return save(adminInfoRole);
    }

}
