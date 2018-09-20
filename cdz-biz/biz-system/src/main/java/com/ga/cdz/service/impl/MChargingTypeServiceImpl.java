package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingTypeMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;
import com.ga.cdz.service.IMChargingTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电桩充电方式实现类
 * @date:2018/9/10 9:44
 */
@Slf4j
@Service("mChargingTypeService")
public class MChargingTypeServiceImpl extends ServiceImpl<ChargingTypeMapper, ChargingType> implements IMChargingTypeService {


    @Override
    public List<ChargingType> getChargingTypeList() {
        List<ChargingType> list = baseMapper.selectList(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeState, ChargingType.CgtypeState.NORMAL).orderByAsc(ChargingType::getCgtypeId));
        return list;
    }

    //todo-ok Cedar 返回类型为void，异常在BusinessException中抛出
    @Override
    @Transactional
    public void removeChargingTypeById(ChargingTypeVo vo) {
        ChargingType chargingType = new ChargingType();
        BeanUtils.copyProperties(vo, chargingType);
        //验证充电方式id是否存在
        ChargingType hasCount = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeId, chargingType.getCgtypeId()));
        if (ObjectUtils.isEmpty(hasCount)) {
            throw new BusinessException("充电方式ID不存在");
        }
        //删除充电方式
        ChargingType chargingType1 = getById(chargingType.getCgtypeId());
        chargingType1.setCgtypeState(ChargingType.CgtypeState.DELETE);
        updateById(chargingType1);
    }

    //todo-ok Cedar 返回类型为void，异常在BusinessException中抛出
    //todo-ok Cedar 添加时添加，不是恢复
    @Override
    @Transactional
    public void saveChargingTypeObj(ChargingTypeVo vo) {
        ChargingType chargingType = new ChargingType();
        BeanUtils.copyProperties(vo, chargingType);
        //验证充电名称是否已存在
        ChargingType hasName = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().eq(ChargingType::getCgtypeState, ChargingType.CgtypeState.NORMAL).eq(ChargingType::getCgtypeName, chargingType.getCgtypeName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("充电方式名称存在");
        }
        String nextCode = nextCode();
        chargingType.setCgtypeCode(nextCode);
        /**初始化平台状态为可用*/
        chargingType.setCgtypeState(ChargingType.CgtypeState.NORMAL);
        //添加数据
        save(chargingType);
    }

    /**
     * @author:wanzhongsu
     * @description: 产生充电站编码
     * @date: 2018/9/20 14:42
     * @return: 充电站编码
     */
    private String nextCode() {
        //查询当前最大的充电站编码
        ChargingType type = baseMapper.selectOne(new QueryWrapper<ChargingType>().lambda().orderByDesc(ChargingType::getCgtypeCode).last("limit 1"));
        String nextCode;
        //获取下一个充电站编码
        if (ObjectUtils.isEmpty(type)) {
            nextCode = "0";
        } else {
            nextCode = type.getCgtypeCode();
            int value = Integer.parseInt(nextCode) + 1;
            nextCode = String.format("%1d", value);
            if (nextCode.equals("9")) {
                throw new BusinessException("充电方式编号已用完");
            }
        }
        return nextCode;
    }

}
