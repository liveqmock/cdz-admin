package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingOrderMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.bean.Paging;
import com.ga.cdz.domain.bean.UserFreezeException;
import com.ga.cdz.domain.dto.api.ChargingOrderListDTO;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.domain.vo.api.ChargingOrderInitVo;
import com.ga.cdz.domain.vo.api.ChargingOrderPageListVo;
import com.ga.cdz.service.IChargingOrderService;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("chargingOrderService")
public class ChargingOrderServiceImpl extends ServiceImpl<ChargingOrderMapper, ChargingOrder> implements IChargingOrderService {

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    ChargingOrderMapper chargingOrderMapper;

    @Resource
    IChargingRedisService chargingRedisService;

    //获得充电站信息路径
    @Value("${url.station}")
    private String stationUrl;

    @Override
    public List<ChargingOrderListDTO> getChargingOrderOfAllPageList(ChargingOrderPageListVo vo) {
        List<ChargingOrderListDTO> chargingOrderListTmp = getChargingOrderList(vo.getUserId());
        if (!chargingOrderListTmp.isEmpty() && chargingOrderListTmp.size() > 0) {
            List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderListTmp.stream()
                    .filter(chargingOrderListDTO -> chargingOrderListDTO.getOrderState() != ChargingOrder.OrderState.REMOVE)
                    .collect(Collectors.toList());
            Paging<ChargingOrderListDTO> page = new Paging<>(chargingOrderListDTOList, vo.getPageIndex(), vo.getPageSize());
            List<ChargingOrderListDTO> colList = page.getList();
            return colList;
        }
        return Lists.newArrayList();
    }

    @Override
    public List<ChargingOrderListDTO> getChargingOrderOfInitPageList(ChargingOrderPageListVo vo) {
        List<ChargingOrderListDTO> chargingOrderListTmp = getChargingOrderList(vo.getUserId());
        if (!chargingOrderListTmp.isEmpty() && chargingOrderListTmp.size() > 0) {
            List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderListTmp.stream()
                    .filter(chargingOrderListDTO -> chargingOrderListDTO.getOrderState() == ChargingOrder.OrderState.INIT)
                    .collect(Collectors.toList());
            Paging<ChargingOrderListDTO> page = new Paging<>(chargingOrderListDTOList, vo.getPageIndex(), vo.getPageSize());
            List<ChargingOrderListDTO> colList = page.getList();
            return colList;
        }
        return Lists.newArrayList();
    }

    @Override
    public List<ChargingOrderListDTO> getOrderListOfPayingPageList(ChargingOrderPageListVo vo) {
        List<ChargingOrderListDTO> chargingOrderListTmp = getChargingOrderList(vo.getUserId());
        if (!chargingOrderListTmp.isEmpty() && chargingOrderListTmp.size() > 0) {
            List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderListTmp.stream()
                    .filter(chargingOrderListDTO -> chargingOrderListDTO.getOrderState() == ChargingOrder.OrderState.PAYING)
                    .collect(Collectors.toList());
            Paging<ChargingOrderListDTO> page = new Paging<>(chargingOrderListDTOList, vo.getPageIndex(), vo.getPageSize());
            List<ChargingOrderListDTO> colList = page.getList();
            return colList;
        }
        return Lists.newArrayList();
    }

    @Override
    public List<ChargingOrderListDTO> getOrderListOfPayedPageList(ChargingOrderPageListVo vo) {
        List<ChargingOrderListDTO> chargingOrderListTmp = getChargingOrderList(vo.getUserId());
        if (!chargingOrderListTmp.isEmpty() && chargingOrderListTmp.size() > 0) {
            List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderListTmp.stream()
                    .filter(chargingOrderListDTO -> chargingOrderListDTO.getOrderState() == ChargingOrder.OrderState.PAYED)
                    .collect(Collectors.toList());
            Paging<ChargingOrderListDTO> page = new Paging<>(chargingOrderListDTOList, vo.getPageIndex(), vo.getPageSize());
            List<ChargingOrderListDTO> colList = page.getList();
            return colList;
        }
        return Lists.newArrayList();
    }

    @Override
    public List<ChargingOrderListDTO> getOrderListOfRefundingPageList(ChargingOrderPageListVo vo) {
        List<ChargingOrderListDTO> chargingOrderListTmp = getChargingOrderList(vo.getUserId());
        if (!chargingOrderListTmp.isEmpty() && chargingOrderListTmp.size() > 0) {
            List<ChargingOrderListDTO> chargingOrderListDTOList = chargingOrderListTmp.stream()
                    .filter(chargingOrderListDTO -> chargingOrderListDTO.getOrderState() == ChargingOrder.OrderState.REFUNDING)
                    .collect(Collectors.toList());
            Paging<ChargingOrderListDTO> page = new Paging<>(chargingOrderListDTOList, vo.getPageIndex(), vo.getPageSize());
            List<ChargingOrderListDTO> colList = page.getList();
            return colList;
        }
        return Lists.newArrayList();
    }

