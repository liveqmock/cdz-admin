package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingStationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author:wanzhongsu
 * @description: 充电站列表服务实现类
 * @date:2018/9/10 15:09
 */
@Service("mChargingStationService")
public class MChargingStationServiceImpl extends ServiceImpl<ChargingStationMapper, ChargingStation> implements IMChargingStationService {

    @Override
    public IPage<ChargingStation> getStationList(PageVo<ChargingStationVo> vo) {
        //属性复制
        ChargingStation chargingStation = new ChargingStation();
        if (!ObjectUtils.isEmpty(vo.getData())) {
            BeanUtils.copyProperties(vo.getData(), chargingStation);
        }
        //构建分页信息
        Page<ChargingStation> page = new Page<>(vo.getIndex(), vo.getSize());
        page.setAsc("station_id");
        chargingStation.setStationState(ChargingStation.StationState.NORMAL);
        //构建包装器
        QueryWrapper<ChargingStation> wrapper = new QueryWrapper<ChargingStation>(chargingStation);
        //分页查询
        IPage<ChargingStation> iPage = baseMapper.selectPage(page,wrapper);
        return iPage;
    }

    @Override
    public boolean updateStationById(ChargingStationVo vo) {
        ChargingStation chargingStation = new ChargingStation();
        BeanUtils.copyProperties(vo, chargingStation);
        //保存修改信息
        System.out.println(vo);
        int result = this.baseMapper.updateById(chargingStation);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteStationById(ChargingStationVo vo) {
        //根据传入的id判断该充电站是否存在
        ChargingStation hasAccount = getById(vo.getStationId());
        if (ObjectUtils.isEmpty(hasAccount)) {
            throw new BusinessException("充电站ID不存在");
        }
        //将充电站逻辑删除
        ChargingStation chargingStation = getById(vo.getStationId());
        chargingStation.setStationState(ChargingStation.StationState.DELETE);
        boolean result = updateById(chargingStation);
        return result;
    }

    @Override
    public boolean saveStation(ChargingStationVo vo) {
        //属性复制
        ChargingStation chargingStation = new ChargingStation();
        BeanUtils.copyProperties(vo, chargingStation);
        //根据名字查询该名称是否存在
        ChargingStation hasName = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationName, vo.getStationName()).eq(ChargingStation::getStationState, ChargingStation.StationState.NORMAL));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("该充电站名称已存在");
        }
        //充电站存在，只是状态删除则修改状态保存
        hasName = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationName, vo.getStationName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setStationState(ChargingStation.StationState.NORMAL);
            boolean result = updateById(hasName);
            return result;
        }
        //检查商户编码是否已存在
        ChargingStation hasCode = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationCode, vo.getStationCode()).eq(ChargingStation::getStationState, ChargingStation.StationState.NORMAL));
        if (!ObjectUtils.isEmpty(hasCode)) {
            throw new BusinessException("该商户编码已存在");
        }
        //保存商户，商户状态初始化为正常
        chargingStation.setStationState(ChargingStation.StationState.NORMAL);
        chargingStation.setStationType(ChargingStation.StationType.OUTSIDE);
        boolean result = save(chargingStation);
        return result;
    }
}
