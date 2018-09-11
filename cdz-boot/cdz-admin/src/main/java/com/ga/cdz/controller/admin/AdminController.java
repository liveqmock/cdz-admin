package com.ga.cdz.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.group.admin.IMAdminInfoGroup;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMAdminInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description: 管理信息控制层
 * @date:2018/9/5_16:17
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends AbstractBaseController {

    /**
     * 管理员信息服务
     **/
    @Resource
    IMAdminInfoService mAdminInfoService;


    /**
     * @author:luqi
     * @description: 添加管理员
     * @date:2018/9/5_16:55
     * @param: AdminInfoVo
     * @return: Result
     */
    @RequiresPermissions("system:admin-Add")
    @PostMapping("/save")
    public Result saveAdminInfo(@RequestBody @Validated(value = {IMAdminInfoGroup.Add.class}) AdminInfoVo adminInfoVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean rs = mAdminInfoService.saveAdminIno(adminInfoVo);
        if (!rs) {
            return Result.fail().message("操作失败");
        }
        return Result.success().message("操作成功");
    }

    /**
     * @author:luqi
     * @description: 更新管理员，通过Id
     * @date:2018/9/5_16:55
     * @param: AdminInfoVo
     * @return: Result
     */
    @RequiresPermissions("system:admin-Update")
    @PostMapping("/update/id")
    public Result updateAdminInfoById(@RequestBody @Validated(value = {IMAdminInfoGroup.Update.class}) AdminInfoVo adminInfoVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean rs = mAdminInfoService.updateAdminInfoById(adminInfoVo);
        if (!rs) {
            return Result.fail().message("操作失败");
        }
        return Result.success().message("操作成功");
    }

    /**
     * @author:luqi
     * @description: 通过条件查询page分页，分页条件可以为空
     * @date:2018/9/5_16:55
     * @param: PageVo
     * @return: Result
     */
    @RequiresPermissions("system:admin-select")
    @PostMapping("/page/con")
    public Result getAdminInfoPageByCon(@RequestBody @Validated PageVo<AdminInfoVo> pageVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        IPage<AdminInfo> page = mAdminInfoService.getAdminInfoPageByCon(pageVo);
        return Result.success().data(page);
    }


    /**
     * @author:luqi
     * @description: 删除管理员通过ID
     * @date:2018/9/5_16:55
     * @param: AdminInfoVo
     * @return: Result
     */
    @RequiresPermissions("system:admin-Delete")
    @PostMapping("/remove/id")
    public Result removeAdminInfoById(@RequestBody @Validated({IMAdminInfoGroup.Remove.class}) AdminInfoVo adminInfoVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean isDelete = mAdminInfoService.removeAdminInfoById(adminInfoVo.getAdminId());
        if (!isDelete) {
            return Result.fail().message("操作失败");
        }
        return Result.success().message("操作成功");
    }

}
