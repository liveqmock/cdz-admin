package com.ga.cdz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.UserMemberDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserInfoVo;

/**
 * @author huanghaohao
 * @date 2018-09-09 21:36
 * @desc 会员管理
 */
public interface IMAdminUserService extends IService<UserInfo> {
    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-09 21:39
     * @desc 分页查询个人会员列表
     */
    IPage<UserMemberDTO> getUserMemberListPage(PageVo<UserInfoVo> pageVo);

    /**
     * @param pageVo
     * @return
     * @author huanghaohao
     * @date 2018-09-09 22:34
     * @desc 分页查询企业会员列表
     */
    IPage<UserMemberDTO> getCompanyMemberListPage(PageVo<UserInfoVo> pageVo);

    /**
     * @param userInfoVo
     * @return
     * @author huanghaohao
     * @date 2018年9月9日 22点16分
     * @desc 更新会员状态
     */
    int updateMemUserSate(UserInfoVo userInfoVo);


}
