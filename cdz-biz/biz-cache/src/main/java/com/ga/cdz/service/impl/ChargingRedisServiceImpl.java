package com.ga.cdz.service.impl;

import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.*;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    ChargingDeviceSubMapper chargingDeviceSubMapper;

    @Resource
    ChargingStationTypeMapper chargingStationTypeMapper;

    @Resource
    ChargingOrderMapper chargingOrderMapper;

    @Resource
    ChargingOrderCommentMapper chargingOrderCommentMapper;


    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public void cacheMainAndNearChargingPageList() {
        cacheChargingStation();
        cacheChargingStationAttach();
        cacheChargingType();
        cacheChargingPrice();
        cacheChargingDevice();
        cacheChargingDeviceGroupByStation();
        cacheChargingDeviceSub();
        cacheChargingStationType();
        cacheChargingOrderCommentList();
/*        cacheChargingOrder();
        cacheChargingOrderComment();*/
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

    private void cacheChargingType() {
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

    private void cacheChargingPrice() {
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

    /**
     * @author:luqi
     * @description: 缓存设备
     * @date:2018/9/14_15:52
     * @param:
     * @return:
     */
    private void cacheChargingDevice() {
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

    /**
     * @author:luqi
     * @description: 缓存设备按照 充电站分组
     * @date:2018/9/14_15:52
     * @param:
     * @return:
     */
    private void cacheChargingDeviceGroupByStation() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_DEVICE_STATION)) {
            List<ChargingDevice> list = chargingDeviceMapper.selectList(null);
            Map<String, List<ChargingDevice>> map = Maps.newHashMap();
            String stationIdStr;
            for (ChargingDevice chargingDevice : list) {
                stationIdStr = chargingDevice.getStationId() + "";
                List<ChargingDevice> mList;
                if (map.containsKey(stationIdStr)) {
                    mList = map.get(stationIdStr);
                } else {
                    mList = Lists.newArrayList();
                }
                mList.add(chargingDevice);
                map.put(stationIdStr, mList);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_DEVICE_STATION, map);
            log.info("TABLE_CHARGING_DEVICE_STATION缓存成功");
        }
    }

    /**
     * @author:luqi
     * @description: 充电桩枪 表缓存
     * @date:2018/9/14_13:39
     * @param:
     * @return:
     */
    private void cacheChargingDeviceSub() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_DEVICE_SUB)) {
            List<ChargingDeviceSub> list = chargingDeviceSubMapper.selectList(null);
            Map<String, ChargingDeviceSub> map = Maps.newHashMap();
            for (ChargingDeviceSub chargingDeviceSub : list) {
                map.put(chargingDeviceSub.getDeviceSubId() + "", chargingDeviceSub);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_DEVICE_SUB, map);
            log.info("TABLE_CHARGING_DEVICE_SUB缓存成功");
        }
    }

    private void cacheChargingStationType() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_STATION_TYPE)) {
            List<ChargingStationType> list = chargingStationTypeMapper.selectList(null);
            Map<String, ChargingStationType> map = Maps.newHashMap();
            for (ChargingStationType chargingStationType : list) {
                map.put(chargingStationType.getSttpeId() + "", chargingStationType);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_STATION_TYPE, map);
            log.info("TABLE_CHARGING_STATION_TYPE缓存成功");
        }
    }

    /**
     * 缓存站点的评论列表
     */
    private void cacheChargingOrderCommentList() {
        if (!mRedisUtil.hasKey(RedisConstant.LIST_CHARGING_STATION_SCORE)) {
            /**站点*/
            List<ChargingStation> stationList = chargingStationMapper.selectList(null);
            /**订单*/
            List<ChargingOrder> orderList = chargingOrderMapper.selectList(null);
            /**订单评论*/
            List<ChargingOrderComment> orderCommentList = chargingOrderCommentMapper.selectList(null);
            /**生成评论的分组map<orderId,评论类>**/
            Map<String, Integer> orderCommentMap = Maps.newHashMap();
            for (ChargingOrderComment chargingOrderComment : orderCommentList) {
                /**只需要根评论**/
                if (chargingOrderComment.getCommentPid().intValue() == 0) {
                    orderCommentMap.put(chargingOrderComment.getOrderId(), chargingOrderComment.getCommentCode());
                }
            }

            /**分组 站有哪些订单 map<stationId，订单id> 判断订单是否为评论*/
            Map<Integer, Set<String>> stationOrderGroup = Maps.newHashMap();
            for (ChargingOrder chargingOrder : orderList) {
                Integer stationIdStr = chargingOrder.getStationId();
                Set<String> set;
                if (stationOrderGroup.containsKey(stationIdStr)) {
                    set = stationOrderGroup.get(stationIdStr);
                } else {
                    set = Sets.newHashSet();
                }
                if (chargingOrder.getOrderState().equals(ChargingOrder.OrderState.COMMENTED)) {
                    set.add(chargingOrder.getOrderId());
                }
                stationOrderGroup.put(chargingOrder.getStationId(), set);
            }
            /**生成评分map*/
            Map<String, Integer> rsMap = Maps.newHashMap();
            for (ChargingStation chargingStation : stationList) {
                //初始化5星评论
                rsMap.put(chargingStation.getStationId() + "", 5);
            }
            /**计算充电站下订单的评论的平均分*/
            for (Integer stationId : stationOrderGroup.keySet()) {
                Set<String> orderIds = stationOrderGroup.get(stationId);
                int size = orderIds.size();
                /**站点下订单分数的总和 为了保险起见 使用BigDecimal**/
                BigDecimal totalCount = orderIds.stream().map(orderId -> BigDecimal.valueOf(orderCommentMap.get(orderId)))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                /**获取到平均分*/
                int score = totalCount.divideToIntegralValue(BigDecimal.valueOf(size)).intValue();
                if (score > 5) {
                    score = 5;
                }
                rsMap.put(stationId + "", score);
            }
            mRedisUtil.pushHashAll(RedisConstant.LIST_CHARGING_STATION_SCORE, rsMap);
        }
    }

    /**
     * @Author: liuyi
     * @Description: 缓存订单信息
     * @Date: 2018/9/18_11:02
     */
    private void cacheChargingOrder() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_ORDER)) {
            List<ChargingOrder> list = chargingOrderMapper.selectList(null);
            Map<String, ChargingOrder> map = Maps.newHashMap();
            for (ChargingOrder chargingOrder : list) {
                map.put(chargingOrder.getOrderId() + "", chargingOrder);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_ORDER, map);
            log.info("TABLE_CHARGING_ORDER缓存成功");
        }
    }

    /**
     * @param chargingOrder 订单实体
     * @return
     * @Author: liuyi
     * @Description: 缓存一条订单信息
     * @Date: 2018/9/18_11:03
     */
    @Override
    public void cacheOneChargingOrder(ChargingOrder chargingOrder) {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_ORDER)) {
            cacheChargingOrder();
        } else {
            mRedisUtil.putHash(RedisConstant.TABLE_CHARGING_ORDER, chargingOrder.getOrderId() + "", chargingOrder);
            log.info("TABLE_CHARGING_ORDER缓存一条数据成功");
        }
    }

    /**
     * @Author: liuyi
     * @Description: 缓存订单评论
     * @Date: 2018/9/18_11:02
     */
    private void cacheChargingOrderComment() {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_ORDER_COMMENT)) {
            List<ChargingOrderComment> list = chargingOrderCommentMapper.selectList(null);
            Map<String, ChargingOrderComment> map = Maps.newHashMap();
            for (ChargingOrderComment chargingOrderComment : list) {
                map.put(chargingOrderComment.getCommentId() + "", chargingOrderComment);
            }
            mRedisUtil.pushHashAll(RedisConstant.TABLE_CHARGING_ORDER_COMMENT, map);
            log.info("TABLE_CHARGING_ORDER_COMMENT缓存成功");
        }
    }

    /**
     * @param chargingOrderComment 订单评论实体
     * @return
     * @Author: liuyi
     * @Description: 缓存一条订单评论
     * @Date: 2018/9/18_11:03
     */
    @Override
    public void cacheOneChargingOrderComment(ChargingOrderComment chargingOrderComment) {
        if (!mRedisUtil.hasKey(RedisConstant.TABLE_CHARGING_ORDER_COMMENT)) {
            cacheChargingOrderComment();
        } else {
            mRedisUtil.putHash(RedisConstant.TABLE_CHARGING_ORDER_COMMENT, chargingOrderComment.getCommentId() + "", chargingOrderComment);
            log.info("TABLE_CHARGING_ORDER_COMMENT缓存一条数据成功");
        }
    }
}

