package com.ga.cdz.dao.charging;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ga.cdz.domain.dto.admin.UserMemberDTO;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.entity.UserInfo;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018年9月7日 18点03分
 * @desc 会员信息的查询Mapper
 *
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * @author:luqi
     * @description: 获取用户最大的userCode
     * @date:2018/9/11_14:17
     * @param:
     * @return: userCode
     */
    String getMaxUserCode();

    /**
     * @author:luqi
     * @description: 根据用户id获取我的界面信息
     * @date:2018/9/11_15:36
     * @param:
     * @return:
     */
    MyInfoDTO getMyInfoDTOById(Integer id);

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
