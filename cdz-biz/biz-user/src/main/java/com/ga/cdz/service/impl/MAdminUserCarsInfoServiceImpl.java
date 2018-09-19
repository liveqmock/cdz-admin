package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserCarsInfoMapper;
import com.ga.cdz.domain.dto.admin.UserMemberCarsInfoDTO;
import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.admin.UserMemberCarsInfoVo;
import com.ga.cdz.service.IMAdminUserCarsInfoService;
import org.springframework.stereotype.Service;

/**
 * @author huanghaohao
 * @date 2018-09-10 10:03
 * @desc UserCarsService 实现类 会员车辆 实现类
 */
@Service("mAdminUserCarsInfoService")
public class MAdminUserCarsInfoServiceImpl extends ServiceImpl<UserCarsInfoMapper, UserCarsInfo> implements IMAdminUserCarsInfoService {
  /**
   * @author huanghaohao
   * @date 2018-09-10 11:21
   * @desc 查询汽车列表分页查询
   * @param pageVo
   * @return
   */
  public IPage<UserMemberCarsInfoDTO> getUserCarsListPage(PageVo<UserMemberCarsInfoVo>pageVo){
    Page<UserMemberCarsInfoDTO> page=new Page<>(pageVo.getIndex(),pageVo.getSize());
    return page.setRecords(this.baseMapper.getUserCarListPage(page));
  }
}
