package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingOrderCommentDTO;
import com.ga.cdz.domain.entity.ChargingOrderComment;
import com.ga.cdz.domain.vo.admin.ChargingOrderCommentVo;
import com.ga.cdz.domain.vo.base.PageVo;

/**
 * @author huanghaohao
 * @date 2018-09-12 18:54
 * @desc 订单评价 service
 */
public interface IMChargingOrderCommentService extends IService<ChargingOrderComment> {
    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-12 19:01
     * @desc 分页获取订单评价
     */
    Page<ChargingOrderCommentDTO> getChargingOrderCommentListPage(PageVo<ChargingOrderCommentVo> pageVo, String name);
}
