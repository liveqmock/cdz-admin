package com.ga.cdz.controller.member;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.UserMemberCarsInfoDTO;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserMemberCarsInfoVo;
import com.ga.cdz.service.IMAdminUserCarsInfoService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanghaohao
 * @date 2018年9月9日 23点39分
 * @desc 会员汽车列表
 *
 */

@RestController
@RequestMapping("/member/car")
public class MemberCarController extends AbstractBaseController {
  @Resource
  IMAdminUserCarsInfoService mAdminUserCarsInfoService;


  /**
   * @author huanghaohao
   * @date 2018-09-10 11:25
   * @desc 获取汽车列表分页
   * @param pageVo
   * @param bindingResult
   * @return
   */
  @PostMapping("/list")
  public Result getCarListPage(@RequestBody @Validated PageVo<UserMemberCarsInfoVo> pageVo, BindingResult bindingResult){
    checkParams(bindingResult);
    List<UserMemberCarsInfoDTO> list = mAdminUserCarsInfoService.getUserCarsListPage(pageVo);
    return Result.success().data(list);
  }


}
