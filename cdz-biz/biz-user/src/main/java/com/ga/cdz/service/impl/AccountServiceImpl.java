package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.UserCardInfoMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.api.UserLoginDTO;
import com.ga.cdz.domain.entity.UserCardInfo;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.UserInfoLoginVo;
import com.ga.cdz.domain.vo.api.UserInfoRegisterVo;
import com.ga.cdz.domain.vo.api.UserInfoRetrieverVo;
import com.ga.cdz.domain.vo.api.UserInfoSendSmsVo;
import com.ga.cdz.domain.vo.base.UserSmsPushVo;
import com.ga.cdz.service.IAccountService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MSmsUtil;
import com.ga.cdz.util.MUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;


/**
 * @author:luqi
 * @description: 客户端认证与登录实现类
 * @date:2018/9/7_13:26
 */
@Slf4j
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IAccountService {

    /**
     * 用户头像路径
     */
    @Value("${url.user_avatar}")
    String userAvatar;

    /**
     * 用户卡信息
     **/
    @Resource
    UserCardInfoMapper userCardInfoMapper;

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


    @Value("${sms.timeout}")
    private Long REDIS_TIME_OUT;

    @Override
    public void registerSendSms(UserInfoSendSmsVo userInfoSendSmsVo) {
        String userTel = userInfoSendSmsVo.getUserTel();
        String smsRedisKey = RedisConstant.USER_REGISTER_SMS + userTel;
        /**判断用户是否已经发送过短信**/
        /**调用 sms 工具类发送短信，并判断短信是否发送成功，可参照以前项目**/
        /**发送成功后把短信存入到redis 中 设置 其时效性 **/

        /**去数据库验证电话是否被注册*/
        UserInfo hasUserTel = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserTel, userTel));
        if (!ObjectUtils.isEmpty(hasUserTel)) {
            throw new BusinessException("电话已被注册");
        }
        /**生成验证码*/
        String code = mSmsUtil.buildCode();
        String isSend = mSmsUtil.sendCodeDetail(userTel, code);
        if (StringUtils.isEmpty(isSend)) {
            mRedisUtil.put(smsRedisKey, code, REDIS_TIME_OUT, TimeUnit.SECONDS);
        } else {
            throw new BusinessException(isSend);
        }
    }

    @Override
    public void retrieveSendSms(UserInfoSendSmsVo userInfoSendSmsVo) {
        String userTel = userInfoSendSmsVo.getUserTel();
        String smsRedisKey = RedisConstant.USER_RETRIEVE_SMS + userTel;
        /**去数据库验证是否存在该电话*/
        UserInfo hasUserTel =baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserTel, userTel));
        if(ObjectUtils.isEmpty(hasUserTel)){
            throw new BusinessException("该电话不存在");
        }
        /**生成验证码*/
        String code = mSmsUtil.buildCode();
        String isSend = mSmsUtil.sendCodeDetail(userTel, code);
        if (StringUtils.isEmpty(isSend)) {
            mRedisUtil.put(smsRedisKey, code, REDIS_TIME_OUT, TimeUnit.SECONDS);
        } else {
            throw new BusinessException(isSend);
        }
    }

    @Override
    @Transactional
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
        /**生成数据库插入对象 设置用户的属性userType为个人 默认可用状态**/
        UserInfo userInfo = new UserInfo();
        userInfo.setUserPwd(md5Pwd).setUserTel(userTel)
                .setUserSex(registerVo.getUserSex()).setUserNickName(userNickName)
                .setUserType(UserInfo.UserType.PERSONAL).setUserState(UserInfo.UserState.NORMAL);
        boolean isSuccess = save(userInfo);
        if (!isSuccess) {
            throw new BusinessException("注册失败，稍后重试");
        }

        /**注册成功后新生成card*/
        String maxCardCode = userCardInfoMapper.getCardCodeLast();
        if (StringUtils.isEmpty(maxCardCode)) {
            maxCardCode = "10000";
        }
        /**cardCode*/
        String cardCode = new BigInteger(maxCardCode).add(new BigInteger("1")).toString();
        log.info("cardCode=======>{}");
        UserCardInfo userCardInfo = new UserCardInfo();
        userCardInfo.setCardCode(cardCode).setUserId(userInfo.getUserId());
        int userCarInfoRow = userCardInfoMapper.insert(userCardInfo);
        if (userCarInfoRow != 1) {
            throw new BusinessException("注册失败，稍后重试");
        }
        /**注册成功后删除redis的短信键值对**/
        mRedisUtil.remove(smsRedisKey);
    }

    @Override
    public void registerCallBack(UserSmsPushVo userSmsPushVo) {

    }

    @Override
    public void retriever(UserInfoRetrieverVo retrieverVo) {
        String userTel = retrieverVo.getUserTel();
        String smsRedisKey = RedisConstant.USER_RETRIEVE_SMS + userTel;
        /**判断在缓存中验证码是否存在*/
        if (!mRedisUtil.hasKey(smsRedisKey)) {
            throw new BusinessException("验证码失效");
        }
        /**判断上传验证码是否与缓存验证码一致*/
        String cacheSmsCode = mRedisUtil.get(smsRedisKey);
        if (!cacheSmsCode.equals(retrieverVo.getSmsCode())) {
            throw new BusinessException("验证码不一致");
        }
        /**去数据库验证电话是否被注册*/
        UserInfo hasUserTel = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserTel, userTel));
        if (ObjectUtils.isEmpty(hasUserTel)) {
            throw new BusinessException("该电话不存在");
        }
        /**把用户上传的密码md5加密**/
        String md5Pwd = mUtil.MD5(retrieverVo.getUserPwd());
        /**生成数据库更新对象**/
        hasUserTel.setUserPwd(md5Pwd);
        int isSuccess = baseMapper.updateById(hasUserTel);
        if (isSuccess == 0) {
            throw new BusinessException("找回密码失败，稍后重试");
        }
        /**找回成功后删除redis的短信键值对**/
        mRedisUtil.remove(smsRedisKey);
    }

    @Override
    public UserLoginDTO login(UserInfoLoginVo userInfoLoginVo) {
        /**用户密码md5*/
        String md5Pwd = mUtil.MD5(userInfoLoginVo.getUserPwd());
        String userTel = userInfoLoginVo.getUserTel();
        UserInfo hasUserInfo = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getUserTel, userTel).eq(UserInfo::getUserPwd, md5Pwd));
        if (ObjectUtils.isEmpty(hasUserInfo)) {
            throw new BusinessException("电话或密码错误");
        }
        /**生成token*/
        String token = mUtil.MD5(mUtil.UUID16());
        String redisTokenKey = RedisConstant.USER_TOKEN + userTel;
        /**redis 7天*/
        mRedisUtil.put(redisTokenKey, token, 7L, TimeUnit.DAYS);
        /**组成返回前端*/
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserTel(userTel);
        userLoginDTO.setToken(token);
        userLoginDTO.setUserId(hasUserInfo.getUserId());
        userLoginDTO.setUserNickName(hasUserInfo.getUserNickName());
        userLoginDTO.setUserAvatar(userAvatar + hasUserInfo.getUserAvatar());
        return userLoginDTO;
    }
}
