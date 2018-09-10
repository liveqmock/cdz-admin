package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.group.admin.IMChargingShopGroup;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingShopService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author:wanzhongsu
 * @description: 商户管理控制层
 * @date:2018/9/10 9:57
 */
@RestController
@RequestMapping("/chargingshop")
public class ChargingShopController extends AbstractBaseController {
    //商户管理服务
    @Resource
    IMChargingShopService mChargingShopService;

    /**
     * @author:wanzhongsu
     * @description: 获取商户列表
     * @date: 2018/9/10 14:40
     * @param: PageVo
     * @return: Result
     */
    @PostMapping("/list")
    public Result getShopList(@RequestBody @Validated PageVo<ChargingShopVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        IPage<ChargingShop> iPage = mChargingShopService.getChargingShopList(vo);
        return Result.success().data(iPage);
    }

    /**
     * @author:wanzhongsu
     * @description: 添加商户
     * @date: 2018/9/10 14:41
     * @param: ChargingShopVo
     * @return: Result
     */
    @PostMapping("/add")
    public Result saveShop(@RequestBody @Validated(value = {IMChargingShopGroup.add.class}) ChargingShopVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        Boolean result = mChargingShopService.saveChargingShop(vo);
        if (result) {
            return Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 修改商户
     * @date: 2018/9/10 14:41
     * @param: ChargingShopVo
     * @return: Result
     */
    @PostMapping("/update")
    public Result updateShop(@RequestBody @Validated(value = IMChargingShopGroup.update.class) ChargingShopVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingShopService.updateShopById(vo);
        if (result) {
            return Result.success().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 删除商户
     * @date: 2018/9/10 14:41
     * @param: ChargingShopVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result deleteShop(@RequestBody @Validated(value = IMChargingShopGroup.delete.class) ChargingShopVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingShopService.deleteShopById(vo);
        if (result) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }
}

