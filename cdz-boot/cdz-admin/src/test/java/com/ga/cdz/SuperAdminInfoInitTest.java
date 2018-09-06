package com.ga.cdz;

import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.vo.base.AdminInfoRoleVo;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.domain.vo.base.AdminRoleVo;
import com.ga.cdz.service.IMAdminInfoRoleService;
import com.ga.cdz.service.IMAdminInfoService;
import com.ga.cdz.service.IMAdminRoleService;
import com.ga.cdz.util.MUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description: 初始化脚本
 * @date:2018/9/6_10:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperAdminInfoInitTest {

    /**
     * 工具类
     */
    @Resource
    MUtil mUtil;

    /***后台管理员服务*/
    @Resource
    IMAdminInfoService mAdminInfoService;

    /**
     * 后台管理员角色服务
     **/
    @Resource
    IMAdminRoleService mAdminRoleService;

    @Resource
    IMAdminInfoRoleService mAdminInfoRoleService;

    /**
     * @author:luqi
     * @description: 初始化超级管理员
     * @date:2018/9/6_10:59
     * @param:
     * @return:
     */
    @Test
    public void initSuperAdmin() {
        AdminInfoVo adminInfo = new AdminInfoVo();
        adminInfo.setAdminName("超级管理员");
        adminInfo.setAdminAccount("cdzadmin");
        adminInfo.setAdminPwd(mUtil.MD5("cdz123"));
        adminInfo.setAdminSex(AdminInfo.AdminSex.MAN);
        adminInfo.setAdminTel("18685147291");
        adminInfo.setAdminState(AdminInfo.AdminState.ABLE);
        boolean hasSuccess = mAdminInfoService.saveAdminIno(adminInfo);
        if (hasSuccess) {
            System.out.println("初始化超级管理员成功");
        }
    }

    /**
     * @author:luqi
     * @description: 初始化管理员角色
     * @date:2018/9/6_11:06
     * @param:
     * @return:
     */
    @Test
    public void initSuperRole() {
        AdminRoleVo adminRoleVo = new AdminRoleVo();
        adminRoleVo.setRoleName("超级管理员");
        boolean hasSuccess = mAdminRoleService.saveAdminRole(adminRoleVo);
        if (hasSuccess) {
            System.out.println("初始化超级管理员角色成功");
        }
    }

    /**
     * @author:luqi
     * @description: 绑定管理员角色
     * @date:2018/9/6_11:45
     * @param:
     * @return:
     */
    @Test
    public void bindSuperRoleWithAdmin() {
        AdminInfoRoleVo adminInfoRoleVo = new AdminInfoRoleVo();
        adminInfoRoleVo.setAdminId(1).setRoleId(1);
        boolean hasSuccess = mAdminInfoRoleService.saveAdminInfoRole(adminInfoRoleVo);
        if (hasSuccess) {
            System.out.println("绑定超级管理员角色成功");
        }
    }

}
