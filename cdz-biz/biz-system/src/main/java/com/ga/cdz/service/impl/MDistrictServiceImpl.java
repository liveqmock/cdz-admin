package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.domain.vo.base.DistrictVo;
import com.ga.cdz.service.IMDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description:地区信息服务实现类
 * @date: 2018/9/7 10:59
 */
@Service("mDistrictService")
public class MDistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements IMDistrictService {

    @Override
    public List<District> getListByParentId(DistrictVo vo) {
        List<District> districts = baseMapper.selectList(new QueryWrapper<District>().lambda().eq(District::getDistrictParentCode, vo.getDistrictCode()));
        return districts;
    }

    @Override
    public List<District> getCodeByName(DistrictVo vo) {
        List<District> districts = baseMapper.selectList(new QueryWrapper<District>().lambda().like(District::getDistrictName, vo.getDistrictName()));
        return districts;
    }
}
