package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserCardInfoMapper;
import com.ga.cdz.domain.dto.admin.UserMemberCardInfoDTO;
import com.ga.cdz.domain.entity.UserCardInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.admin.UserMemberCardInfoVo;
import com.ga.cdz.service.IMAdminUserCardInfoService;

import org.springframework.stereotype.Service;

/**
 * @author huanghaohao
 * @date 2018-09-10 09:36
 * @desc userCardService 实现类 会员卡实现类
 */
@Service("mAdminUserCardInfoService")
public class MAdminUserCardInfoServiceImpl extends ServiceImpl<UserCardInfoMapper, UserCardInfo> implements IMAdminUserCardInfoService {
    /**
     * @param userMemberCardInfoVo
     * @return
     * @author huanghaohao
     * @desc 更新用户会员卡状态 by Id
     * @date 2018-09-10 10:32
     */
    @Override
    public int updateMemCardStatById(UserMemberCardInfoVo userMemberCardInfoVo) {
        UserCardInfo userCardInfo = new UserCardInfo();
        userCardInfo.setCardId(userMemberCardInfoVo.getCardId());
        userCardInfo.setCardState(userMemberCardInfoVo.getCardState());
        return this.baseMapper.updateById(userCardInfo);
    }

    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018年9月9日 23点21分
     * @desc 分页查询会员ka列表
     */
    public Page<UserMemberCardInfoDTO> getMemberCardListPage(PageVo<UserMemberCardInfoVo> pageVo) {
        Page<UserMemberCardInfoDTO> page = new Page<>(pageVo.getIndex(), pageVo.getSize());
        page.setRecords(this.baseMapper.getMemberCardInfoListByPage(page, pageVo.getData()));
        return page;

    }
}
