package com.ga.cdz.controller.district;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.domain.vo.base.DistrictVo;
import com.ga.cdz.service.IDistrictService;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liuyi
 * @description: 地区信息表
 * @date: 2018/9/10_10:50
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends AbstractBaseController {

    @Resource
    private IDistrictService iDistrictService;

    /**
     * @author: liuyi
     * @description: 根据上级区域获取数据
     * @date: 2018/9/10_11:07
     * @param: DistrictVo对象
     * @return: 返回该上级区域包含的list
     */
    @PostMapping("/list")
    public Result getDistrictListByParentCode(@RequestBody @Validated DistrictVo districtVo, BindingResult bindingResult) {
        checkParams(bindingResult);
        int parentCode;
        if (!ObjectUtils.isEmpty(districtVo)) {
            parentCode = districtVo.getDistrictParentCode();
        } else {
            parentCode = 0;
        }
        List<District> districtList = iDistrictService.getListByParentId(parentCode);
        return Result.success().data(districtList);
    }
}
