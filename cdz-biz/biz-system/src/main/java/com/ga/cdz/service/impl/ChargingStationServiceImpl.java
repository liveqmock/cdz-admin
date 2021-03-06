package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.domain.bean.Paging;
import com.ga.cdz.domain.dto.api.ChargingStationCommentDTO;
import com.ga.cdz.domain.dto.api.ChargingStationDetailDTO;
import com.ga.cdz.domain.dto.api.ChargingStationPageDTO;
import com.ga.cdz.domain.dto.api.ChargingStationTerminalDTO;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.domain.vo.api.ChargingStationDetailVo;
import com.ga.cdz.domain.vo.api.ChargingStationPageVo;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.service.IChargingShopRedisService;
import com.ga.cdz.service.IChargingStationService;
import com.ga.cdz.service.IUserRedisService;
import com.ga.cdz.util.MDistanceUtil;
import com.ga.cdz.util.MRedisUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author:luqi
 * @description: 客户端 充电站接口实现类
 * @date:2018/9/13_15:51
 */
@Slf4j
@Service("chargingStationService")
public class ChargingStationServiceImpl extends ServiceImpl<ChargingStationMapper, ChargingStation> implements IChargingStationService {

    private DateTimeFormatter jodaTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-ddHH:mm:ss");

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    MDistanceUtil mDistanceUtil;

    @Resource
    IUserRedisService userRedisService;

    @Resource
    IChargingRedisService chargingRedisService;

    @Resource
    IChargingShopRedisService chargingShopRedisService;

    @Value("${url.station}")
    private String stationUrl;

    @Value("${url.user_avatar}")
    private String userAvatarUrl;

    @Override
    public List<ChargingStationPageDTO> getMainStationPage(ChargingStationPageVo vo) {
        /**默认按照离我最近*/
        vo.setDistanceType(ChargingStationPageVo.DistanceType.NEAREST);
        /**默认按照价格最低**/
        vo.setPriceType(ChargingStationPageVo.PriceType.MIN);
        return getStationPageBySort(vo);
    }

    @Override
    public List<ChargingStationPageDTO> getNearStationPage(ChargingStationPageVo vo) {
        return getStationPageBySort(vo);
    }


