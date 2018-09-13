package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingPriceMapper;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingPriceDTO;
import com.ga.cdz.domain.entity.ChargingPrice;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.admin.ChargingPriceVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingPriceService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wanzhongsu
 * @description: 计费标准实现类
 * @date:2018/9/11 10:33
 */
@Slf4j
@Service("mChargingPriceService")
public class MChargingPriceServiceImpl extends ServiceImpl<ChargingPriceMapper, ChargingPrice> implements IMChargingPriceService {

    @Resource
    MRedisUtil mRedisUtil;
    /**
     * 充电站mapper
     */
    @Resource
    private ChargingStationMapper chargingStationMapper;

    @Override
    public Page<ChargingPriceDTO> getPageByType(PageVo<ChargingPriceVo> vo, Integer myPriceType) {
        //获取分页信息
        Page<ChargingPriceDTO> page = new Page<ChargingPriceDTO>(vo.getIndex(), vo.getSize());
        //设置查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("type", myPriceType);
        //查询
        List<ChargingPriceDTO> list = baseMapper.getChargingPricePage(page, map);
        page.setRecords(list);
        return page;
    }

    @Override
    @Transactional
    public boolean saveChargingPriceByKeys(ChargingPriceVo chargingPriceVo) {
        //低谷时间段计费信息
        ChargingPrice low = new ChargingPrice();
        //平谷时间段计费信息
        ChargingPrice middle = new ChargingPrice();
        //高峰时间段计费信息
        ChargingPrice high = new ChargingPrice();
        //获取低谷时间段信息
        low.setStationId(chargingPriceVo.getStationId());
        low.setPriceName(chargingPriceVo.getPriceName());
        low.setPriceType(chargingPriceVo.getPriceType());
        low.setPriceIdx(ChargingPrice.PriceIdx.LOW);
        low.setPriceBeginDt(chargingPriceVo.getLowStart());
        low.setPriceEndDt(chargingPriceVo.getLowEnd());
        low.setChargingPrice(chargingPriceVo.getLowPrice());
        low.setPriceParking(chargingPriceVo.getLowParking());
        low.setServicePrice(chargingPriceVo.getLowService());
        low.setPriceState(ChargingPrice.PriceState.ABLE);
        //获取平谷时间段信息
        middle.setStationId(chargingPriceVo.getStationId());
        middle.setPriceName(chargingPriceVo.getPriceName());
        middle.setPriceType(chargingPriceVo.getPriceType());
        middle.setPriceIdx(ChargingPrice.PriceIdx.MIDDLE);
        middle.setPriceBeginDt(chargingPriceVo.getMiddleStart());
        middle.setPriceEndDt(chargingPriceVo.getMiddleEnd());
        middle.setChargingPrice(chargingPriceVo.getMiddlePrice());
        middle.setPriceParking(chargingPriceVo.getMiddleParking());
        middle.setServicePrice(chargingPriceVo.getMiddleService());
        middle.setPriceState(ChargingPrice.PriceState.ABLE);
        //获取高峰时间段信息
        high.setStationId(chargingPriceVo.getStationId());
        high.setPriceName(chargingPriceVo.getPriceName());
        high.setPriceType(chargingPriceVo.getPriceType());
        high.setPriceIdx(ChargingPrice.PriceIdx.HIGH);
        high.setPriceBeginDt(chargingPriceVo.getHighStart());
        high.setPriceEndDt(chargingPriceVo.getHighEnd());
        high.setChargingPrice(chargingPriceVo.getHighPrice());
        high.setPriceParking(chargingPriceVo.getHighParking());
        high.setServicePrice(chargingPriceVo.getHighService());
        high.setPriceState(ChargingPrice.PriceState.ABLE);
        //判断充电站ID是否存在
        ChargingStation station = chargingStationMapper.selectById(chargingPriceVo.getStationId());
        if (ObjectUtils.isEmpty(station)) {
            throw new BusinessException("充电站ID不存在");
        }
        //持久化
        try {
            save(low);
            save(middle);
            save(high);
        } catch (Exception e) {
            throw new BusinessException("复合主键重复");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateChargingPriceByKeys(ChargingPriceVo chargingPriceVo) {
        //低谷时间段计费
        ChargingPrice low = new ChargingPrice();
        //平谷时间段计费
        ChargingPrice middle = new ChargingPrice();
        //高峰时间段计费
        ChargingPrice high = new ChargingPrice();
        //低谷时间段计费信息获取
        low.setStationId(chargingPriceVo.getStationId());
        low.setPriceName(chargingPriceVo.getPriceName());
        low.setPriceType(chargingPriceVo.getPriceType());
        low.setPriceIdx(ChargingPrice.PriceIdx.LOW);
        low.setPriceBeginDt(chargingPriceVo.getLowStart());
        low.setPriceEndDt(chargingPriceVo.getLowEnd());
        low.setChargingPrice(chargingPriceVo.getLowPrice());
        low.setPriceParking(chargingPriceVo.getLowParking());
        low.setServicePrice(chargingPriceVo.getLowService());
        low.setPriceState(ChargingPrice.PriceState.ABLE);
        //平谷时间段计费信息获取
        middle.setStationId(chargingPriceVo.getStationId());
        middle.setPriceName(chargingPriceVo.getPriceName());
        middle.setPriceType(chargingPriceVo.getPriceType());
        middle.setPriceIdx(ChargingPrice.PriceIdx.MIDDLE);
        middle.setPriceBeginDt(chargingPriceVo.getMiddleStart());
        middle.setPriceEndDt(chargingPriceVo.getMiddleEnd());
        middle.setChargingPrice(chargingPriceVo.getMiddlePrice());
        middle.setPriceParking(chargingPriceVo.getMiddleParking());
        middle.setServicePrice(chargingPriceVo.getMiddleService());
        middle.setPriceState(ChargingPrice.PriceState.ABLE);
        //高峰时间段计费信息获取
        high.setStationId(chargingPriceVo.getStationId());
        high.setPriceName(chargingPriceVo.getPriceName());
        high.setPriceType(chargingPriceVo.getPriceType());
        high.setPriceIdx(ChargingPrice.PriceIdx.HIGH);
        high.setPriceBeginDt(chargingPriceVo.getHighStart());
        high.setPriceEndDt(chargingPriceVo.getHighEnd());
        high.setChargingPrice(chargingPriceVo.getHighPrice());
        high.setPriceParking(chargingPriceVo.getHighParking());
        high.setServicePrice(chargingPriceVo.getHighService());
        high.setPriceState(ChargingPrice.PriceState.ABLE);
        //充电站id是否存在
        ChargingStation station = chargingStationMapper.selectById(chargingPriceVo.getStationId());
        if (ObjectUtils.isEmpty(station)) {
            throw new BusinessException("充电站ID不存在");
        }
        //持久化到数据库
        try {
            //持久化低谷
            QueryWrapper<ChargingPrice> wrapper = new QueryWrapper();
            wrapper.lambda().eq(ChargingPrice::getStationId, low.getStationId()).eq(ChargingPrice::getPriceState, low.getPriceState())
                    .eq(ChargingPrice::getPriceType, low.getPriceType());
            low.setPriceState(null).setPriceType(null).setStationId(null).setPriceIdx(null);
            update(low, wrapper);
            //持久化平谷
            QueryWrapper<ChargingPrice> wrapper1 = new QueryWrapper();
            wrapper1.lambda().eq(ChargingPrice::getStationId, middle.getStationId()).eq(ChargingPrice::getPriceState, middle.getPriceState())
                    .eq(ChargingPrice::getPriceType, middle.getPriceType());
            middle.setPriceState(null).setPriceType(null).setStationId(null).setPriceIdx(null);
            update(middle, wrapper1);
            //持久化高峰
            QueryWrapper<ChargingPrice> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().eq(ChargingPrice::getStationId, high.getStationId()).eq(ChargingPrice::getPriceState, high.getPriceState())
                    .eq(ChargingPrice::getPriceType, high.getPriceType());
            high.setPriceState(null).setPriceType(null).setStationId(null).setPriceIdx(null);
            update(high, wrapper2);
        } catch (Exception e) {
            throw new BusinessException("修改失败");
        }
        return true;
    }


    @Override
    public void getRedisListAll() {
        List<ChargingPrice> list = baseMapper.selectList(null);
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
