package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
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
    /**
     * @author:wanzhongsu
     * @description: 获取商户列表
     * @date: 2018/9/10 14:42
     * @param:
     * @return:
     */
    IPage<ChargingShopDTO> getChargingShopPage(PageVo<ChargingShopVo> vo);

    List<ChargingShop> getListByName(ChargingShopVo vo);
    /**
     * @author:wanzhongsu
     * @description: 修改商户
     * @date: 2018/9/10 14:42
     * @param:
     * @return:
     */
    boolean updateShopById(ChargingShopVo vo);

    /**
     * @author:wanzhongsu
     * @description: 删除商户
     * @date: 2018/9/10 14:42
     * @param:
     * @return:
     */
    boolean deleteShopById(ChargingShopVo vo);

    /**
     * @author:wanzhongsu
     * @description: 保存商户
     * @date: 2018/9/10 14:43
     * @param:
     * @return:
     */
    boolean saveChargingShop(ChargingShopVo vo);
}
