package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingStationAttachMapper;
import com.ga.cdz.domain.entity.ChargingStationAttach;
import com.ga.cdz.domain.redis.ChargingStationAttachRD;
import com.ga.cdz.service.IMChargingStationAttachService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:luqi
 * @description: 充电桩附件 实现类
 * @date:2018/9/11_20:03
 */
@Slf4j
@Service("mChargingStationAttachService")
public class MChargingStationAttachServiceImpl extends ServiceImpl<ChargingStationAttachMapper, ChargingStationAttach> implements IMChargingStationAttachService {

    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public void getRedisListAll() {
        List<ChargingStationAttach> list = baseMapper.selectList(null);
        Map<String, List<ChargingStationAttachRD>> map = Maps.newHashMap();
        String stationIdStr;
        for (ChargingStationAttach attach : list) {
            stationIdStr = attach.getStationId() + "";
            List<ChargingStationAttachRD> mList;
            if (map.containsKey(stationIdStr)) {
                mList = map.get(stationIdStr);
            } else {
                mList = Lists.newArrayList();
            }
            ChargingStationAttachRD chargingStationAttachRD = new ChargingStationAttachRD();
            BeanUtils.copyProperties(attach, chargingStationAttachRD);
            mList.add(chargingStationAttachRD);
            map.put(stationIdStr, mList);
        }
        mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_ATTACH, map);
        log.info("TABLE_CHARGING_ATTACH缓存成功");
    }
}
