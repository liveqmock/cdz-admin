package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.admin.ChargingOrderSelectVo;;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2018-09-12
 */
public interface ChargingOrderMapper extends SuperMapper<ChargingOrder> {


    /**
     * @author huanghaohao
     * @date 2018-09-12 15:12
     * @desc 获取订单列表
     */
    List<ChargingOrderDTO> getChargingOrderListPage(Page<ChargingOrderDTO> page, @Param("order") ChargingOrderSelectVo chargingOrderSelectVo, @Param("shop") ChargingShop chargingShop);

}
