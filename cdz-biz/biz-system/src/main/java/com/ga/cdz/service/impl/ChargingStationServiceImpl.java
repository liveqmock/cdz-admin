package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.domain.bean.Paging;
import com.ga.cdz.domain.dto.api.ChargingStationPageDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.api.ChargingStationPageVo;
import com.ga.cdz.service.IChargingRedisService;
import com.ga.cdz.service.IChargingStationService;
import com.ga.cdz.util.MDistanceUtil;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:luqi
 * @description: 客户端 充电站接口实现类
 * @date:2018/9/13_15:51
 */
@Slf4j
@Service("chargingStationService")
public class ChargingStationServiceImpl extends ServiceImpl<ChargingStationMapper, ChargingStation> implements IChargingStationService {

    @Resource
    MUtil mUtil;

    @Resource
    MRedisUtil mRedisUtil;

    @Resource
    MDistanceUtil mDistanceUtil;

    @Resource
    IChargingRedisService chargingRedisService;


    @Override
    public List<ChargingStationPageDTO> getStationPage(ChargingStationPageVo vo) {
        /**检测缓存*/
        chargingRedisService.cacheChargingPageList();
        double voLat = vo.getLat();
        double voLng = vo.getLng();
        Integer cityCode = vo.getCityCode();
        DecimalFormat df = new DecimalFormat("#.0");
        /**获取站的列表*/
        List<ChargingStation> chargingStationList = mRedisUtil.getHashOfList(RedisConstant.TABLE_CHARGING_STATION);
        List<ChargingStation> listCity = chargingStationList.stream()
                .filter(chargingStation -> cityCode == chargingStation.getCity().intValue())
                .collect(Collectors.toList());
        /**列表转换,查询城市编码相同的数据*/
        List<ChargingStationPageDTO> page = listCity.stream().map(chargingStation -> {
            ChargingStationPageDTO chargingStationPageDTO = new ChargingStationPageDTO();
            chargingStationPageDTO.setStationId(chargingStation.getStationId());
            chargingStationPageDTO.setStationName(chargingStation.getStationName());
            chargingStationPageDTO.setLat(chargingStation.getLat());
            chargingStationPageDTO.setLng(chargingStation.getLng());
            double distance = mDistanceUtil.getDistance(voLng, voLat, chargingStation.getLng(), chargingStation.getLat());
            chargingStationPageDTO.setDistance(Double.parseDouble(df.format(distance)));
            /**计算距离**/
            return chargingStationPageDTO;
        }).collect(Collectors.toList());
        /**排序*/
        Collections.sort(page);
        /**分页*/
        Paging<ChargingStationPageDTO> mPage = new Paging<>(page, vo.getIndex(), vo.getSize());
        /**得到分页后的数据*/
        List<ChargingStationPageDTO> pageList = mPage.getList();
        /**补全站点的信息*/
        return pageList;
    }

}
