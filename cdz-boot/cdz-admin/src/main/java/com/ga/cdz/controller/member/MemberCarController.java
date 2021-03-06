package com.ga.cdz.controller.member;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.UserMemberCarsInfoDTO;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.admin.UserMemberCarsInfoVo;
import com.ga.cdz.service.IMAdminUserCarsInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author huanghaohao
 * @date 2018年9月9日 23点39分
 * @desc 会员汽车列表
 */

@RestController
@RequestMapping("/member/car")
public class MemberCarController extends AbstractBaseController {
    @Resource
    IMAdminUserCarsInfoService mAdminUserCarsInfoService;

    /**
     * @param pageVo
     * @param bindingResult
     * @return
     * @author huanghaohao
     * @date 2018-09-10 11:25
     * @desc 获取汽车列表分页
     */
    @PostMapping("/list")
    public Result getCarListPage(@RequestBody @Validated PageVo<UserMemberCarsInfoVo> pageVo, BindingResult bindingResult) {
        checkParams(bindingResult);
//    Session session=SecurityUtils.getSubject().getSession();
        Object name = SecurityUtils.getSubject().getPrincipals();
//    Object ut=SecurityUtils.getSecurityManager();
        System.err.println(name.toString());
        IPage<UserMemberCarsInfoDTO> page = mAdminUserCarsInfoService.getUserCarsListPage(pageVo);
        return Result.success().data(page);
    }


}
