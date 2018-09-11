package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.dto.admin.UserMemberCardInfoDTO;
import com.ga.cdz.domain.entity.UserCardInfo;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-10 09:18
 * @desc userCardInfoMapper
 */
public interface UserCardInfoMapper extends BaseMapper<UserCardInfo> {
  /**
   * @author huanghaohao
   * @date 2018-09-09 23:07
   * @desc 查询会员卡信息
   * @param Page
   * @return
   */
  List<UserMemberCardInfoDTO> getMemberCardInfoListByPage(Page<UserMemberCardInfoDTO> page);

    /**
     * @author:luqi
     * @description: 获取最新cardCode
     * @date:2018/9/10_18:38
     * @param:
     * @return: string
     */
    String getCardCodeLast();
}
