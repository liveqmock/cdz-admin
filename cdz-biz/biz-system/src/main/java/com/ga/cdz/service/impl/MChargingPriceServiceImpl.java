package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.dao.charging.ChargingPriceMapper;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingPriceDTO;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.domain.vo.admin.ChargingPriceAddVo;
import com.ga.cdz.domain.vo.admin.ChargingPriceSelectVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 计费标准实现类
 * @date:2018/9/11 10:33
 */
@Slf4j
@Service("mChargingPriceService")
public class MChargingPriceServiceImpl extends ServiceImpl<ChargingPriceMapper, ChargingPrice> implements IMChargingPriceService {
    @Resource
    AdminInfoMapper adminInfoMapper;
    @Resource
    ChargingShopMapper chargingShopMapper;
    /**
     * 充电站mapper
     */
    @Resource
    private ChargingStationMapper chargingStationMapper;

    @Override
    public Page<ChargingPriceDTO> getPageByType(PageVo<ChargingPriceSelectVo> vo,String name) {
        ChargingPriceSelectVo param = new ChargingPriceSelectVo();
        BeanUtils.copyProperties(vo.getData(), param);
        //获取分页信息
        Page<ChargingPriceDTO> page = new Page<ChargingPriceDTO>(vo.getIndex(), vo.getSize());
        //查询
        List<ChargingPriceDTO> lists=null;
        List<AdminInfo> list=adminInfoMapper.selectList(new QueryWrapper<AdminInfo>().lambda().eq(AdminInfo::getAdminName,name));
        if(list.size()>0){
           lists= baseMapper.getChargingPricePage(page, param,new ChargingShop());
        }else{
            ChargingShop chargingShop=chargingShopMapper.selectOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopName,name));

