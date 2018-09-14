package com.ga.cdz.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingPriceDTO;
import com.ga.cdz.domain.entity.ChargingPrice;
import com.ga.cdz.domain.vo.admin.ChargingPriceAddVo;
import com.ga.cdz.domain.vo.base.PageVo;

/**
 * @author:wanzhongsu
 * @description: 计费标准服务接口
 * @date:2018/9/11 10:32
 */
public interface IMChargingPriceService extends IService<ChargingPrice> {
    /**
     * @author:wanzhongsu
     * @description: 根据价格类型分页获取计费管理相关信息
     * @date: 2018/9/11 17:03
     * @param: myPriceType 1 专场 2 非专场
     * @return: 分页数据
     */
    Page<ChargingPriceDTO> getPageByType(PageVo<ChargingPriceAddVo> vo, Integer myPriceType);

    /**
     * @author:wanzhongsu
     * @description: 保存添加计费
     * @date: 2018/9/11 17:05
     * @param: ChargingPriceAddVo
     * @return: 是否成功
     */
    boolean saveChargingPriceByKeys(ChargingPriceAddVo vo);

    /**
     * @author:wanzhongsu
     * @description: 编辑修改计费信息
     * @date: 2018/9/11 17:06
     * @param: ChargingPriceAddVo
     * @return: 是否成功
     */
    boolean updateChargingPriceByKeys(ChargingPriceAddVo vo);


}
