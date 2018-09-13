package com.ga.cdz.service.impl;

import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.*;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("chargingRedisService")
public class ChargingRedisServiceImpl implements IChargingRedisService {

    @Resource
    ChargingStationMapper chargingStationMapper;

    @Resource
    ChargingStationAttachMapper chargingStationAttachMapper;

    @Resource
    ChargingTypeMapper chargingTypeMapper;

    @Resource
    ChargingPriceMapper chargingPriceMapper;

    @Resource
    ChargingDeviceMapper chargingDeviceMapper;

    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public void cacheChargingPageList() {
        cacheChargingStation();
        cacheChargingStationAttach();
        cacheChargingType();
        cacheChargingPrice();
        cacheChargingDevice();
    }

    private void cacheChargingStation() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_STATION)) {
            List<ChargingStation> list = chargingStationMapper.selectList(null);
            Map<String, ChargingStation> stationMap = Maps.newHashMap();
            for (ChargingStation station : list) {
                stationMap.put(station.getStationId() + "", station);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_STATION, stationMap);
            log.info("TABLE_CHARGING_STATION缓存成功");
        }
    }

    private void cacheChargingStationAttach() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_ATTACH)) {
            List<ChargingStationAttach> list = chargingStationAttachMapper.selectList(null);
            Map<String, List<ChargingStationAttach>> map = Maps.newHashMap();
            String stationIdStr;
            for (ChargingStationAttach attach : list) {
                stationIdStr = attach.getStationId() + "";
                List<ChargingStationAttach> mList;
                if (map.containsKey(stationIdStr)) {
                    mList = map.get(stationIdStr);
                } else {
                    mList = Lists.newArrayList();
                }
                mList.add(attach);
                map.put(stationIdStr, mList);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_ATTACH, map);
            log.info("TABLE_CHARGING_ATTACH缓存成功");
        }
    }

    public void cacheChargingType() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_TYPE)) {
            List<ChargingType> list = chargingTypeMapper.selectList(null);
            Map<String, ChargingType> map = Maps.newHashMap();
            for (ChargingType chargingType : list) {
                map.put(chargingType.getCgtypeId() + "", chargingType);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_TYPE, map);
            log.info("TABLE_CHARGING_TYPE缓存成功");
        }
    }

    public void cacheChargingPrice() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_PRICE)) {
            List<ChargingPrice> list = chargingPriceMapper.selectList(null);
            Map<String, List<ChargingPrice>> map = Maps.newHashMap();
            String stationIdStr;
            for (ChargingPrice chargingPrice : list) {
                stationIdStr = chargingPrice.getStationId() + "";
                List<ChargingPrice> mList;
                if (map.containsKey(stationIdStr)) {
                    mList = map.get(stationIdStr);
                } else {
                    mList = Lists.newArrayList();
                }
                mList.add(chargingPrice);
                map.put(stationIdStr, mList);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_PRICE, map);
            log.info("TABLE_CHARGING_PRICE缓存成功");
        }
    }

    public void cacheChargingDevice() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_DEVICE)) {
            List<ChargingDevice> list = chargingDeviceMapper.selectList(null);
            Map<String, ChargingDevice> map = Maps.newHashMap();
            for (ChargingDevice chargingDevice : list) {
                map.put(chargingDevice.getDeviceId() + "", chargingDevice);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_DEVICE, map);
            log.info("TABLE_CHARGING_DEVICE缓存成功");
        }
    }

}