            lists=baseMapper.getChargingPricePage(page,param,chargingShop);
        }
        page.setRecords(lists);
        return page;
    }

    @Override
    @Transactional
    public boolean saveChargingPriceByKeys(ChargingPriceAddVo chargingPriceAddVo) {
        boolean check = checkPriceTime(chargingPriceAddVo);
        if (!check) {
            throw new BusinessException("请检查输入的时间段是否连续以及时间是否闭合");
        }
        //低谷时间段计费信息
        ChargingPrice low = new ChargingPrice();
        //平谷时间段计费信息
        ChargingPrice middle = new ChargingPrice();
        //高峰时间段计费信息
        ChargingPrice high = new ChargingPrice();
        //获取低谷时间段信息
        low.setStationId(chargingPriceAddVo.getStationId());
        low.setPriceName(chargingPriceAddVo.getPriceName());
        low.setPriceType(chargingPriceAddVo.getPriceType());
        low.setPriceIdx(ChargingPrice.PriceIdx.LOW);
        low.setPriceBeginDt(chargingPriceAddVo.getLowStart());
        low.setPriceEndDt(chargingPriceAddVo.getLowEnd());
        low.setChargingPrice(chargingPriceAddVo.getLowPrice());
        low.setPriceParking(chargingPriceAddVo.getLowParking());
        low.setServicePrice(chargingPriceAddVo.getLowService());
        //获取平谷时间段信息
        middle.setStationId(chargingPriceAddVo.getStationId());
        middle.setPriceName(chargingPriceAddVo.getPriceName());
        middle.setPriceType(chargingPriceAddVo.getPriceType());
        middle.setPriceIdx(ChargingPrice.PriceIdx.MIDDLE);
        middle.setPriceBeginDt(chargingPriceAddVo.getMiddleStart());
        middle.setPriceEndDt(chargingPriceAddVo.getMiddleEnd());
        middle.setChargingPrice(chargingPriceAddVo.getMiddlePrice());
        middle.setPriceParking(chargingPriceAddVo.getMiddleParking());
        middle.setServicePrice(chargingPriceAddVo.getMiddleService());
        //获取高峰时间段信息
        high.setStationId(chargingPriceAddVo.getStationId());
        high.setPriceName(chargingPriceAddVo.getPriceName());
        high.setPriceType(chargingPriceAddVo.getPriceType());
        high.setPriceIdx(ChargingPrice.PriceIdx.HIGH);
        high.setPriceBeginDt(chargingPriceAddVo.getHighStart());
        high.setPriceEndDt(chargingPriceAddVo.getHighEnd());
        high.setChargingPrice(chargingPriceAddVo.getHighPrice());
        high.setPriceParking(chargingPriceAddVo.getHighParking());
        high.setServicePrice(chargingPriceAddVo.getHighService());
        //判断充电站ID是否存在
        ChargingStation station = chargingStationMapper.selectById(chargingPriceAddVo.getStationId());
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
    public boolean updateChargingPriceByKeys(ChargingPriceAddVo chargingPriceAddVo) {
        boolean check = checkPriceTime(chargingPriceAddVo);
        if (!check) {
            throw new BusinessException("请检查输入的时间段是否连续以及时间是否闭合");
        }
        //低谷时间段计费
        ChargingPrice low = new ChargingPrice();
        //平谷时间段计费
        ChargingPrice middle = new ChargingPrice();
        //高峰时间段计费
        ChargingPrice high = new ChargingPrice();
        //低谷时间段计费信息获取
        low.setStationId(chargingPriceAddVo.getStationId());
        low.setPriceName(chargingPriceAddVo.getPriceName());
        low.setPriceType(chargingPriceAddVo.getPriceType());
        low.setPriceIdx(ChargingPrice.PriceIdx.LOW);
        low.setPriceBeginDt(chargingPriceAddVo.getLowStart());
        low.setPriceEndDt(chargingPriceAddVo.getLowEnd());
        low.setChargingPrice(chargingPriceAddVo.getLowPrice());
        low.setPriceParking(chargingPriceAddVo.getLowParking());
        low.setServicePrice(chargingPriceAddVo.getLowService());
        //平谷时间段计费信息获取
        middle.setStationId(chargingPriceAddVo.getStationId());
        middle.setPriceName(chargingPriceAddVo.getPriceName());
        middle.setPriceType(chargingPriceAddVo.getPriceType());
        middle.setPriceIdx(ChargingPrice.PriceIdx.MIDDLE);
        middle.setPriceBeginDt(chargingPriceAddVo.getMiddleStart());
        middle.setPriceEndDt(chargingPriceAddVo.getMiddleEnd());
        middle.setChargingPrice(chargingPriceAddVo.getMiddlePrice());
        middle.setPriceParking(chargingPriceAddVo.getMiddleParking());
        middle.setServicePrice(chargingPriceAddVo.getMiddleService());
        //高峰时间段计费信息获取
        high.setStationId(chargingPriceAddVo.getStationId());
        high.setPriceName(chargingPriceAddVo.getPriceName());
        high.setPriceType(chargingPriceAddVo.getPriceType());
        high.setPriceIdx(ChargingPrice.PriceIdx.HIGH);
        high.setPriceBeginDt(chargingPriceAddVo.getHighStart());
        high.setPriceEndDt(chargingPriceAddVo.getHighEnd());
        high.setChargingPrice(chargingPriceAddVo.getHighPrice());
        high.setPriceParking(chargingPriceAddVo.getHighParking());
        high.setServicePrice(chargingPriceAddVo.getHighService());
        //充电站id是否存在
        ChargingStation station = chargingStationMapper.selectById(chargingPriceAddVo.getStationId());
        if (ObjectUtils.isEmpty(station)) {
            throw new BusinessException("充电站ID不存在");
        }
        //持久化到数据库
        try {
            //持久化低谷
            QueryWrapper<ChargingPrice> wrapper = new QueryWrapper();
            wrapper.lambda().eq(ChargingPrice::getStationId, low.getStationId())
                    .eq(ChargingPrice::getPriceIdx, low.getPriceIdx());
            low.setStationId(null).setPriceIdx(null);
            update(low, wrapper);
            //持久化平谷
            QueryWrapper<ChargingPrice> wrapper1 = new QueryWrapper();
            wrapper1.lambda().eq(ChargingPrice::getStationId, middle.getStationId())
                    .eq(ChargingPrice::getPriceIdx, middle.getPriceIdx());
            middle.setStationId(null).setPriceIdx(null);
            update(middle, wrapper1);
            //持久化高峰
            QueryWrapper<ChargingPrice> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().eq(ChargingPrice::getStationId, high.getStationId())
                    .eq(ChargingPrice::getPriceIdx, high.getPriceIdx());
            high.setStationId(null).setPriceIdx(null);
            update(high, wrapper2);
        } catch (Exception e) {
            throw new BusinessException("修改失败");
        }
        return true;
    }

    /**
     * @author:wanzhongsu
     * @description: 验证输入充电类型时间是否连续以及封闭
     * @date: 2018/9/14 14:37
     * @param: ChargingPriceAddVo
     * @return: 是否连续以及封闭
     */
    private boolean checkPriceTime(ChargingPriceAddVo vo) {
        long lowStart = vo.getLowStart().getTime();
        long lowEnd = vo.getLowEnd().getTime();
        long middleStart = vo.getMiddleStart().getTime();
        long middleEnd = vo.getMiddleEnd().getTime();
        long highStart = vo.getHighStart().getTime();
        long highEnd = vo.getHighEnd().getTime();
        long ml = middleStart - lowEnd;
        long hm = highStart - middleEnd;
        long lh = highEnd - lowStart;
        boolean a = (ml >= 0 && ml <= 1000);
        boolean b = (hm >= 0 && hm <= 1000);
        boolean c = (lh >= -1000 && lh <= 0) || (lh >= 86399000 && lh <= 86400000);
        if (a && b && c) {
            return true;
        }
        return false;
    }
}
