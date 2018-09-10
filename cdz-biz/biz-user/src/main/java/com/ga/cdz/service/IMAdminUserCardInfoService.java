package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.UserMemberCardInfoDTO;
import com.ga.cdz.domain.entity.UserCardInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserMemberCardInfoVo;

import java.util.List;

/**
 * @author huanghaohao
 * @desc 2018-09-10 09:25
 * @desc UserCardInfoServiceInfo interface
 *
 */
public interface IMAdminUserCardInfoService extends IService<UserCardInfo> {
  /**
   * @author huanghaohao
   * @desc 更新用户会员卡状态通过Id
   * @param userMemberCardInfoVo
   * @return
   */
  int updateMemCardStatById(UserMemberCardInfoVo userMemberCardInfoVo);

  /**
   * @author huanghaohao
   * @date 2018年9月9日 23点25分
   * @param pageVo
   * @return
   */
  List<UserMemberCardInfoDTO> getMemberCardListPage(PageVo<UserMemberCardInfoVo> pageVo);

}
