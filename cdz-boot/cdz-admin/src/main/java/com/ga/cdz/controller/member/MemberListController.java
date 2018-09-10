package com.ga.cdz.controller.member;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.UserMemberDTO;
import com.ga.cdz.domain.group.admin.IMUserInfoGroup;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;
import com.ga.cdz.service.IMAdminUserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author huanghaohao
 * @date 2018-09-09 21:49
 * @desc 会员列表 Controller
 */
@RestController
@RequestMapping("/member/list")
public class MemberListController extends AbstractBaseController {
  @Resource
  IMAdminUserService mAdminUserService;

  /**
   * @author huanghaohao
   * @desc 2018-09-09 21:59
   * @desc 分页查询企业会员列表
   * @param pageVo
   * @param bindingResult
   * @return
   */
  @PostMapping("/company/list")
  public Result getCompanyMemListByPage(@RequestBody @Validated PageVo<UserInfoVo> pageVo , BindingResult bindingResult){
    checkParams(bindingResult);
    IPage<UserMemberDTO> list=mAdminUserService.getUserMemberListPage(pageVo);
    return Result.success().data(list);
  }

  /**
   * @author huanghaohao
   * @date 2018年9月9日 22点28分
   * @desc 分页查询个人用户会员列表
   * @param pageVo
   * @param bindingResult
   * @return
   */
  @PostMapping("/user/list")
  public Result getUserMemListByPage(@RequestBody @Validated PageVo<UserInfoVo> pageVo , BindingResult bindingResult){
    checkParams(bindingResult);
    IPage<UserMemberDTO> list=mAdminUserService.getUserMemberListPage(pageVo);
    return Result.success().data(list);
  }

  /**
   * @author huanghaohao
   * @date 2018年9月9日 22点23分
   * @desc 更新公司或者用户会员状态
   * @param userInfoVo
   * @param bindingResult
   * @return
   */
  @PostMapping("/update")
  public Result updateCompanyMemStatById(@RequestBody @Validated(IMUserInfoGroup.updateMemSate.class)UserInfoVo userInfoVo ,BindingResult bindingResult){
    checkParams(bindingResult);
    int stat=mAdminUserService.updateMemUserSate(userInfoVo);//sql执行返回状态
    if(stat!=0){
      return Result.success().message("更新状态成功");
    }else{
      return Result.fail().message("更新失败或无需更新");
    }
  }
}
