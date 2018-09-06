package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.vo.base.AdminRoleVo;

/**
 * @author:luqi
 * @description: 管理员角色接口
 * @date:2018/9/6_11:09
 */
public interface IMAdminRoleService extends IService<AdminRole> {

    /**
     * @author:luqi
     * @description: 新增角色
     * @date:2018/9/6_11:20
     * @param:
     * @return:
     */
    boolean saveAdminRole(AdminRoleVo adminRoleVo);

}
