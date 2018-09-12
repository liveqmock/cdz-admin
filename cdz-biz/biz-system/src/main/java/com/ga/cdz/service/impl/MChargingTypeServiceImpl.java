package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingTypeMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.redis.ChargingTypeRD;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;
import com.ga.cdz.service.IMChargingTypeService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:wanzhongsu
 * @description: 充电柱充电方式实现类
 * @date:2018/9/10 9:44
 */
@Slf4j
@Service("mChargingTypeService")
public class MChargingTypeServiceImpl extends ServiceImpl<ChargingTypeMapper, ChargingType> implements IMChargingTypeService {

    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public List<ChargingType> getChargingTypeList() {
        List<ChargingType> list = baseMapper.selectList(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeState, ChargingType.CgtypeState.NORMAL).orderByAsc(ChargingType::getCgtypeId));
        return list;
    }

    @Override
    @Transactional
    public Integer removeChargingTypeById(ChargingTypeVo vo) {
        ChargingType chargingType = new ChargingType();
        BeanUtils.copyProperties(vo, chargingType);
        //验证充电方式id是否存在
        ChargingType hasCount = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeId, chargingType.getCgtypeId()));
        if (ObjectUtils.isEmpty(hasCount)) {
            throw new BusinessException("充电方式ID不存在");
        }
        //删除充电方式
//        Integer integer = baseMapper.deleteById(chargingType.getCgtypeId());
        ChargingType chargingType1 = getById(chargingType.getCgtypeId());
        chargingType1.setCgtypeState(ChargingType.CgtypeState.DELETE);
        updateById(chargingType1);
        return 1;
    }

    @Override
    @Transactional
    public Integer saveChargingTypeObj(ChargingTypeVo vo) {
        ChargingType chargingType = new ChargingType();
        BeanUtils.copyProperties(vo, chargingType);
        //验证充电名称是否已存在
        ChargingType hasName = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeState, ChargingType.CgtypeState.NORMAL).eq(ChargingType::getCgtypeName, chargingType.getCgtypeName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("充电方式名称存在");
        }
        //已存在，修改状态后提交
        hasName = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeName, chargingType.getCgtypeName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setCgtypeState(ChargingType.CgtypeState.NORMAL);
            updateById(hasName);
            return 1;
        }
        /**初始化平台状态为可用*/
        chargingType.setCgtypeState(ChargingType.CgtypeState.NORMAL);
        //添加数据
        Integer integer = baseMapper.insert(chargingType);
        return integer;
    }

    @Override
    public void getRedisListAll() {
        List<ChargingType> list = baseMapper.selectList(null);
        Map<String, ChargingTypeRD> map = Maps.newHashMap();
        for (ChargingType chargingType : list) {
            ChargingTypeRD chargingTypeRD = new ChargingTypeRD();
            BeanUtils.copyProperties(chargingType, chargingTypeRD);
            chargingTypeRD.setCgtypeState(chargingType.getCgtypeState().getValue());
            map.put(chargingType.getCgtypeId() + "", chargingTypeRD);
        }
        mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_TYPE, map);
        log.info("TABLE_CHARGING_TYPE缓存成功");
    }
}
