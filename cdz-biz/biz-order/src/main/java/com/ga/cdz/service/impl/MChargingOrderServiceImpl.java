package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.dao.charging.ChargingOrderMapper;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.ChargingOrder;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.admin.ChargingOrderVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-12 16:07
 * @desc 充电订单Service
 */
@Service("mChargingOrderService")
public class MChargingOrderServiceImpl extends ServiceImpl<ChargingOrderMapper, ChargingOrder> implements IMChargingOrderService {
  @Resource
  ChargingShopMapper chargingShopMapper;
  @Resource
  AdminInfoMapper adminInfoMapper;
  /**
   * @author huanghaohao
   * @date 2018-09-12 18:53
   * @desc 查询获取订单列表
   * @param pageVo
   * @return
   */
  @Override
  public Page<ChargingOrderDTO> getChargingOrderListPag(PageVo<ChargingOrderVo> pageVo,String name) {
    Page<ChargingOrderDTO> page= new Page<>(pageVo.getIndex(),pageVo.getSize());
    ChargingOrderVo vo= pageVo.getData();
    List<ChargingOrderDTO> lists;
    List<AdminInfo> list=adminInfoMapper.selectList(new QueryWrapper<AdminInfo>().lambda().eq(AdminInfo::getAdminName,name));
    if(list.size()>0){
      lists =this.baseMapper.getChargingOrderListPage(page,vo,new ChargingShop());
    }else{
      ChargingShop chargingShop=this.chargingShopMapper.selectOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopName,name));

      lists=this.baseMapper.getChargingOrderListPage(page,vo,chargingShop);
    }


    page.setRecords(lists);
    return page;
  }
}
