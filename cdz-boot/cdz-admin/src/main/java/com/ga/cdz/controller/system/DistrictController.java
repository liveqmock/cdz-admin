package com.ga.cdz.controller.system;


import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.domain.vo.base.DistrictVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMDistrictService;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 地区信息表 前端控制器
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends AbstractBaseController {
    @Resource
    private IMDistrictService mDistrictService;

    @PostMapping("/list")
    public Result getDistrictListByParentCode(@RequestBody @Validated DistrictVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        String parentCode;
        if (!ObjectUtils.isEmpty(vo)) {
            parentCode = String.valueOf(vo.getDistrictCode());
        } else {
            parentCode = "0";
        }
        if (parentCode == null || "".equals(parentCode)) {
            parentCode = "0";
        }
        List<District> districtList = mDistrictService.getListByParentId(parentCode);
        return Result.success().data(districtList);
    }
}

