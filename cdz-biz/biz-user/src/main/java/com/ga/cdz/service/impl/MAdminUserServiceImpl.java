package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.dto.admin.UserMemberDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;
import com.ga.cdz.service.IMAdminUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author huanghaohao
 * @date 2018-09-09 21:46
 * @desc 会员列表的实现类
 */
@Service("mAdminUserService")
public class MAdminUserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IMAdminUserService {
    @Resource
    DistrictMapper districtMapper;
    /**
     * @param pageVo 分页
     * @return
     * @author huanghaohao
     * @desc 企业用户会员列表的 分组查询
     * @date 2018-09-09 21:47
     */
    @Override
    public IPage<UserMemberDTO> getCompanyMemberListPage(PageVo<UserInfoVo> pageVo) {
        Page<UserMemberDTO> page = new Page<>(pageVo.getIndex(), pageVo.getSize());
        List<UserMemberDTO> list = this.baseMapper.getCompanyMemberListPage(page, pageVo.getData());
        list.forEach(item -> {
            item.setProvinceName(districtMapper.selectById(item.getProvince()).getDistrictName());
            item.setCityName(districtMapper.selectById(item.getCity()).getDistrictName());
            item.setCountyName(districtMapper.selectById(item.getCountry()).getDistrictName());
        });
        page.setRecords(list);
        return page;
    }

    /**
     * @param pageVo 分页
     * @return
     * @author huanghaohao
     * @desc 个人会员列表的 分组查询
     * @date 2018-09-09 21:47
     */
    @Override
    public IPage<UserMemberDTO> getUserMemberListPage(PageVo<UserInfoVo> pageVo) {
        Page<UserMemberDTO> page = new Page<>(pageVo.getIndex(), pageVo.getSize());
        List<UserMemberDTO> userMemberDTOS = this.baseMapper.getUserMemberListPage(page, pageVo.getData());
        userMemberDTOS.forEach(item -> {
            item.setProvinceName(districtMapper.selectById(item.getProvince()).getDistrictName());
            item.setCityName(districtMapper.selectById(item.getCity()).getDistrictName());
            item.setCountyName(districtMapper.selectById(item.getCountry()).getDistrictName());
        });
        page.setRecords(userMemberDTOS);
        return page;
    }

    /**
     * @param userInfoVo
     * @return
     * @author huanghaohaohao
     * @date 2018年9月9日 22点19分
     * @desc 更新会员状态
     */
    @Override
    public int updateMemUserSate(UserInfoVo userInfoVo) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVo, userInfo);
        return this.baseMapper.updateById(userInfo);
//    return true;
    }


}
