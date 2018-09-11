package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * @author:luqi
 * @description: 用户接口具体实现类
 * @date:2018/9/4_11:02
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements IUserService {

    /**
     * 用户头像路径
     */
    @Value("${url.user_avatar}")
    protected String userAvatarFilePath;

    @Override
    public MyInfoDTO getMyInfoDTOById(Integer id) {
        MyInfoDTO myInfoDTO = baseMapper.getMyInfoDTOById(id);
        String userAvatar = userAvatarFilePath + myInfoDTO.getUserAvatar();
        myInfoDTO.setUserAvatar(userAvatar);
        if (ObjectUtils.isEmpty(myInfoDTO.getUserPrice())) {
            myInfoDTO.setUserPrice(new BigDecimal("0"));
        }
        if (ObjectUtils.isEmpty(myInfoDTO.getCoupon())) {
            myInfoDTO.setCoupon(0);
        }
        if (ObjectUtils.isEmpty(myInfoDTO.getCarbon())) {
            myInfoDTO.setCarbon(new BigDecimal(0));
        }
        return myInfoDTO;
    }

    @Override
    public boolean isUserFreeze(String tel) {
        UserInfo hasUserInfo = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getUserTel, tel));
        if (!ObjectUtils.isEmpty(hasUserInfo)) {
            if (hasUserInfo.getUserState().equals(UserInfo.UserState.FREEZE)) {
                //被冻结了
                return true;
            }
        }
        //没有冻结
        return false;
    }
}
