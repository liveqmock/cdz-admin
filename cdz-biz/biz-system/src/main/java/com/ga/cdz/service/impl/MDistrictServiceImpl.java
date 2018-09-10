package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.domain.vo.base.DistrictVo;
import com.ga.cdz.service.IMDistrictService;
import org.springframework.beans.BeanUtils;
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
    //地区信息mapper
    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<District> getListByParentId(int par_id) {
        List<District> districts = districtMapper.selectList(new QueryWrapper<District>().lambda().eq(District::getDistrictParentCode, par_id));
        return districts;
    }
}