    @Override
    public Integer placeOrderByPrice(ChargingOrderInitVo vo) {
        /**检测缓存*/
        chargingRedisService.cacheChargingStationDetail();

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
        if (chargingStation.getStationState() == ChargingStation.StationState.DELETE) {
            throw new BusinessException("该充电站已删除");
        }

        //检测充电桩是否存在，是否空闲
        ChargingDevice chargingDevice = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE, vo.getDeviceId().toString());
        if (ObjectUtils.isEmpty(chargingDevice)) {
            throw new BusinessException("充电桩不存在");
        }
        if (chargingDevice.getStationId() != vo.getStationId()) {
            throw new BusinessException("充电桩与充电站不匹配");
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
            long nowDate = date.getTime();
            long beginDt = timeToNowDateTimeLong(chargingPriceTmp.getPriceBeginDt(), date);
            long endDt = timeToNowDateTimeLong(chargingPriceTmp.getPriceEndDt(), date);
            if ((nowDate - beginDt > 0) && (nowDate - endDt < 0)) {
                chargingPrice = chargingPriceTmp;
                break;
            }
        }

        //计算钱是否合适
        if (chargingPrice.getChargingPrice().compareTo(new BigDecimal(0)) == -1
                || chargingPrice.getChargingPrice().equals(0)) {
            throw new BusinessException("充电金额错误");
        }
        BigDecimal price = vo.getTotalPrice().subtract(chargingPrice.getPriceParking()).subtract(chargingPrice.getServicePrice());
        if (price.compareTo(new BigDecimal(0)) == -1 || price.equals(0)) {
            throw new BusinessException("金额不足");
        }

        //计算时间
        double amount = price.divide(chargingPrice.getChargingPrice(), 2).doubleValue();
        long time = (long) (((amount * 3600) / chargingDevice.getDevicePower()) * 1000);
        Date finalDate = new Date(time + date.getTime());

        //先获得数据库中订单最后一份的ID
        String orderId = generateOrderId(userInfo.getUserId(), date.getTime());
        ChargingOrder chargingOrder = new ChargingOrder();
        chargingOrder.setOrderId(orderId).setUserId(userInfo.getUserId())
                .setStationId(vo.getStationId())
                .setDeviceId(vo.getDeviceId())
                .setDeviceSubId(vo.getDeviceSubId())
                .setTotalPrice(vo.getTotalPrice())
                .setTotalEnergy(amount)
                .setCharginBeginDt(date)
                .setCharginEndDt(finalDate)
                .setOrderState(ChargingOrder.OrderState.INIT);
        return insertAnOrder(chargingOrder);
    }

    /**
     * @param userId 用户id
     * @return
     * @Author: liuyi
     * @Description: 搜索出该用户的全部订单
     * @Date: 2018/9/19_14:36
     */
    private List<ChargingOrderListDTO> getChargingOrderList(Integer userId) {
        //检测缓存
        chargingRedisService.cacheChargingStationDetail();
        //获取缓存的订单信息
        List<ChargingOrder> chargingOrderList = mRedisUtil.getHashOfList(RedisConstant.TABLE_CHARGING_ORDER);
        //List<ChargingOrder> userOrderList=chargingOrderList.stream().filter(chargingOrder ->chargingOrder.getUserId().equals(userId)).collect(Collectors.toList());
        List<ChargingOrderListDTO> ChargingOrderListDTOList = chargingOrderList.stream().filter(chargingOrder -> chargingOrder.getUserId().equals(userId)).map(chargingOrder -> {
            ChargingOrderListDTO chargingOrderListDTO = new ChargingOrderListDTO();
            chargingOrderListDTO.setChargingOrder(chargingOrder);
            ChargingStation chargingStation = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_STATION, chargingOrder.getStationId().toString());
            List<ChargingStationAttach> chargingStationAttach = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_ATTACH, chargingOrder.getStationId().toString());
            chargingOrderListDTO.setStationName(chargingStation.getStationName());
            if (!chargingStationAttach.isEmpty() && chargingStationAttach.size() > 0) {
                chargingOrderListDTO.setAttachPath(stationUrl + chargingStationAttach.get(0).getAttachPath());
            }
            return chargingOrderListDTO;
        }).collect(Collectors.toList());
        return ChargingOrderListDTOList;
    }

    /**
     * @param chargingOrder 订单信息
     * @return 返回状态
     * @Author: liuyi
     * @Description: 插入数据库
     * @Date: 2018/9/17_14:02
     */
    @Transactional
    Integer insertAnOrder(ChargingOrder chargingOrder) {
        Integer result = chargingOrderMapper.insert(chargingOrder);
        return result;
    }

    /**
     * @param userId 用户id
     * @param date   下单时间
     * @return 生成的OrderId
     * @Author: liuyi
     * @Description: 生成OrderId
     * @Date: 2018/9/17_13:35
     */
    private String generateOrderId(int userId, long date) {
        return String.valueOf(userId) + String.valueOf(date / 1000);
    }

    /**
     * @param time
     * @param nowDate
     * @return
     * @author:luqi
     * @description: Time类型转换为当天的时间戳
     * @date:2018/9/14_14:19
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
