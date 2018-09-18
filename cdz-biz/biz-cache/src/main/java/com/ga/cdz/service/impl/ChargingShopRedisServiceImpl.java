package com.ga.cdz.service.impl;

import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.service.IChargingShopRedisService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("chargingShopRedisService")
public class ChargingShopRedisServiceImpl implements IChargingShopRedisService {

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    ChargingShopMapper chargingShopMapper;

    @Override
    public void cacheChargingShopList() {
        cacheChargingShop();
    }

    private void cacheChargingShop() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_SHOP)) {
            List<ChargingShop> list = chargingShopMapper.selectList(null);
            Map<String, ChargingShop> stationMap = Maps.newHashMap();
            for (ChargingShop shop : list) {
                stationMap.put(shop.getShopId() + "", shop);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_SHOP, stationMap);
            log.info("TABLE_CHARGING_SHOP缓存成功");
        }
    }

}
