package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.AdminLoginDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.AdminRole;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.service.IMAccountService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:luqi
 * @description: 认证接口具体实现类
 * @date:2018/9/4_10:54
 */
@Service("mAccountService")
public class MAccountServiceImpl extends ServiceImpl<AdminInfoMapper,AdminInfo> implements IMAccountService {

    /***工具类***/
    @Resource
    MUtil mUtil;

    /***redis工具类**/
    @Resource
    MRedisUtil mRedisUtil;

    @Override
    public AdminLoginDTO login(AdminInfoVo adminInfoVo) {
        AdminInfo adminInfo=new AdminInfo();
        BeanUtils.copyProperties(adminInfoVo,adminInfo);
        //密码MD5
        String md5Pwd= mUtil.MD5(adminInfo.getAdminPwd());
        AdminInfo hasAdminInfo = baseMapper.selectOne(new QueryWrapper<AdminInfo>().lambda()
                .eq(AdminInfo::getAdminAccount, adminInfo.getAdminAccount()).eq(AdminInfo::getAdminPwd, md5Pwd));
        if(ObjectUtils.isEmpty(hasAdminInfo)){
            throw new BusinessException("账号或密码错误");
        }
        //token 生成 uuid 在md5
        String token=mUtil.MD5(mUtil.UUID16());
        //redisTokenKey
        String redisTokenKey = RedisConstant.ADMIN_TOKEN + hasAdminInfo.getAdminAccount();
        //权限rediskey
        String redisPermKey = RedisConstant.ADMIN_PERM + hasAdminInfo.getAdminAccount();
        //查询权限列表
        List<String> permCodeList = baseMapper.selectAdminPermCodeListById(hasAdminInfo.getAdminId());
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
        adminLoginDTO.setAdminId(hasAdminInfo.getAdminId());
        adminLoginDTO.setToken(token);
        adminLoginDTO.setAdminAccount(hasAdminInfo.getAdminAccount());
        adminLoginDTO.setAdminName(hasAdminInfo.getAdminName());
        adminLoginDTO.setAdminPermissionList(permCodeList);
        //token加入redis,7天过期
        mRedisUtil.put(redisTokenKey, token, 7L, TimeUnit.DAYS);
        //权限加入redis中
        mRedisUtil.put(redisPermKey, permCodeList);
        return adminLoginDTO;
    }
}
