package com.ga.cdz.dao.center;

import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.AdminDemoDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.AdminPermission;

import java.util.List;

/**
 * @author:luqi
 * @description: 管理员信息表 Mapper 接口
 * @date:2018/9/5_9:45
 */
public interface AdminInfoMapper extends SuperMapper<AdminInfo> {

    /**
     * @author:luqi
     * @description: 通过ID查询管理员的权限
     * @date:2018/9/6_18:13
     * @param:
     * @return:
     */
    List<String> selectAdminPermCodeListById(Integer adminId);

    /**
     *  关联分页测试
     * */
    List<AdminDemoDTO> selectAdminDemoDTOPage();

}
