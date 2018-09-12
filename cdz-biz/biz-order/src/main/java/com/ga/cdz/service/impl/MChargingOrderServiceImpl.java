package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingOrderMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.vo.admin.ChargingOrderVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-12 16:07
 * @desc 充电订单Service
 */
@Service("mChargingOrderService")
public class MChargingOrderServiceImpl extends ServiceImpl<ChargingOrderMapper, ChargingOrder> implements IMChargingOrderService {
  /**
   * @author huanghaohao
   * @date 2018-09-12 18:53
   * @desc 查询获取订单列表
   * @param pageVo
   * @return
   */
  @Override
  public Page<ChargingOrderDTO> getChargingOrderListPag(PageVo<ChargingOrderVo> pageVo) {
    Page<ChargingOrderDTO> page= new Page<>(pageVo.getIndex(),pageVo.getSize());
    ChargingOrderVo vo= pageVo.getData();
    System.err.println(pageVo);
    List<ChargingOrderDTO> list=this.baseMapper.getChargingOrderListPage(page,vo);
    page.setRecords(list);
    return page;
  }
}
