package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.UserInfoLoginVo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.service.IAccountService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MSmsUtil;
import com.ga.cdz.util.MUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description: 客户端认证与登录实现类
 * @date:2018/9/7_13:26
 */
@Slf4j
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IAccountService {

    /**
     * redis工具类
     **/
    @Resource
    MRedisUtil mRedisUtil;

    /**
     * sms工具类
     **/
    @Resource
    MSmsUtil mSmsUtil;

    @Resource
    MUtil mUtil;


    @Override
    public String registerSendSms(UserInfoSendSmsVo userInfoSendSmsVo) {
        //结果，结果为空字符串代表短信发送成功，如果有错误，把错误提示放在rs上
        String rs = "";
        String userTel = userInfoSendSmsVo.getUserTel();
        String smsRedisKey = RedisConstant.USER_REGISTER_SMS + userTel;
        /**判断用户是否已经发送过短信**/
        /**调用 sms 工具类发送短信，并判断短信是否发送成功，可参照以前项目**/
        /**发送成功后把短信存入到redis 中 设置 其时效性 **/
        return rs;
    }

    @Override
    public void register(UserInfoRegisterVo registerVo) {
        String userTel = registerVo.getUserTel();
        String smsRedisKey = RedisConstant.USER_REGISTER_SMS + userTel;
        /**判断在缓存中验证码是否存在*/
        if (!mRedisUtil.hasKey(smsRedisKey)) {
            throw new BusinessException("验证码失效");
        }
        /**判断上传验证码是否与缓存验证码一致*/
        String cacheSmsCode = mRedisUtil.get(smsRedisKey);
        if (!cacheSmsCode.equals(registerVo.getSmsCode())) {
            throw new BusinessException("验证码不一致");
        }
        /**去数据库验证电话是否被注册*/
        UserInfo hasUserTel = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserTel, userTel));
        if (!ObjectUtils.isEmpty(hasUserTel)) {
            throw new BusinessException("电话已被注册");
        }
        /**把用户上传的密码md5加密**/
        String md5Pwd = mUtil.MD5(registerVo.getUserPwd());
        /**生成一个随机昵称**/
        String userNickName = "cdz_" + System.currentTimeMillis();
        /**生成数据库插入对象 设置用户的属性userType为个人**/
        UserInfo userInfo = new UserInfo();
        userInfo.setUserPwd(md5Pwd).setUserTel(userTel)
                .setUserSex(registerVo.getUserSex()).setUserNickName(userNickName)
                .setUserType(UserInfo.UserType.PERSONAL);
        boolean isSuccess = save(userInfo);
        if (!isSuccess) {
            throw new BusinessException("注册失败，稍后重试");
        }
        /**注册成功后删除redis的短信键值对**/
        mRedisUtil.remove(smsRedisKey);
    }

    @Override
    public UserLoginDTO login(UserInfoLoginVo userInfoLoginVo) {

        return null;
    }
}
