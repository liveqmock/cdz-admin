package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.dto.admin.UserMemberDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;
import com.ga.cdz.service.IMAdminUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * @author huanghaohao
 * @date 2018-09-09 21:46
 * @desc 会员列表的实现类
 */
@Service("mAdminUserService")
public class MAdminUserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IMAdminUserService {
  /**
   * @author huanghaohao
   * @desc 企业用户会员列表的 分组查询
   * @date 2018-09-09 21:47
   * @param pageVo 分页
   * @return
   */
  @Override
  public IPage<UserMemberDTO> getCompanyMemberListPage(PageVo<UserInfoVo> pageVo) {
    IPage<UserMemberDTO> page= new Page<>(pageVo.getIndex(),pageVo.getSize());
    return page.setRecords(this.baseMapper.getCompanyMemberListPage());
  }

  /**
   * @author huanghaohao
   * @desc 个人会员列表的 分组查询
   * @date 2018-09-09 21:47
   * @param pageVo 分页
   * @return
   */
  @Override
  public IPage<UserMemberDTO> getUserMemberListPage(PageVo<UserInfoVo> pageVo) {
    IPage<UserMemberDTO> page = new Page<>(pageVo.getIndex(),pageVo.getSize());
   return  page.setRecords(this.baseMapper.getUserMemberListPage());
  }

  /**
   * @author huanghaohaohao
   * @date 2018年9月9日 22点19分
   * @desc 更新会员状态
   * @param userInfoVo
   * @return
   */
  @Override
  public int updateMemUserSate(UserInfoVo userInfoVo) {
    UserInfo userInfo=new UserInfo();
    BeanUtils.copyProperties(userInfoVo,userInfo);
    return this.baseMapper.updateById(userInfo);
//    return true;
  }



}
