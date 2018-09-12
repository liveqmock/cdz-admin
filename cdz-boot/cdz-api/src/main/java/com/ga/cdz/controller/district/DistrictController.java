package com.ga.cdz.controller.district;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.service.IDistrictService;
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
    private IDistrictService districtService;

    /**
     * @author: liuyi
     * @description: 根据上级区域获取数据
     * @date: 2018/9/10_11:07
     * @param: DistrictVo对象
     * @return: 返回该上级区域包含的list
     */
    @PostMapping("/list")
    public Result getListAllCity() {
        List<District> districts = districtService.getListAllCity();
        return Result.success().data(districts);
    }

    @PostMapping("/province/list")
    public Result getListProvince() {
        List<District> province = districtService.getListAllProvince();
        return Result.success().data(province);
    }


}
