package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminRoleMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.vo.base.AdminRoleVo;
import com.ga.cdz.service.IMAdminRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * @author:luqi
 * @description: 后台管理员角色服务
 * @date:2018/9/6_11:43
 */
@Slf4j
@Service("mAdminRoleService")
public class MAdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IMAdminRoleService {

    @Override
    public boolean saveAdminRole(AdminRoleVo adminRoleVo) {
        AdminRole adminRole = new AdminRole();
        BeanUtils.copyProperties(adminRoleVo, adminRole);
        AdminRole hasAdminRole = baseMapper.selectOne(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getRoleName, adminRole.getRoleName()));
        if (!ObjectUtils.isEmpty(hasAdminRole)) {
            throw new BusinessException("角色名称已存在");
        }
        return save(adminRole);
    }
}
