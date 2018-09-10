package com.ga.cdz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingStationType;
import com.ga.cdz.domain.vo.base.ChargingStationTypeVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站运营类型服务接口
 * @date:2018/9/10 9:47
 */
public interface IMChargingStationTypeService extends IService<ChargingStationType> {
    /**
     * @author:wanzhongsu
     * @description: 获取充电站运营类型列表
     * @date: 2018/9/10 9:48
     * @param:
     * @return:
     */
    List<ChargingStationType> getCSTList();

    /**
     * @author:wanzhongsu
     * @description: 逻辑删除充电站运营类型
     * @date: 2018/9/10 9:48
     * @param:
     * @return:
     */
    Integer removeCSTById(ChargingStationTypeVo vo);

    /**
     * @author:wanzhongsu
     * @description: 保存充电站运营类型
     * @date: 2018/9/10 9:53
     * @param:
     * @return:
     */
    Integer saveCST(ChargingStationTypeVo vo);
}
