package com.ga.cdz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;

import java.util.List;

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
    IPage<ChargingStationDTO> getStationPage(PageVo<ChargingStationVo> vo);

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
     * @param: ChargingStation的vo对象
     * @return: 返回保存后的StationId值，否则-1
     */
    int saveStation(ChargingStationVo vo);

    /**
     * @author:luqi
     * @description: 获取所有的表信息到缓存
     * @date:2018/9/11_19:25
     * @param:
     * @return:
     */
    void getRedisListAll();
}
