package com.ga.cdz.controller.district;

import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.service.IDistrictService;
import com.ga.cdz.util.MRedisUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    private MRedisUtil mRedisUtil;

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

    @PostMapping("/test")
    public Result getTest(@RequestBody ChargingStation chargingStationRD) {
        System.out.println(chargingStationRD.getStationState());
       /* Map<String, ChargingStation> object = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_STATION);
        Map<String, ChargingDeviceSub> map1 = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE_SUB);
        Map<String, ChargingDevice> map2 = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE);*/
        Map<String, List<ChargingPrice>> map3 = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_PRICE);
     /*   Map<String, ChargingType> map4 = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_TYPE);
        Map<String, List<ChargingStationAttach>> map5 = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_ATTACH);*/
        return Result.success().data(map3);
    }


}
