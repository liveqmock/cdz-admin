package com.ga.cdz.controller.admin;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.AdminRolePermDTO;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.group.admin.IMAdminRoleGroup;
import com.ga.cdz.domain.vo.base.AdminRoleVo;
import com.ga.cdz.service.IMAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auhor :huanghaohao
 * @date : 2018-09-06 15:29
 * @param :
 * @desc ：管理员权限管理模块
 * @version  :
 *
 */
@RestController
@RequestMapping("/roles")
public class AdminRoleController extends AbstractBaseController {
  @Autowired
  IMAdminRoleService mAdminRoleService;
  @GetMapping("/list")
  public Result getListAdminRole(){
    List<AdminRole> list =mAdminRoleService.listAdminRole();
      //如果查询结构不为空 返回正常
      return Result.success().data(list);
  }

  /**
   * @author huanghaohao
   * @desc 用于根据角色Id查询 角色信息
   * @date 2018-09-06 16:30
   * @params :rolesId
   * @return
   */
  @PostMapping("/id")
  public Result getRoleById(@RequestBody @Validated(value = IMAdminRoleGroup.Find.class)AdminRoleVo adminRoleVo , BindingResult bindingResult){
    checkParams(bindingResult);
    System.out.println(adminRoleVo.getRoleId());
    AdminRole adminRole=mAdminRoleService.getAdminRoleById(adminRoleVo.getRoleId());
    if(adminRole==null){
      return Result.fail().message("根据角色Id未查询到角色");
    }
    return Result.success().data(adminRole);
  }

  @PostMapping("/permission/list")
  public Result findAdminRolePermiseListById(@RequestBody @Validated(value = IMAdminRoleGroup.Find.class)AdminRoleVo adminRoleVo , BindingResult bindingResult){
    checkParams(bindingResult);
    AdminRolePermDTO adminRolePermDTO=mAdminRoleService.getAdminRolePermById(adminRoleVo.getRoleId());
    return Result.success().data(adminRolePermDTO);
  }



}
