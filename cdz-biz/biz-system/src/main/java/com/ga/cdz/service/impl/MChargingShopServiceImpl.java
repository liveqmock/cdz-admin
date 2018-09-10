package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
        Page<ChargingShop> page = new Page<>(vo.getIndex(), vo.getSize());
        page.setAsc("shop_id");
        QueryWrapper wrapper = new QueryWrapper<ChargingShop>(chargingShop);
        IPage<ChargingShop> iPage = page(page, wrapper);
        return iPage;
    }

    @Override
    public boolean updateById(ChargingShopVo vo) {
        return false;
    }

    @Override
    public boolean deleteById(ChargingShopVo vo) {
        return false;
    }

    @Override
    public boolean saveChargingShop(ChargingShopVo vo) {
        return false;
    }
}
