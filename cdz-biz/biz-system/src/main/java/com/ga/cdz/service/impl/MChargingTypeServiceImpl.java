package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingTypeMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;
import com.ga.cdz.service.IMChargingTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
 */
@Service("mChargingTypeService")
public class MChargingTypeServiceImpl extends ServiceImpl<ChargingTypeMapper, ChargingType> implements IMChargingTypeService {

    @Override
    public List<ChargingType> getChargingTypeList() {
        List<ChargingType> list = baseMapper.selectList(new QueryWrapper<ChargingType>().lambda().orderByAsc(ChargingType::getCgtypeId));
        return list;
    }

    @Override
    public Integer removeChargingTypeById(ChargingTypeVo vo) {
        ChargingType chargingType = new ChargingType();
        BeanUtils.copyProperties(vo, chargingType);
        ChargingType hasCount = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeId, chargingType.getCgtypeId()));
        if (ObjectUtils.isEmpty(hasCount)) {
            throw new BusinessException("充电方式ID不存在");
        }
        Integer integer = baseMapper.deleteById(chargingType.getCgtypeId());
        return integer;
    }

    @Override
    public Integer saveChargingTypeObj(ChargingTypeVo vo) {
        ChargingType chargingType = new ChargingType();
        BeanUtils.copyProperties(vo, chargingType);
        ChargingType hasName = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeName, chargingType.getCgtypeName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("充电方式名称存在");
        }
        Integer integer = baseMapper.insert(chargingType);
        return integer;
    }
}
