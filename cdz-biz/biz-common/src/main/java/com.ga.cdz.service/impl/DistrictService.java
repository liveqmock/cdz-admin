package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.service.IDistrictService;
import com.ga.cdz.util.MRedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/10_10:39
 */
@Service("districtService")
public class DistrictService extends ServiceImpl<DistrictMapper, District> implements IDistrictService {


    @Resource
    MRedisUtil mRedisUtil;


    @Override
    public List<District> getListAllCity() {
        List<District> districts = list(new QueryWrapper<District>().lambda().eq(District::getDistrictLevel,
                District.DistrictLevel.SHI));
        //Collections.sort(districts, Comparator.comparing(District::getDistrictCode));
        return districts;
    }

    @Override
    public List<District> getListAllProvince() {
        List<District> list;
        if (mRedisUtil.hasKey(RedisConstant.TABLE_DISTRICT)) {
            list = mRedisUtil.get(RedisConstant.TABLE_DISTRICT);
        } else {
            list = baseMapper.selectList(null);
            mRedisUtil.pushList(RedisConstant.TABLE_DISTRICT, list);
        }
        List<District> rsList = list.stream().filter(district -> district.getDistrictParentCode().equals(0))
                .collect(Collectors.toList());
        return rsList;
    }
}
