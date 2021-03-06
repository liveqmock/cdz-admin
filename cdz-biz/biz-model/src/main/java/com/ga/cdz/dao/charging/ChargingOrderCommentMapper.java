package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderCommentDTO;
import com.ga.cdz.domain.entity.ChargingOrderComment;
import com.ga.cdz.domain.entity.ChargingShop;
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
public interface ChargingOrderCommentMapper extends SuperMapper<ChargingOrderComment> {
    /**
     * @param page
     * @param
     * @return
     * @author huanghaohao
     * @date 2018-09-12 19:07
     * @desc 分页获取订单评价
     */
    List<ChargingOrderCommentDTO> getChargingCommentOrderListPage(@Param("shop") ChargingShop chargingShop, Page<ChargingOrderCommentDTO> page);
//  List<ChargingOrderCommentDTO> getChargingOrderListPage(Page<ChargingOrderCommentDTO> page , @Param("comment")ChargingOrderInitVo chargingOrderVo);
}
