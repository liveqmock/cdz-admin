package com.ga.cdz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;

/**
 * @author:wanzhongsu
 * @description: 充电站列表服务接口
 * @date:2018/9/10 15:08
 */
public interface IMChargingStationService extends IService<ChargingStation> {
    /**
     * @author:wanzhongsu
     * @description: 分页获取充电站信息
     * @date: 2018/9/10 16:29
     * @param:
     * @return:
     */
    IPage<ChargingStation> getStationPage(PageVo<ChargingStationVo> vo);

    /**
     * @author:wanzhongsu
     * @description: 修改充电站信息
     * @date: 2018/9/10 16:29
     * @param:
     * @return:
     */
    boolean updateStationById(ChargingStationVo vo);

    /**
     * @author:wanzhongsu
     * @description: 删除充电站信息
     * @date: 2018/9/10 16:29
     * @param:
     * @return:
     */
    boolean deleteStationById(ChargingStationVo vo);

    /**
     * @author:wanzhongsu
     * @description: 保存充电站信息
     * @date: 2018/9/10 16:30
     * @param:
     * @return:
     */
    boolean saveStation(ChargingStationVo vo);
}
