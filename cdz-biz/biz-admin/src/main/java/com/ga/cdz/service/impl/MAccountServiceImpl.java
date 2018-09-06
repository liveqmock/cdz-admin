package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.AdminLoginDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.service.IMAccountService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author:luqi
 * @description: 认证接口具体实现类
 * @date:2018/9/4_10:54
 */
@Service("mAccountService")
public class MAccountServiceImpl extends ServiceImpl<AdminInfoMapper,AdminInfo> implements IMAccountService {

    @Resource
    MUtil mUtil;

    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public AdminLoginDTO login(AdminInfoVo adminInfoVo) {
        AdminInfo adminInfo=new AdminInfo();
        BeanUtils.copyProperties(adminInfoVo,adminInfo);
        //密码MD5
        String md5Pwd= mUtil.MD5(adminInfo.getAdminPwd());
        adminInfo.setAdminPwd(md5Pwd);
        AdminInfo hasAdminInfo= baseMapper.selectOne(new QueryWrapper<AdminInfo>().setEntity(adminInfo));
        if(ObjectUtils.isEmpty(hasAdminInfo)){
            throw new BusinessException("账号或密码错误");
        }
        //token 生成 uuid 在md5
        String token=mUtil.MD5(mUtil.UUID16());
        //查询角色
        //查询权限列表
        return null;
    }
}