    /**
     * @author:luqi
     * @description: 获取电站的分页列表，根据条件排序
     * @date:2018/9/16 09:38
     * @param:
     * @return:
     */
    private List<ChargingStationPageDTO> getStationPageBySort(ChargingStationPageVo vo) {
        /**检测缓存*/
        chargingRedisService.cacheMainAndNearChargingPageList();
        Integer pageIndex = vo.getIndex();
        Integer pageSize = vo.getSize();
        double voLat = vo.getLat();
        double voLng = vo.getLng();
        String deviceNo = vo.getDeviceNo();
        String mainDeviceKey = RedisConstant.LIST_MAIN_DEVICE + deviceNo;
        /**如果页码为1，清除设备分页缓存列表*/
        if (pageIndex == 1) {
            mRedisUtil.remove(mainDeviceKey);
        }
        Integer cityCode = vo.getCityCode();
        DecimalFormat df = new DecimalFormat("#.0");
        /**获取站的列表*/
        List<ChargingStation> chargingStationList = mRedisUtil.getHashOfList(RedisConstant.TABLE_CHARGING_STATION);
        String cityRedisKey = RedisConstant.LIST_CITY_STATION + cityCode;
        List<ChargingStation> listCity;
        /**将城市站点列表缓存，5分钟**/
        if (!mRedisUtil.hasKey(cityRedisKey)) {
            listCity = chargingStationList.stream()
                    .filter(chargingStation ->
                            cityCode == chargingStation.getCity().intValue()
                                    && chargingStation.getStationState().equals(ChargingStation.StationState.NORMAL)
                    )
                    .collect(Collectors.toList());
            mRedisUtil.put(cityRedisKey, listCity, 5L, TimeUnit.MINUTES);
        } else {
            listCity = mRedisUtil.get(cityRedisKey);
        }
        List<ChargingStationPageDTO> page;
        /**判断是否有设备分页缓存*/
        if (!mRedisUtil.hasKey(mainDeviceKey)) {
            /**列表转换,查询城市编码相同的数据*/
            page = listCity.stream().map(chargingStation -> {
                ChargingStationPageDTO chargingStationPageDTO = new ChargingStationPageDTO();
                chargingStationPageDTO.setStationId(chargingStation.getStationId());
                chargingStationPageDTO.setStationName(chargingStation.getStationName());
                chargingStationPageDTO.setLat(chargingStation.getLat());
                chargingStationPageDTO.setSttpeId(chargingStation.getSttpeId());
                chargingStationPageDTO.setLng(chargingStation.getLng());
                double distance = mDistanceUtil.getDistance(voLng, voLat, chargingStation.getLng(), chargingStation.getLat());
                chargingStationPageDTO.setDistance(Double.parseDouble(df.format(distance)));
                /**计算距离**/
                return chargingStationPageDTO;
            }).collect(Collectors.toList());
            /**距离排序**/
            ChargingStationPageVo.DistanceType distanceType = vo.getDistanceType();
            switch (distanceType) {
                case KM_10:
                    page = page.stream().filter(chargingStationPageDTO ->
                            Double.doubleToRawLongBits(chargingStationPageDTO.getDistance())
                                    <= Double.doubleToRawLongBits(ChargingStationPageVo.DistanceType.KM_10.getValue()))
                            .collect(Collectors.toList());
                    break;
                case KM_20:
                    page = page.stream().filter(chargingStationPageDTO ->
                            Double.doubleToRawLongBits(chargingStationPageDTO.getDistance())
                                    <= Double.doubleToRawLongBits(ChargingStationPageVo.DistanceType.KM_20.getValue()))
                            .collect(Collectors.toList());
                    break;
                case KM_40:
                    page = page.stream().filter(chargingStationPageDTO ->
                            Double.doubleToRawLongBits(chargingStationPageDTO.getDistance())
                                    <= Double.doubleToRawLongBits(ChargingStationPageVo.DistanceType.KM_40.getValue()))
                            .collect(Collectors.toList());
                    break;
                default:
                    /**默认离我最近，不需要条件过滤*/
                    break;
            }
            /**距离升序排列*/
            page.sort(Comparator.comparing(ChargingStationPageDTO::getDistance));
            /**缓存5分钟*/
            mRedisUtil.put(mainDeviceKey, page, 5L, TimeUnit.MINUTES);
        } else {
            page = mRedisUtil.get(mainDeviceKey);
        }
        /**分页*/
        Paging<ChargingStationPageDTO> mPage = new Paging<>(page, pageIndex, pageSize);
        /**得到分页后的数据*/
        List<ChargingStationPageDTO> pageList = mPage.getList();
        /**补全站点的信息*/
        /**获取站的价格缓存列表*/
        Date nowTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = timeFormat.format(nowTime);
        long nowTimeLong = nowTime.getTime();
        Map<String, List<ChargingPrice>> priceMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_PRICE);
        /**获取站的评分缓存列表**/
        Map<String, Integer> scoreMap = mRedisUtil.getHash(RedisConstant.LIST_CHARGING_STATION_SCORE);
        /**获取站的附件表*/
        Map<String, List<ChargingStationAttach>> stationAttach = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_ATTACH);
        /**获取站的类型*/
        Map<String, ChargingStationType> chargingStationTypeMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_STATION_TYPE);
        /**设备列表按站点分组*/
        Map<String, List<ChargingDevice>> chargingDeviceMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE_STATION);
        /**设备类型*/
        Map<String, ChargingType> chargingTypeMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_TYPE);
        List<ChargingStationPageDTO> rsList = pageList.stream().map(chargingStationPageDTO -> {
            String stationKey = chargingStationPageDTO.getStationId() + "";
            /**分数**/
            Integer score = scoreMap.get(stationKey);
            if (score == null) {
                chargingStationPageDTO.setScore(5);
            } else {
                chargingStationPageDTO.setScore(score);
            }
            /**当前价格默认值，为了避免出错,初始值1.7块每度**/
            chargingStationPageDTO.setNowPrice(1.7D);
            /**当前价格*/
            List<ChargingPrice> priceList = priceMap.get(stationKey);
            if (priceList != null && pageList.size() > 0) {
                for (ChargingPrice chargingPrice : priceList) {
                    long beginDt = timeToNowDateTimeLong(chargingPrice.getPriceBeginDt(), nowDateStr);
                    long endDt = timeToNowDateTimeLong(chargingPrice.getPriceEndDt(), nowDateStr);
                    /**判断是否在当前时间段**/
                    if (nowTimeLong >= beginDt && nowTimeLong <= endDt) {
                        /**赋值价格，转为Double类*/
                        chargingStationPageDTO.setNowPrice(chargingPrice.getChargingPrice().doubleValue());
                    }
                }
            }
            /**列表图片，默认值 空字符串**/
            chargingStationPageDTO.setPicUrl("");
            List<ChargingStationAttach> stationAttachList = stationAttach.get(stationKey);
            if (!ObjectUtils.isEmpty(stationAttachList) && stationAttachList.size() > 0) {
                //拿到第一个附件的
                ChargingStationAttach firstStationAttach = stationAttachList.get(0);
                if (!ObjectUtils.isEmpty(firstStationAttach)) {
                    chargingStationPageDTO.setPicUrl(stationUrl + firstStationAttach.getAttachPath());
                }
            }
            /**标签**/
            String tags = "";
            ChargingStationType chargingStationType = chargingStationTypeMap.get(chargingStationPageDTO.getSttpeId() + "");
            if (!ObjectUtils.isEmpty(chargingStationType)) {
                tags += chargingStationType.getSttpeName() + ";";
            }
            tags += "对外开放;免费停车";
            chargingStationPageDTO.setTags(tags);
            /**快充慢充*/
            Integer fastNum = 0;
            Integer slowNum = 0;
            Integer fastTotal = 0;
            Integer slowTotal = 0;
            /**获取当前站点的所有设备*/
            List<ChargingDevice> chargingDeviceList = chargingDeviceMap.get(stationKey);
            if (!ObjectUtils.isEmpty(chargingDeviceList) && chargingDeviceList.size() > 0) {
                for (ChargingDevice chargingDevice : chargingDeviceList) {
                    Integer cgtypeId = chargingDevice.getCgtypeId();
                    ChargingType chargingType = chargingTypeMap.get(cgtypeId + "");
                    if (!ObjectUtils.isEmpty(chargingType)) {
                        if (chargingType.getCgtypeMode().equals(ChargingType.CgtypeMode.FAST)) {
                            //快充
                            fastTotal += 1;
                            if (chargingDevice.getDeviceState().equals(ChargingDevice.DeviceState.USING)) {
                                fastNum += 1;
                            }
                        } else if (chargingType.getCgtypeMode().equals(ChargingType.CgtypeMode.SLOW)) {
                            //慢冲
                            slowTotal += 1;
                            if (chargingDevice.getDeviceState().equals(ChargingDevice.DeviceState.USING)) {
                                slowNum += 1;
                            }
                        }
                    }
                }
            }
            chargingStationPageDTO.setFastNum(fastNum);
            chargingStationPageDTO.setFastTotal(fastTotal);
            chargingStationPageDTO.setSlowNum(slowNum);
            chargingStationPageDTO.setSlowTotal(slowTotal);
            return chargingStationPageDTO;
        }).collect(Collectors.toList());
        /**价格排序**/
        ChargingStationPageVo.PriceType priceType = vo.getPriceType();
        switch (priceType) {
            case MIN:
                rsList.sort(Comparator.comparing(ChargingStationPageDTO::getNowPrice));
                break;
            case MAX:
                rsList.sort(Comparator.comparing(ChargingStationPageDTO::getNowPrice).reversed());
                break;
        }
        return rsList;
    }

    /**
     * @param vo ChargingStationDetailVo
     * @return ChargingStationDetailDTO
     * @Author: liuyi
     * @Description: 获取充电站信息
     * @Date: 2018/9/17_14:53
     */
    @Override
    public ChargingStationDetailDTO getChargingStationDetail(ChargingStationDetailVo vo) {
        chargingRedisService.cacheChargingStationDetail();
        chargingShopRedisService.cacheChargingShopList();
        ChargingStationDetailDTO chargingStationDetailDTO = new ChargingStationDetailDTO();
        //获得充电站缓存列表
        ChargingStation chargingStation = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_STATION, vo.getStationId().toString());
        //获得充电站商户缓存列表
        ChargingShop chargingShop = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_SHOP, chargingStation.getShopId().toString());
        //获得充电站价格缓存列表
        Date nowTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = timeFormat.format(nowTime);
        long nowTimeLong = nowTime.getTime();
        Map<String, List<ChargingPrice>> priceMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_PRICE);
        //当前价格
        List<ChargingPrice> priceList = priceMap.get(vo.getStationId() + "");
        if (priceList != null) {
            for (ChargingPrice chargingPrice : priceList) {
                long beginDt = timeToNowDateTimeLong(chargingPrice.getPriceBeginDt(), nowDateStr);
                long endDt = timeToNowDateTimeLong(chargingPrice.getPriceEndDt(), nowDateStr);
                //判断是否在当前时间段
                if (nowTimeLong >= beginDt && nowTimeLong <= endDt) {
                    chargingStationDetailDTO.setChargingPrice(chargingPrice);
                    break;
                }
            }
        }
        chargingStationDetailDTO.setChargingStation(chargingStation);
        chargingStationDetailDTO.setChargingShop(chargingShop);
        return chargingStationDetailDTO;
    }

    /**
     * @param vo ChargingStationDetailVo
     * @return List<ChargingStationTerminalDTO>
     * @Author: liuyi
     * @Description: 获取充电站终端
     * @Date: 2018/9/17_15:44
     */
    @Override
    public List<ChargingStationTerminalDTO> getChargingStationTerminal(ChargingStationDetailVo vo) {
        chargingRedisService.cacheChargingStationDetail();
        Map<String, List<ChargingDevice>> chargingDeviceMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_DEVICE_STATION);
        Map<String, ChargingType> chargingTypeMap = mRedisUtil.getHash(RedisConstant.TABLE_CHARGING_TYPE);

        /**获取当前站点的所有设备*/
        List<ChargingStationTerminalDTO> chargingStationTerminalList = new ArrayList<>();
        List<ChargingDevice> chargingDeviceList = chargingDeviceMap.get(vo.getStationId() + "");
        if (!ObjectUtils.isEmpty(chargingDeviceList) && chargingDeviceList.size() > 0) {
            for (ChargingDevice chargingDevice : chargingDeviceList) {
                Integer cgtypeId = chargingDevice.getCgtypeId();
                ChargingType chargingType = chargingTypeMap.get(cgtypeId + "");
                ChargingStationTerminalDTO chargingStationTerminal = new ChargingStationTerminalDTO();
                chargingStationTerminal.setChargingDevice(chargingDevice);
                if (!ObjectUtils.isEmpty(chargingType)) {
                    chargingStationTerminal.setChargingType(chargingType);
                }
                chargingStationTerminalList.add(chargingStationTerminal);
            }
        }
        return chargingStationTerminalList;
    }

    @Override
    public List<ChargingStationCommentDTO> getChargingStationComment(ChargingStationDetailVo vo) {
        userRedisService.cacheUserList();
        chargingRedisService.cacheChargingStationDetail();
        //获取所有缓存的用户信息
        Map<String, UserInfo> userInfoMap = mRedisUtil.getHash(RedisConstant.TABLE_USER_INFO);
        //获取所有缓存的订单
        List<ChargingOrder> chargingOrderList = mRedisUtil.getHashOfList(RedisConstant.TABLE_CHARGING_ORDER);
        //获取所有缓存的评论
        List<ChargingOrderComment> chargingOrderCommentList = mRedisUtil.getHashOfList(RedisConstant.TABLE_CHARGING_ORDER_COMMENT);
        //无订单或无评论
        if ((chargingOrderList.isEmpty() || chargingOrderList.size() <= 0)
                || (chargingOrderCommentList.isEmpty() || chargingOrderCommentList.size() <= 0)) {
            return Lists.newArrayList();
        }

        //根据stationId得到符合的订单
        List<String> orderIdList = chargingOrderList.stream()
                .filter(chargingOrder -> chargingOrder.getStationId().equals(vo.getStationId()))
                .map(chargingOrder -> chargingOrder.getOrderId())
                .collect(Collectors.toList());
        //无符合的订单
        if (orderIdList.isEmpty() || orderIdList.size() <= 0) {
            return Lists.newArrayList();
        }

        //根据orderId得到评论
        List<ChargingStationCommentDTO> chargingStationCommentList = chargingOrderCommentList.stream()
                .filter(chargingOrderComment -> chargingOrderComment.getCommentPid() == 0)
                .map(chargingOrderComment -> {
                    ChargingStationCommentDTO chargingStationComment = new ChargingStationCommentDTO();
                    for (String orderId : orderIdList) {
                        if (chargingOrderComment.getOrderId().equals(orderId)) {
                            chargingStationComment.setChargingOrderComment(chargingOrderComment);
                            UserInfo userInfo = userInfoMap.get(chargingOrderComment.getUserId().toString());
                            chargingStationComment.setUserRealName(userInfo.getUserRealName())
                                    .setUserAvatar(userAvatarUrl + userInfo.getUserAvatar());
                        }
                    }
                    return chargingStationComment;
                }).collect(Collectors.toList());
        //无符合的评论
        if (chargingStationCommentList.isEmpty() || chargingStationCommentList.size() <= 0) {
            return Lists.newArrayList();
        }

        //分页
        Paging<ChargingStationCommentDTO> mPage = new Paging<>(chargingStationCommentList, vo.getPageIndex(), vo.getPageSize());
        List<ChargingStationCommentDTO> cscList = mPage.getList();
        return cscList;
    }

    /**
     * @author:luqi
     * @description: Time类型转换为当天的时间戳
     * @date:2018/9/14_14:19
     * @param:
     * @return:
     */
    private long timeToNowDateTimeLong(Time time, String nowDateStr) {
        DateTime dateTime = DateTime.parse(nowDateStr + time.toString(), jodaTimeFormatter);
        return dateTime.toDate().getTime();
    }

}
