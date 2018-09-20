package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.dao.center.AdminRoleMapper;
import com.ga.cdz.dao.charging.ChargingOrderCommentMapper;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.dto.admin.ChargingOrderCommentDTO;
import com.ga.cdz.domain.entity.*;
import com.ga.cdz.domain.vo.admin.ChargingOrderCommentVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingOrderCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-12 18:58
 * @desc 订单评价 Impl
 */
@Service("mChargingOrderCommentService")
public class MChargingOrderCommentServiceImpl extends ServiceImpl<ChargingOrderCommentMapper, ChargingOrderComment> implements IMChargingOrderCommentService {
    @Resource
    AdminInfoMapper adminInfoMapper;
    @Resource
    ChargingShopMapper chargingShopMapper;

    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-12
     * @desc 返回订单评价
     */
    @Override
    public Page<ChargingOrderCommentDTO> getChargingOrderCommentListPage(PageVo<ChargingOrderCommentVo> pageVo, String name) {
        Page<ChargingOrderCommentDTO> page = new Page<>(pageVo.getIndex(), pageVo.getSize());
        List<AdminInfo> list = adminInfoMapper.selectList(new QueryWrapper<AdminInfo>().lambda().eq(AdminInfo::getAdminAccount, name));
        List<ChargingOrderCommentDTO> lists = null;
        if (list.size() > 0) {
            lists = this.baseMapper.getChargingCommentOrderListPage(new ChargingShop(), page);
        } else {
            ChargingShop chargingShop = chargingShopMapper.selectOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, name));
            lists = this.baseMapper.getChargingCommentOrderListPage(chargingShop, page);
        }
        page.setRecords(lists);
        return page;
    }
}
