package com.ga.cdz.dao.center;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ga.cdz.domain.entity.AdminRolePermission;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2018-09-05
 */
public interface AdminRolePermissionMapper extends BaseMapper<AdminRolePermission> {
    /**
     * @author:luqi
     * @description: 批量插入
     * @date:2018/9/6_20:51
     * @param: list集合
     * @return: 受影响的行数
     */
    int insertBatch(List<AdminRolePermission> list);

}
