package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.admin.ChargingShopSelectVo;
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
     * @param: PageVo
     * @return: List
     */
    IPage<ChargingShopDTO> getChargingShopPage(PageVo<ChargingShopSelectVo> vo);

    /**
     * @author:wanzhongsu
     * @description: 根据名字模糊查询充电桩列表
     * @date: 2018/9/14 13:37
     * @param: ChargingShopVo
     * @return: List
     */
    List<ChargingShop> getListByName(ChargingShopVo vo);

    /**
     * @author:wanzhongsu
     * @description: 修改商户
     * @date: 2018/9/10 14:42
     * @param: ChargingShopVo
     * @return: 是否成功
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

    /**
     * @author  huanghaohao
     * @date 2018年9月19日 14点01分
     * @desc 商户登陆
     * @param chargingShopVo
     * @return
     */
    ChargingShopDTO login(ChargingShopVo chargingShopVo);
}
