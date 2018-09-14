package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserSmsMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.UserSms;
import com.ga.cdz.domain.vo.admin.UserSmsVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMUserSmsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 消息服务实现类
 * @date:2018/9/12 14:30
 */
@Service("mUserSmsService")
public class MUserSmsServiceImpl extends ServiceImpl<UserSmsMapper, UserSms> implements IMUserSmsService {


    @Override
    public IPage<UserSms> getSmsPage(PageVo<UserSmsVo> vo) {
        //获取分页信息
        Page<UserSms> page = new Page<>(vo.getIndex(), vo.getSize());
        //设置查询条件
        UserSms userSms = new UserSms();
        BeanUtils.copyProperties(vo.getData(), userSms);
        LambdaQueryWrapper<UserSms> wrapper = new QueryWrapper<UserSms>().lambda().eq(UserSms::getSmsType, userSms.getSmsType()).like(UserSms::getSmsTitle, userSms.getSmsTitle());
        //根据条件获取查询后的分页对象
        IPage<UserSms> userSmsIPage = page(page, wrapper);
        return userSmsIPage;
    }

    @Override
    @Transactional
    public int saveSms(UserSmsVo vo) {
        //获取消息信息进行验证
        String title = vo.getSmsTitle();
        String url = vo.getSmsUrl();
        UserSms.SmsType smsType = vo.getSmsType();
        if (ObjectUtils.isEmpty(title)) {
            throw new BusinessException("消息标题不能为空");
        } else if (ObjectUtils.isEmpty(url)) {
            throw new BusinessException("消息内容不能为空");
        } else if (ObjectUtils.isEmpty(smsType)) {
            throw new BusinessException("消息类型不能为空");
        }
        //初始化路径并保存
        UserSms userSms = new UserSms();
        BeanUtils.copyProperties(vo, userSms);
        userSms.setSmsPic("等待更新");
        try {
            save(userSms);
            UserSms lastRecord = baseMapper.selectOne(new QueryWrapper<UserSms>().lambda().orderByDesc(UserSms::getSmsId).last("limit 1"));
            //返回消息ID
            return lastRecord.getSmsId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("消息保存失败");
        }
    }
}
