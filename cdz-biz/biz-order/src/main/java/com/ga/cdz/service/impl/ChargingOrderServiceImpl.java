package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingOrderMapper;
import com.ga.cdz.dao.charging.ChargingPriceMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.bean.UserFreezeException;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.domain.vo.api.ChargingOrderVo;
import com.ga.cdz.service.IChargingOrderService;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.util.MRedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.stream.events.DTD;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("chargingOrderService")
public class ChargingOrderServiceImpl extends ServiceImpl<ChargingOrderMapper, ChargingOrder> implements IChargingOrderService {

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    IChargingRedisService chargingRedisService;

    @Override
    public Object placeOrderByPrice(ChargingOrderVo vo) {
        /**检测缓存*/
        chargingRedisService.cacheChargingPageList();

        //检测用户是否存在，是否被冻结
        UserInfo userInfo = userInfoMapper.selectById(vo.getUserId());
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new BusinessException("用户不存在");
        }
        if (userInfo.getUserState() == UserInfo.UserState.FREEZE) {
            throw new UserFreezeException();
        }

        //检测充电站是否存在，是否对外开放
        ChargingStation chargingStation = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_STATION, vo.getStationId().toString());
        if (ObjectUtils.isEmpty(chargingStation)) {
            throw new BusinessException("充电站不存在");
        }
        if (chargingStation.getStationType() == ChargingStation.StationType.INSIDE) {
            throw new BusinessException("该充电站不对外提供服务");
        }

        //检测充电桩是否存在，是否空闲
        ChargingDevice chargingDevice = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE, vo.getDeviceId().toString());
        if (ObjectUtils.isEmpty(chargingDevice)) {
            throw new BusinessException("充电桩不存在");
        }
        switch (chargingDevice.getDeviceState()) {
            case ERROR:
                throw new BusinessException("该充电桩故障");
            case USING:
                throw new BusinessException("该充电桩正在使用中");
            default:
                break;
        }

        //检测充电枪是否存在，是否空闲
        ChargingDeviceSub chargingDeviceSub = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE_SUB, vo.getDeviceSubId().toString());
        if (ObjectUtils.isEmpty(chargingDeviceSub)) {
            throw new BusinessException("充电枪不存在");
        }
        switch (chargingDeviceSub.getDeviceSubState()) {
            case ERROR:
                throw new BusinessException("该充电枪故障");
            case USING:
                throw new BusinessException("该充电枪正在使用中");
            default:
                break;
        }

        //获取价格
        List<ChargingPrice> chargingPriceList = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_PRICE, vo.getStationId().toString());
        Date date = new Date();
        ChargingPrice chargingPrice = null;
        for (ChargingPrice chargingPriceTmp : chargingPriceList) {
            long nowData = date.getTime();
            long beginDt = timeToNowDateTimeLong(chargingPriceTmp.getPriceBeginDt(), date);
            long endDt = timeToNowDateTimeLong(chargingPriceTmp.getPriceEndDt(), date);
            if ((nowData - beginDt > 0) && (nowData - endDt < 0)) {
                chargingPrice = chargingPriceTmp;
                break;
            }
        }

        //计算钱是否合适
        BigDecimal price = vo.getTotalPrice().subtract(chargingPrice.getPriceParking()).subtract(chargingPrice.getServicePrice());
        if (price.compareTo(new BigDecimal(0.0)) == -1 || price.equals(0.0)) {
            throw new BusinessException("金额不足");
        }

        //计算时间

        return price;
    }


    /**
     * @author:luqi
     * @description: Time类型转换为当天的时间戳
     * @date:2018/9/14_14:19
     * @param:
     * @return:
     */
    private Long timeToNowDateTimeLong(Time time, Date nowDate) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String nowDateStr = timeFormat.format(nowDate);
        try {
            Date nowDateTime = dateFormat.parse(nowDateStr + time.toString());
            return nowDateTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

}
