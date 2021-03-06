package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminPermissionMapper;
import com.ga.cdz.dao.center.AdminRoleMapper;
import com.ga.cdz.dao.center.AdminRolePermissionMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.AdminRolePermDTO;
import com.ga.cdz.domain.entity.AdminPermission;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.entity.AdminRolePermission;
import com.ga.cdz.domain.vo.base.AdminRolePermVo;
import com.ga.cdz.domain.vo.base.AdminRoleVo;
import com.ga.cdz.service.IMAdminRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;


/**
 * @author:luqi
 * @description: 后台管理员角色服务
 * @date:2018/9/6_11:43
 */
@Slf4j
@Service("mAdminRoleService")
public class MAdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IMAdminRoleService {
    @Resource
    AdminRolePermissionMapper adminRolePermissionMapper;
    @Resource
    AdminPermissionMapper adminPermissionMapper;


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

    /**
     * @param :
     * @return :返回封装好的List<AdminRole>
     * @author :huanghaohao
     * @date : 2018-09-06 15:26
     * @desc :列表展示所有的角色信息
     */
    @Override
    public List<AdminRole> listAdminRole() {
        List<AdminRole> list = this.baseMapper.selectList(new QueryWrapper<AdminRole>());
        return list;
    }

    /**
     * @param id :roleId
     * @return ：返回AdminRole实体
     * @author :huanghaohao
     * @date :2018-09-06 16:23
     * @desc 根据RoleId 查询角色详情
     */
    @Override
    public AdminRole getAdminRoleById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * @param id ：用户传递的roleId
     * @return :封装好的角色权限数据
     * @author :huanghaohao
     * @desc :但是我告诉你 这里是用于根据用户传递的 role_id 把查询到的数据，按照树状结构整理好 排序给前端使用
     * @date : 2018年9月6日 20点04分
     */
    public AdminRolePermDTO getAdminRolePermById(Integer id) {
        //首先查询出，当前角色所拥有的权限
        List<AdminRolePermission> adminRolePermissionList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermission>().lambda().eq(AdminRolePermission::getRoleId, id));
        AdminRole adminRole = this.baseMapper.selectOne(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getRoleId, id));
        //用于封装前端数据的 DTO
        AdminRolePermDTO permDTO = new AdminRolePermDTO();
        //查询出 权限的总母列数据
        AdminPermission parentPerm = adminPermissionMapper.selectOne(new QueryWrapper<AdminPermission>().lambda().eq(AdminPermission::getPermParentId, 0));
        //封装角色名
        permDTO.setRoleName(adminRole.getRoleName());
        //封装角色ID
        permDTO.setRoleId(adminRole.getRoleId());
        //封装母权限列表ID
        permDTO.setPermId(parentPerm.getPermId());
        //封装母权限名
        permDTO.setPermName(parentPerm.getPermName());
        //封装母权限之母权限
        permDTO.setPermParentId(parentPerm.getPermParentId());
        //设置该权限的有效性 一级母权限 默认有
        permDTO.setIsValid(true);
        //循环递归解决 树状结构数据整理
        System.out.println(adminRolePermissionList);
        sortPermDate(permDTO, adminRolePermissionList);
        return permDTO;
    }

    /**
     * @param currentPermDTO          当前 AdminRolePermDTO
     * @param adminRolePermissionList
     * @return
     * @author huanghaohao
     * @date ：2018年9月6日 20点23分
     * @desc 用于递归找到子节点 然然后挂靠到母节点上去
     */
    private void sortPermDate(AdminRolePermDTO currentPermDTO, List<AdminRolePermission> adminRolePermissionList) {
        //根据上级权限ID 查询子权限信息
        List<AdminPermission> list = adminPermissionMapper.selectList(new QueryWrapper<AdminPermission>().lambda().eq(AdminPermission::getPermParentId, currentPermDTO.getPermId()));
        //查不到就是退出 查询到了 继续下挖
        if (list.size() > 0) {
            //循环所有的孩子权限
            for (int i = 0; i < list.size(); i++) {
                AdminRolePermDTO childAdminRolePermDTO = new AdminRolePermDTO();
                childAdminRolePermDTO.setPermId(list.get(i).getPermId());
                childAdminRolePermDTO.setPermName(list.get(i).getPermName());
                childAdminRolePermDTO.setRoleId(currentPermDTO.getRoleId());
                childAdminRolePermDTO.setRoleName(currentPermDTO.getRoleName());
                childAdminRolePermDTO.setPermParentId(currentPermDTO.getPermId());
                for (int j = 0; j < adminRolePermissionList.size(); j++) {
                    if (list.get(i).getPermId() == adminRolePermissionList.get(j).getPermId()) {
                        childAdminRolePermDTO.setIsValid(true);
                    }
                }
                List listT = currentPermDTO.getChild();
                if (listT == null) {
                    listT = new LinkedList();
                }
                listT.add(childAdminRolePermDTO);
                //把孩子权限挂靠到母权限的child 属性之下
                currentPermDTO.setChild(listT);
                //递归孩子的子节点
                sortPermDate(childAdminRolePermDTO, adminRolePermissionList);
            }
        } else {
            return;
        }
    }

    /**
     * @param
     * @author huanghaohao
     * @date 2018-09-07 14:17
     * @desc 用于更新或者新增角色的权限
     */
    //todo huanghaohao  请看接口描述
    @Override
    @Transactional
    public Integer updateOrInsertAdminRolePermission(List<AdminRolePermVo> adminRolePermVoList) {
        List<AdminRolePermission> list = new LinkedList<AdminRolePermission>();
        for (int i = 0; i < adminRolePermVoList.size(); i++) {
            if (adminRolePermVoList.get(i).getIsValid()) {
                AdminRolePermission adminRolePermission = new AdminRolePermission();
                adminRolePermission.setPermId(adminRolePermVoList.get(i).getPermId());
                adminRolePermission.setRoleId(adminRolePermVoList.get(i).getRoleId());
                list.add(adminRolePermission);
            }
        }
        adminRolePermissionMapper.delete(new QueryWrapper<AdminRolePermission>().lambda().eq(AdminRolePermission::getRoleId, adminRolePermVoList.get(0).getRoleId()));
        return adminRolePermissionMapper.insertBatch(list);
    }

    /**
     * @param adminRoleVo
     * @return
     * @author huanghaohao
     * @desc 新增角色
     * @date 2018-09-11 16:38
     */
    //todo huanghaohao  请看接口描述
    @Override
    public Boolean addAdminRole(AdminRoleVo adminRoleVo) {
        AdminRole adminRole = new AdminRole();
        BeanUtils.copyProperties(adminRoleVo, adminRole);
        List<AdminRole> list = baseMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getRoleName, adminRole.getRoleName()));
        if (list.size() > 0) {
            return false;
        }
        int i = baseMapper.insert(adminRole);
        if (i != 1) {
            return false;
        }
        return true;
    }

    /**
     * @return
     * @author huanghaohao
     * @date 2018-09-11 16:40
     * @desc 返回所有的role
     */
    @Override
    public List<AdminRole> getRoleList() {
        return this.baseMapper.selectList(new QueryWrapper<AdminRole>());
    }
}
