package com.ga.cdz.controller.system;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.group.admin.IMOperatorsGroup;
import com.ga.cdz.domain.vo.base.OperatorsVo;
import com.ga.cdz.service.IMOperatorsService;
import com.ga.cdz.util.MUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController extends AbstractBaseController {
    @Resource
    IMOperatorsService mOperatorsService;

    @PostMapping("/list")
    public Result getOperatorList() {
        List<Operators> operators = mOperatorsService.getOperatorList();
        return Result.success().data(operators);
    }

    @PostMapping("/save")
    public Result saveOperators(@RequestBody @Validated(value = {IMOperatorsGroup.insert.class}) OperatorsVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        int integer = mOperatorsService.saveOperator(vo);
        if (integer > 0) {
            return Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated(value = {IMOperatorsGroup.delete.class}) OperatorsVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        int integer = mOperatorsService.removeOperator(vo);
        if (integer > 0) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    @PostMapping("/nextCode")
    public Result nextCode() {
        String nextCode = mOperatorsService.nextOperatorCode();
        return Result.success().data(nextCode);
    }
}
