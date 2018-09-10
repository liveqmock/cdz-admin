package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ga.cdz.domain.dto.admin.UserMemberCardInfoDTO;
import com.ga.cdz.domain.entity.UserCardInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserMemberCardInfoVo;

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
   * @return
   */
  List<UserMemberCardInfoDTO> getMemberCardInfoListByPage();
}
