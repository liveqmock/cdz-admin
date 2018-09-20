package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.dto.admin.UserMemberCarsInfoDTO;
import com.ga.cdz.domain.entity.UserCarsInfo;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-10 09:54
 * @desc userCars Mapper
 */
public interface UserCarsInfoMapper extends BaseMapper<UserCarsInfo> {
    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-10 11:11
     * @desc 获取用户 car分页列表
     */
    List<UserMemberCarsInfoDTO> getUserCarListPage(Page<UserMemberCarsInfoDTO> page);
}

