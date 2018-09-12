package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingOrderCommentMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderCommentDTO;
import com.ga.cdz.domain.entity.ChargingOrderComment;
import com.ga.cdz.domain.vo.admin.ChargingOrderCommentVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingOrderCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  huanghaohao
 * @date 2018-09-12 18:58
 * @desc 订单评价 Impl
 */
@Service("mChargingOrderCommentService")
public class MChargingOrderCommentServiceImpl extends ServiceImpl<ChargingOrderCommentMapper, ChargingOrderComment> implements IMChargingOrderCommentService {
  /**
   * @author huanghaohao
   * @date 2018-09-12
   *
   * @param pageVo
   * @return
   */
  @Override
  public Page<ChargingOrderCommentDTO> getChargingOrderComentListPage(PageVo<ChargingOrderCommentVo> pageVo) {
    Page<ChargingOrderCommentDTO> page=new Page<>(pageVo.getIndex(),pageVo.getSize());
    List<ChargingOrderCommentDTO> list =this.baseMapper.getChargingOrderListPage(page);
    page.setRecords(list);
    return page;
  }
}
