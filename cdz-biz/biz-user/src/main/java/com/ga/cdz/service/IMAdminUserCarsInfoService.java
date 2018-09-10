package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.UserMemberCarsInfoDTO;
import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserMemberCarsInfoVo;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-10 09:58
 * @desc UserCars SERVICE INTERFACE
 *
 */
public interface IMAdminUserCarsInfoService extends IService<UserCarsInfo> {
  /**
   * @author huanghaohao
   * @date 2018-09-10 11:02
   * @desc 汽车列表分页查询
   * @param pageVo
   * @return
   */
    List<UserMemberCarsInfoDTO> getUserCarsListPage(PageVo<UserMemberCarsInfoVo> pageVo);
}