package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ga.cdz.domain.dto.admin.UserMemberCardInfoDTO;
import com.ga.cdz.domain.dto.admin.UserMemberDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserMemberCardInfoVo;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018年9月7日 18点03分
 * @desc 会员信息的查询Mapper
 *
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
  /**
   * @author huanghaohao
   * @desc 分页查询 会员用户信息
   * @date 2018年9月7日 18点05分
   * @return
   */
    List<UserMemberDTO> getUserMemberListPage( );

  /**
   * @author huanghaohao
   * @date 2018年9月9日 22点31分
   * @desc 分页查询企业会员列表
   * @param
   * @return
   */
  List<UserMemberDTO> getCompanyMemberListPage();


}
