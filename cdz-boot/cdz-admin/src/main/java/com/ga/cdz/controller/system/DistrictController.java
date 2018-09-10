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
 * @author:wanzhongsu
 * @description: 区域管理控制层
 * @date:2018/9/10 10:02
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends AbstractBaseController {
    /**
     * 区域管理服务
     */
    @Resource
    private IMDistrictService mDistrictService;

    /**
     * @author:wanzhongsu
     * @description: 根据上一级id获取区域信息
     * @date: 2018/9/10 10:02
     * @param: DistrVo
     * @return: Result
     */
    @PostMapping("/list")
    public Result getDistrictListByParentCode(@RequestBody @Validated DistrictVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        int parentCode;
        if (!ObjectUtils.isEmpty(vo)) {
            parentCode = vo.getDistrictCode();
        } else {
            parentCode = 0;
        }
        List<District> districtList = mDistrictService.getListByParentId(parentCode);
        return Result.success().data(districtList);
    }
}

