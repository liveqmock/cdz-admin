package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.AdminInfoRole;
import com.ga.cdz.domain.vo.base.AdminInfoRoleVo;

/**
 * @author:luqi
 * @description: 管理员与角色关联表
 * @date:2018/9/6_11:47
 */
public interface IMAdminInfoRoleService extends IService<AdminInfoRole> {

    /**
     * @author:luqi
     * @description: 添加管理员与角色关系
     * @date:2018/9/6_13:33
     * @param:
     * @return:
     */
    boolean saveAdminInfoRole(AdminInfoRoleVo adminInfoRoleVo);

}
