package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.SuperMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderCommentDTO;
import com.ga.cdz.domain.entity.ChargingOrderComment;
import com.ga.cdz.domain.vo.admin.ChargingOrderVo;
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
   * @author huanghaohao
   * @date 2018-09-12 19:07
   * @desc 分页获取订单评价
   * @param page
   * @param
   * @return
   */
  List<ChargingOrderCommentDTO> getChargingOrderListPage(Page<ChargingOrderCommentDTO> page );
//  List<ChargingOrderCommentDTO> getChargingOrderListPage(Page<ChargingOrderCommentDTO> page , @Param("comment")ChargingOrderVo chargingOrderVo);
}
