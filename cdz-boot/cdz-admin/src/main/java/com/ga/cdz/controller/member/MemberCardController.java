package com.ga.cdz.controller.member;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.UserMemberCardInfoDTO;
import com.ga.cdz.domain.group.admin.IMUserInfoGroup;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.admin.UserMemberCardInfoVo;
import com.ga.cdz.service.IMAdminUserCardInfoService;
import com.ga.cdz.service.IMAdminUserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author huanghaohao
 * @date 2018-09-09 22:40
 * @desc 会员卡管理
 */
@RestController
@RequestMapping("/member/card")
public class MemberCardController extends AbstractBaseController {
  @Resource
  IMAdminUserService mAdminUserService; //用户信息 Service
  @Resource
  IMAdminUserCardInfoService mAdminUserCardInfoService; //

  /**
   * @author huanghaohao
   * @data 2018年9月9日 23点38分
   * @desc 分页查询会员列表
   * @return
   */

  @PostMapping("/list")
  public Result getMemberCardListByPage(@RequestBody @Validated PageVo< UserMemberCardInfoVo> pageVo , BindingResult bindingResult){
    IPage<UserMemberCardInfoDTO> page=mAdminUserCardInfoService.getMemberCardListPage(pageVo);
    return Result.success().data(page);
  }

  /**
   * @author huanghaohao
   * @date 2018-09-10 10:34
   * @desc 更新用户状态
   * @param userMemberCardInfoVo
   * @return
   */
  @PostMapping("/update")
  public Result updateMemberCardStatById(@RequestBody @Validated(value = IMUserInfoGroup.updateMemCardStat.class)UserMemberCardInfoVo userMemberCardInfoVo){
    int flag=mAdminUserCardInfoService.updateMemCardStatById(userMemberCardInfoVo);
    if(flag!=0){
      return Result.success();
    }else{
      return Result.fail().message("无需更新或者更新失败");
    }
  }
}
