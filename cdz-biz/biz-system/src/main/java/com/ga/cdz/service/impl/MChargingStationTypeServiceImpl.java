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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站运营类型服务实现类
 * @date:2018/9/10 9:53
 */
@Service("mChargingStationTypeService")
public class MChargingStationTypeServiceImpl extends ServiceImpl<ChargingStationTypeMapper, ChargingStationType> implements IMChargingStationTypeService {

    @Override
    public List<ChargingStationType> getCSTList() {
        List<ChargingStationType> list = baseMapper.selectList(new QueryWrapper<ChargingStationType>().lambda().eq(ChargingStationType::getSttpeState, ChargingStationType.SttpeState.NORMAL).orderByAsc(ChargingStationType::getSttpeId));
        return list;
    }

    @Override
    @Transactional
    public Integer removeCSTById(ChargingStationTypeVo vo) {
        //查询运营商id是否存在
        ChargingStationType exists = baseMapper.selectOne(new QueryWrapper<ChargingStationType>().lambda().eq(ChargingStationType::getSttpeId, vo.getSttpeId()));
        if (ObjectUtils.isEmpty(exists)) {
            throw new BusinessException("运营商不存在");
        }
        //逻辑删除
//        Integer integer = baseMapper.deleteById(vo.getSttpeId());
        ChargingStationType delete = getById(vo.getSttpeId());
        delete.setSttpeState(ChargingStationType.SttpeState.DELETE);
        updateById(delete);
        return 1;
    }

    @Override
    @Transactional
    public Integer saveCST(ChargingStationTypeVo vo) {
        ChargingStationType chargingStationType = new ChargingStationType();
        BeanUtils.copyProperties(vo, chargingStationType);
        //查询运营商名称是否存在
        ChargingStationType hasName = baseMapper.selectOne(new QueryWrapper<ChargingStationType>().lambda().eq(ChargingStationType::getSttpeState, ChargingStationType.SttpeState.NORMAL).eq(ChargingStationType::getSttpeName, chargingStationType.getSttpeName()));
        if (!ObjectUtils.isEmpty(hasName) && hasName.getSttpeState() == ChargingStationType.SttpeState.NORMAL) {
            throw new BusinessException("运营商名称已存在");
        }
        //已存在，修改状态后保存
        hasName = baseMapper.selectOne(new QueryWrapper<ChargingStationType>().lambda().eq(ChargingStationType::getSttpeName, chargingStationType.getSttpeName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setSttpeState(ChargingStationType.SttpeState.NORMAL);
            updateById(hasName);
            return 1;
        }
        /**初始化平台状态为可用*/
        chargingStationType.setSttpeState(ChargingStationType.SttpeState.NORMAL);
        //保存运营商名称
        Integer integer = baseMapper.insert(chargingStationType);
        return integer;
    }
}
