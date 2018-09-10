package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.PageVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 商户管理服务接口
 * @date:2018/9/10 12:41
 */
public interface IMChargingShopService extends IService<ChargingShop> {
    IPage<ChargingShop> getChargingShopList(PageVo<ChargingShopVo> vo);

    boolean updateById(ChargingShopVo vo);

    boolean deleteById(ChargingShopVo vo);

    boolean saveChargingShop(ChargingShopVo vo);
}
