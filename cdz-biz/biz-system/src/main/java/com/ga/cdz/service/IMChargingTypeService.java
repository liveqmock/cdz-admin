package com.ga.cdz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.ChargingType;
import com.ga.cdz.domain.vo.base.ChargingTypeVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电柱充电方式服务接口
 * @date:2018/9/10 9:41
 */
public interface IMChargingTypeService extends IService<ChargingType> {
    /**
     * @author:wanzhongsu
     * @description: 获取充电桩充电方式列表
     * @date: 2018/9/10 9:42
     * @return: List
     */
    List<ChargingType> getChargingTypeList();

    /**
     * @author:wanzhongsu
     * @description: 逻辑删除充电柱充电方式
     * @date: 2018/9/10 9:43
     * @param: ChargingTypeVo
     * @return: 返回整数 小于等于0 不成功，大于0 成功
     */
    Integer removeChargingTypeById(ChargingTypeVo vo);

    /**
     * @author:wanzhongsu
     * @description: 保存充电柱充电方式
     * @date: 2018/9/10 9:43
     * @param: ChargingTypeVo
     * @return: 返回整数 小于等于0 不成功，大于0 成功
     */
    Integer saveChargingTypeObj(ChargingTypeVo vo);


}
