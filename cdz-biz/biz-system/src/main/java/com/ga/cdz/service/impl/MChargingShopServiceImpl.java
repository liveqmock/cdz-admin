package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 商户管理服务实现类
 * @date: 2018/9/10 12:41
 */
@Service("mChargingShopService")
public class MChargingShopServiceImpl extends ServiceImpl<ChargingShopMapper, ChargingShop> implements IMChargingShopService {

    @Override
    public IPage<ChargingShop> getChargingShopList(PageVo<ChargingShopVo> vo) {
        ChargingShop chargingShop = new ChargingShop();
        if (!ObjectUtils.isEmpty(vo.getData())) {
            BeanUtils.copyProperties(vo.getData(), chargingShop);
        }
        //分页获取状态正常的商户
        Page<ChargingShop> page = new Page<>(vo.getIndex(), vo.getSize());
        page.setAsc("shop_id");
        chargingShop.setShopState(ChargingShop.ShopState.NORMAL);
        QueryWrapper wrapper = new QueryWrapper<ChargingShop>(chargingShop);
        IPage<ChargingShop> iPage = page(page, wrapper);
        return iPage;
    }

    @Override
    @Transactional
    public boolean updateShopById(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        BeanUtils.copyProperties(vo, chargingShop);
        boolean result = updateById(chargingShop);
        return result;
    }

    @Override
    @Transactional
    public boolean deleteShopById(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        chargingShop = getById(vo.getShopId());
        //判断该商户是否存在
        if (ObjectUtils.isEmpty(chargingShop)) {
            throw new BusinessException("商户id在数据库中不存在");
        }
        //删除
        chargingShop.setShopState(ChargingShop.ShopState.DELETE);
        boolean result = updateById(chargingShop);
        return result;
    }

    @Override
    @Transactional
    public boolean saveChargingShop(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        BeanUtils.copyProperties(vo, chargingShop);
        //判断是否已存在数据库中且状态为正常
        ChargingShop hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopState, ChargingShop.ShopState.NORMAL).eq(ChargingShop::getShopName, vo.getShopName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("商户名在数据库中已存在");
        }
        //判断是否存在数据库中，只是状态删除了
        hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopName, vo.getShopName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setShopState(ChargingShop.ShopState.NORMAL);
            updateById(hasName);
            return true;
        }
        //保存商户
        boolean result = save(chargingShop);
        return result;
    }
}
