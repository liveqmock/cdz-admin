package com.ga.cdz.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.group.admin.IMOperatorsGroup;
import com.ga.cdz.domain.vo.base.OperatorsVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMOperatorsService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 运营商信息控制层
 * @date:2018/9/10 10:04
 */
@RestController
@RequestMapping("/operator")
public class OperatorController extends AbstractBaseController {
    /**
     * 运营商信息服务
     */
    @Resource
    IMOperatorsService mOperatorsService;

    /**
     * @author:wanzhongsu
     * @description: 获取运营商信息列表分页
     * @date: 2018/9/10 10:04
     * @param:
     * @return: Result
     */
    @PostMapping("/list")
    public Result getOperatorList(@RequestBody @Validated PageVo<OperatorsVo> vo) {
        IPage<Operators> operators = mOperatorsService.getOperatorPage(vo);
        return Result.success().data(operators);
    }

    /**
     * @author:wanzhongsu
     * @description: 根据名字获取运营商信息
     * @date: 2018/9/12 10:10
     * @param: OperatorsVo
     * @return: Result
     */
    @PostMapping("/get/id")
    public Result getOperatorByName(@RequestBody @Validated OperatorsVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        List<Operators> list = mOperatorsService.getOperatorsListByName(vo);
        return Result.success().data(list);
    }

    /**
     * @author:wanzhongsu
     * @description: 保存运营商信息
     * @date: 2018/9/10 10:05
     * @param: OperatorsVo
     * @return: Result
     */
    @PostMapping("/save")
    public Result saveOperators(@RequestBody @Validated(value = {IMOperatorsGroup.Add.class}) OperatorsVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        mOperatorsService.saveOperator(vo);
        return Result.success().message("保存成功");
    }

    /**
     * @author:wanzhongsu
     * @description: 删除运营商信息
     * @date: 2018/9/10 10:05
     * @param: OperatorsVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated(value = {IMOperatorsGroup.Delete.class}) OperatorsVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        mOperatorsService.removeOperator(vo);
        return Result.success().message("删除成功");
    }
}
