package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.admin.ChargingStationSelectVo;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingStationService;
import com.ga.cdz.util.MRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站列表服务实现类
 * @date:2018/9/10 15:09
 */
@Slf4j
@Service("mChargingStationService")
public class MChargingStationServiceImpl extends ServiceImpl<ChargingStationMapper, ChargingStation> implements IMChargingStationService {
    /**
     * 区域mapper
     */
    @Resource
    DistrictMapper districtMapper;
    /**
     * 缓存
     */
    @Resource
    MRedisUtil mRedisUtil;

    /**
     *
     */
    @Resource
    AdminInfoMapper adminInfoMapper;
    @Resource
    ChargingShopMapper chargingShopMapper;


    @Override
    public IPage<ChargingStationDTO> getStationPage(PageVo<ChargingStationSelectVo> vo,String name) {
        //构建分页信息
        Page<ChargingStationDTO> page = new Page<>(vo.getIndex(), vo.getSize());
        //复制
        ChargingStationSelectVo param = new ChargingStationSelectVo();
        BeanUtils.copyProperties(vo.getData(), param);
        //分页查询

        List<AdminInfo> list=adminInfoMapper.selectList(new QueryWrapper<AdminInfo>().lambda().eq(AdminInfo::getAdminName,name));
        List<ChargingStationDTO> lists=null;
        if(list.size()>0){
         //为管理员帐号
           lists = baseMapper.getStationList(page, param,new ChargingShop() );
        }else{
            ChargingShop chargingShop = chargingShopMapper.selectOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, name));
          lists = baseMapper.getStationList(page,param,chargingShop);
        }
        //查询后跨库查询区域名称
        lists.forEach(item -> {
            item.setScity(districtMapper.selectById(item.getCity()).getDistrictName());
            item.setSprovince(districtMapper.selectById(item.getProvince()).getDistrictName());
            item.setScountry(districtMapper.selectById(item.getCountry()).getDistrictName());
            item.setScounty(districtMapper.selectById(item.getCounty()).getDistrictName());
        });
        page.setRecords(lists);
        return page;
    }

    @Override
    public IPage<ChargingStationDTO> getStationPageByCon(PageVo<ChargingStationSelectVo> vo) {
        //构建分页信息
        Page<ChargingStationDTO> page = new Page<>(vo.getIndex(), vo.getSize());
        //复制
        ChargingStationSelectVo param = new ChargingStationSelectVo();
        BeanUtils.copyProperties(vo.getData(), param);
        //分页查询
        List<ChargingStationDTO> list = baseMapper.getStationListByCon(page, param);
        //查询后跨库查询区域名称
        list.forEach(item -> {
            item.setScity(districtMapper.selectById(item.getCity()).getDistrictName());
            item.setSprovince(districtMapper.selectById(item.getProvince()).getDistrictName());
            item.setScountry(districtMapper.selectById(item.getCountry()).getDistrictName());
            item.setScounty(districtMapper.selectById(item.getCounty()).getDistrictName());
        });
        page.setRecords(list);
        return page;
    }

    @Override
    public List<ChargingStation> getStationList(ChargingStationVo vo ) {
        List<ChargingStation> chargingStations = baseMapper.selectList(new QueryWrapper<ChargingStation>().lambda().like(ChargingStation::getStationName, vo.getStationName()));
        return chargingStations;
    }

    @Override
    @Transactional
    public boolean updateStationById(ChargingStationVo vo) {
        ChargingStation chargingStation = new ChargingStation();
        BeanUtils.copyProperties(vo, chargingStation);
        //验证充电站名称和编码是否已经存在
        ChargingStation hasName = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationName, vo.getStationName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("该充电站名称已存在");
        }
        hasName = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationCode, vo.getStationCode()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("该充电站编码已存在");
        }
        //保存修改信息
        int result = this.baseMapper.updateById(chargingStation);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public int saveStation(ChargingStationVo vo) {
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
            //返回保存后的Stationid值，否则-1
            if (result) {
                return hasName.getStationId();
            }
            return -1;
        }
        //检查商户编码是否已存在
        ChargingStation hasCode = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationCode, vo.getStationCode()).eq(ChargingStation::getStationState, ChargingStation.StationState.NORMAL));
        if (!ObjectUtils.isEmpty(hasCode)) {
            throw new BusinessException("该充电站编码已存在");
        }
        //充电站存在，只是状态删除则修改状态保存
        hasName = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationCode, vo.getStationCode()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setStationState(ChargingStation.StationState.NORMAL);
            boolean result = updateById(hasName);
            //返回保存后的Stationid值，否则-1
            if (result) {
                return hasName.getStationId();
            }
            return -1;
        }
        //保存商户，商户状态初始化为正常
        chargingStation.setStationState(ChargingStation.StationState.NORMAL);
        boolean value = save(chargingStation);
        //返回保存后的StationId值，否则-1
        if (value) {
            ChargingStation obj = baseMapper.selectOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationName, chargingStation.getStationName()));
            int result = obj.getStationId();
            return result;
        } else {
            return -1;
        }
    }


}
