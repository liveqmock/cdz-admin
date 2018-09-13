package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.service.IChargingStationService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:luqi
 * @description: 客户端 充电站接口实现类
 * @date:2018/9/13_15:51
 */
@Slf4j
@Service("chargingStationService")
public class ChargingStationServiceImpl extends ServiceImpl<ChargingStationMapper, ChargingStation> implements IChargingStationService {

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    MUtil mUtil;

    @Resource
    IChargingRedisService chargingRedisService;


    @Override
    public IPage<ChargingStation> getStationPage(PageVo<ChargingStationVo> vo) {
        /**检测缓存*/
        chargingRedisService.cacheChargingPageList();
        /**获取站的列表*/
        List<ChargingStation> chargingStationList = mRedisUtil.getHashOfList(RedisConstant.TABLE_CHARGING_STATION);

        return null;
    }

}
