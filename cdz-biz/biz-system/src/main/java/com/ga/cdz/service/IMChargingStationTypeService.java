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
     * @return: List
     */
    List<ChargingStationType> getCSTList();

    /**
     * @author:wanzhongsu
     * @description: 逻辑删除充电站运营类型
     * @date: 2018/9/10 9:48
     * @param: ChargingStationTypeVo
     * @return: 返回整数 小于等于0 不成功，大于0 成功
     */
    Integer removeCSTById(ChargingStationTypeVo vo);

    /**
     * @author:wanzhongsu
     * @description: 保存充电站运营类型
     * @date: 2018/9/10 9:53
     * @param: ChargingStationTypeVo
     * @return: 返回整数 小于等于0 不成功，大于0 成功
     */
    Integer saveCST(ChargingStationTypeVo vo);
}
