package com.ga.cdz.controller.admin;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.AdminRolePermDTO;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.group.admin.IMAdminRoleGroup;
import com.ga.cdz.domain.vo.base.AdminRolePermVo;
import com.ga.cdz.domain.vo.base.AdminRoleVo;
import com.ga.cdz.service.IMAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @param :
 * @version :
 * @auhor :huanghaohao
 * @date : 2018-09-06 15:29
 * @desc ：管理员权限管理模块
 */
@RestController
@RequestMapping("/roles")
public class AdminRoleController extends AbstractBaseController {
    @Autowired
    IMAdminRoleService mAdminRoleService;


    @GetMapping("/list")
    public Result getListAdminRole() {
        List<AdminRole> list = mAdminRoleService.listAdminRole();
        //如果查询结构不为空 返回正常
        return Result.success().data(list);
    }

    /**
     * @return
     * @author huanghaohao
     * @date 2018-09-11 16:34
     * @desc 给账号授权时 角色列表
     */
    @PostMapping("/list/all")
    public Result getRoleList() {
        List<AdminRole> list = mAdminRoleService.getRoleList();
        return Result.success().data(list);
    }

    /**
     * @return
     * @author huanghaohao
     * @desc 用于根据角色Id查询 角色信息
     * @date 2018-09-06 16:30
     * @params :rolesId
     */
    @PostMapping("/id")
    public Result getRoleById(@RequestBody @Validated(value = IMAdminRoleGroup.Find.class) AdminRoleVo adminRoleVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        System.out.println(adminRoleVo.getRoleId());
        AdminRole adminRole = mAdminRoleService.getAdminRoleById(adminRoleVo.getRoleId());
        if (adminRole == null) {
            return Result.fail().message("根据角色Id未查询到角色");
        }
        return Result.success().data(adminRole);
    }

    /**
     * @param adminRoleVo
     * @param bindingResult
     * @return
     * @author :huanghaohao
     * @date 2018年9月7日 10点09分
     * @desc -
     */
    @PostMapping("/permission/list")
    public Result findAdminRolePermissionListById(@RequestBody @Validated(value = IMAdminRoleGroup.Find.class) AdminRoleVo adminRoleVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        AdminRolePermDTO adminRolePermDTO = mAdminRoleService.getAdminRolePermById(adminRoleVo.getRoleId());
        return Result.success().data(adminRolePermDTO);
    }

    /**
     * @author :huanghaohao
     * @date 2018-09-07 13:43
     * @desc :用于更新角色权限
     */
    //todo huanghaohao  mAdminRoleService.updateOrInsertAdminRolePermission 接口修改为void类型  返回的Result对象需要添加 message 提示 更新角色权限成功
    @PostMapping("/permission/update")
    public Result updateAdminRolePermissionList(@RequestBody @Validated(value = IMAdminRoleGroup.Update.class) List<AdminRolePermVo> adminRolePermVoList, BindingResult bindingResult) {
        checkParams(bindingResult);
        int successNum = mAdminRoleService.updateOrInsertAdminRolePermission(adminRolePermVoList);
        return Result.success().data(successNum);
    }

    /**
     * @param adminRoleVo
     * @return
     * @author huanghaohao\
     * @date 2018年9月7日 16点10分
     * @desc 新增角色
     */
    @PostMapping("/insert")
    public Result insertAdminRole(@RequestBody @Validated(value = IMAdminRoleGroup.Add.class) AdminRoleVo adminRoleVo) {
        boolean flag = mAdminRoleService.addAdminRole(adminRoleVo);
        if (flag) {
            return Result.success();
        } else {
            return Result.fail().message("角色名已经存在或者新增失败");
        }
    }

}
