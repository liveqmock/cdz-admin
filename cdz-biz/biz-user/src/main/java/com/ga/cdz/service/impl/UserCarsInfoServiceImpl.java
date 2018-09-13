package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserCarsInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserCarsInfoVo;
import com.ga.cdz.service.IUserCarsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author:luqi
 * @description: 用户车辆管理接口实现类
 * @date:2018/9/13_10:33
 */
@Slf4j
@Service("userCarsInfoService")
public class UserCarsInfoServiceImpl extends ServiceImpl<UserCarsInfoMapper, UserCarsInfo> implements IUserCarsInfoService {


    @Override
    public IPage<UserCarsInfo> getUserCarsInfoPage(PageVo<UserCarsInfoVo> pageVo) {
        //查询未删除的车
        Page<UserCarsInfo> page = new Page<>(pageVo.getIndex(), pageVo.getSize());
        page.setDesc("update_dt");
        IPage<UserCarsInfo> rsPage = baseMapper.selectPage(page, new QueryWrapper<UserCarsInfo>().lambda()
                .eq(UserCarsInfo::getCarState, UserCarsInfo.CarState.NORMAL));
        return rsPage;
    }

    @Override
    public void saveUserCarsInfo(UserCarsInfoVo userCarsInfoVo) {
        //查询车牌号是否存在
        Integer userId = userCarsInfoVo.getUserId();
        String carNo = userCarsInfoVo.getCarNo();
        UserCarsInfo dbUserCarsInfo = baseMapper.selectOne(new QueryWrapper<UserCarsInfo>().lambda().eq(UserCarsInfo::getCarNo, carNo));
        UserCarsInfo updateEntity = new UserCarsInfo();
        BeanUtils.copyProperties(userCarsInfoVo, updateEntity);
        if (ObjectUtils.isEmpty(dbUserCarsInfo)) {
            //没有人使用该车牌号
            int row = baseMapper.insert(updateEntity);
            if (row == 0) {
                throw new BusinessException("操作失败，稍后重试");
            }
        } else if (!ObjectUtils.isEmpty(dbUserCarsInfo) && dbUserCarsInfo.getUserId().equals(userId)) {
            //判断状态
            if (dbUserCarsInfo.getCarState().equals(UserCarsInfo.CarState.REMOVE)) {
                //之前有车 处于删除状态 重置状态
                updateEntity.setCarState(UserCarsInfo.CarState.NORMAL);
                int row = baseMapper.update(updateEntity, new QueryWrapper<UserCarsInfo>()
                        .lambda().eq(UserCarsInfo::getUserId, userId).eq(UserCarsInfo::getCarNo, carNo));
                if (row == 0) {
                    throw new BusinessException("操作失败，稍后重试");
                }
            } else {
                throw new BusinessException("车辆已添加");
            }
        } else if (!ObjectUtils.isEmpty(dbUserCarsInfo) && !dbUserCarsInfo.getUserId().equals(userId)) {
            throw new BusinessException("车牌号已被使用");
        }
    }

    @Override
    public void removeUserCarsInfoByCarNo(UserCarsInfoVo userCarsInfoVo) {
        /**判断车和人是否对的上*/
        Integer userId = userCarsInfoVo.getUserId();
        String carNo = userCarsInfoVo.getCarNo();
        UserCarsInfo dbUserCarsInfo = baseMapper.selectOne(new QueryWrapper<UserCarsInfo>().lambda()
                .eq(UserCarsInfo::getUserId, userId).eq(UserCarsInfo::getCarNo, carNo));
        if (ObjectUtils.isEmpty(dbUserCarsInfo)) {
            throw new BusinessException("数据不存在");
        }
        if (dbUserCarsInfo.getCarState().equals(UserCarsInfo.CarState.REMOVE)) {
            throw new BusinessException("车的状态，不能删除");
        }
        UserCarsInfo updateEntity = new UserCarsInfo().setCarState(UserCarsInfo.CarState.REMOVE);
        /**删除车辆信息,逻辑删除*/
        int row = baseMapper.update(updateEntity, new QueryWrapper<UserCarsInfo>().lambda()
                .eq(UserCarsInfo::getUserId, userId).eq(UserCarsInfo::getCarNo, carNo)
                .eq(UserCarsInfo::getCarState, UserCarsInfo.CarState.NORMAL));
        if (row == 0) {
            throw new BusinessException("操作失败，稍后重试");
        }
    }

}
