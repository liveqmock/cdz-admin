package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wanzhongsu
 * @description: 商户管理服务实现类
 * @date: 2018/9/10 12:41
 */
@Service("mChargingShopService")
public class MChargingShopServiceImpl extends ServiceImpl<ChargingShopMapper, ChargingShop> implements IMChargingShopService {

    @Override
    public IPage<ChargingShopDTO> getChargingShopPage(PageVo<ChargingShopVo> vo) {
        //分页获取商户
        Page<ChargingShopDTO> page = new Page<>(vo.getIndex(), vo.getSize());
        List<ChargingShopDTO> list = baseMapper.getShopList();
        page.setRecords(list);
        return page;
    }

    @Override
    @Transactional
    public boolean updateShopById(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        BeanUtils.copyProperties(vo, chargingShop);
        chargingShop.setShopState(ChargingShop.ShopState.NORMAL);
        //判断登录名是否已存在数据库中且状态为正常
        ChargingShop hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopState, ChargingShop.ShopState.NORMAL).eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("登录名在数据库中已存在");
        }
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
        //判断登录名是否已存在数据库中且状态为正常
        ChargingShop hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopState, ChargingShop.ShopState.NORMAL).eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("登录名在数据库中已存在");
        }
        //判断登录名是否存在数据库中，只是状态删除了
        hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setShopState(ChargingShop.ShopState.NORMAL);
            updateById(hasName);
            return true;
        }
        //保存商户，初始化状态为正常
        chargingShop.setShopState(ChargingShop.ShopState.NORMAL);
        boolean result = save(chargingShop);
        return result;
    }
}
