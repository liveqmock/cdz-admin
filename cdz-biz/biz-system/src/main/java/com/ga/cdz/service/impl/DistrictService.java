package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.service.IDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/10_10:39
 */
@Service("districtService")
public class DistrictService extends ServiceImpl<DistrictMapper, District> implements IDistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<District> getListAllCity() {
        List<District> districts = districtMapper.selectList(new QueryWrapper<District>().lambda().eq(District::getDistrictLevel, District.DistrictLevel.SHI));

        return districts;
    }
}
