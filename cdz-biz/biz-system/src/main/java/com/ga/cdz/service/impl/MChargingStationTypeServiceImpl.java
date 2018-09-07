package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingStationTypeMapper;
import com.ga.cdz.domain.bean.BaseException;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.ChargingStationType;
import com.ga.cdz.domain.vo.base.ChargingStationTypeVo;
import com.ga.cdz.service.IMChargingStationTypeService;
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
@Service("mChargingStationTypeService")
public class MChargingStationTypeServiceImpl extends ServiceImpl<ChargingStationTypeMapper, ChargingStationType> implements IMChargingStationTypeService {

    @Override
    public List<ChargingStationType> getCSTList() {
        List<ChargingStationType> list = baseMapper.selectList(new QueryWrapper<ChargingStationType>().lambda().orderByAsc(ChargingStationType::getSttpeId));
        return list;
    }

    @Override
    public Integer removeCSTById(ChargingStationTypeVo vo) {
        ChargingStationType exists = baseMapper.selectOne(new QueryWrapper<ChargingStationType>().lambda().eq(ChargingStationType::getSttpeId, vo.getSttpeId()));
        if (ObjectUtils.isEmpty(exists)) {
            throw new BusinessException("运营商不存在");
        }
        Integer integer = baseMapper.deleteById(vo.getSttpeId());
        return integer;
    }

    @Override
    public Integer saveCST(ChargingStationTypeVo vo) {
        ChargingStationType chargingStationType = new ChargingStationType();
        BeanUtils.copyProperties(vo, chargingStationType);
        ChargingStationType hasName = baseMapper.selectOne(new QueryWrapper<ChargingStationType>().lambda().eq(ChargingStationType::getSttpeName, chargingStationType.getSttpeName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("运营商名称已存在");
        }
        Integer integer = baseMapper.insert(chargingStationType);
        return integer;
    }
}
