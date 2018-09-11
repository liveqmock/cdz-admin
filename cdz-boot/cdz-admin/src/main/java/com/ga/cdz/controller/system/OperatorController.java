package com.ga.cdz.controller.system;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.group.admin.IMOperatorsGroup;
import com.ga.cdz.domain.vo.base.OperatorsVo;
import com.ga.cdz.service.IMOperatorsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @description: 获取运营商信息列表
     * @date: 2018/9/10 10:04
     * @param:
     * @return: Result
     */
    @PostMapping("/list")
    public Result getOperatorList() {
        List<Operators> operators = mOperatorsService.getOperatorList();
        return Result.success().data(operators);
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
        int integer = mOperatorsService.saveOperator(vo);
        if (integer > 0) {
            return Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
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
        int integer = mOperatorsService.removeOperator(vo);
        if (integer > 0) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 获取运营商下一个编码
     * @date: 2018/9/10 10:06
     * @param:
     * @return: Result
     */
    @PostMapping("/nextcode")
    public Result nextCode() {
        String nextCode = mOperatorsService.nextOperatorCode();
        return Result.success().data(nextCode);
    }
}
