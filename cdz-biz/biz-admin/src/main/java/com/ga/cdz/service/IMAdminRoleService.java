package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.AdminRolePermDTO;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.vo.base.AdminRolePermVo;
import com.ga.cdz.domain.vo.base.AdminRoleVo;

import java.util.List;

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

    /**
     * @param :
     * @return :返回封装AdminRole的数组
     * @author :huanghoahao
     * @desc :列表展示所有角色
     * @date 2018-09-06 15:33
     */
    List<AdminRole> listAdminRole();

    /**
     * @param id :roleId
     * @return ：返回AdminRole实体
     * @author :huanghaohao
     * @date :2018-09-06 16:23
     * @desc 根据RoleId 查询角色详情
     */
    AdminRole getAdminRoleById(Integer id);

    /**
     * @param id
     * @return
     * @author huanghaohoa
     * @date 2018年9月6日 20点57分
     * @desc 根据用户传递的roleId 查询该角色所有的权限列表
     */
    AdminRolePermDTO getAdminRolePermById(Integer id);

    /**
     * @param
     * @author huanghaohao
     * @date 2018-09-07 14:17
     * @desc 用于更新或者新增角色的权限
     */
    //todo huanghaohao  方法修改为void类型，错误请使用 throw new BusinessException 抛出
    Integer updateOrInsertAdminRolePermission(List<AdminRolePermVo> adminRolePermVoList);

    /**
     * @param adminRoleVo
     * @return
     * @author huanghaohao
     * @desc 新增角色
     */
    //todo huanghaohao 方法修改为void类型，错误请使用 throw new BusinessException 抛出
    Boolean addAdminRole(AdminRoleVo adminRoleVo);

    /**
     * @return
     * @author huanghaohao
     * @date 2018-09-11 16:36
     * @desc 返回所有的角色
     */
    List<AdminRole> getRoleList();
}
