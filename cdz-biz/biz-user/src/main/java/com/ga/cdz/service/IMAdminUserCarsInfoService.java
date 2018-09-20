package com.ga.cdz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.UserMemberCarsInfoDTO;
import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.admin.UserMemberCarsInfoVo;

/**
 * @author huanghaohao
 * @date 2018-09-10 09:58
 * @desc UserCars SERVICE INTERFACE
 */
public interface IMAdminUserCarsInfoService extends IService<UserCarsInfo> {
    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-10 11:02
     * @desc 汽车列表分页查询
     */
    IPage<UserMemberCarsInfoDTO> getUserCarsListPage(PageVo<UserMemberCarsInfoVo> pageVo);
}