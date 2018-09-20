package com.ga.cdz.domain.bean;


import com.ga.cdz.config.BaseCustomRealm;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.util.MRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * @author:luqi
 * @description: 自定义realm 实现了授权和认证
 * @date:2018/9/3_14:22
 */
@Slf4j
public class CustomRealm extends BaseCustomRealm {

    @Resource
    MRedisUtil mRedisUtil;

    /**
     * @author:luqi
     * @description: 权限授权
     * @date:2018/9/3_14:32
     * @param: PrincipalCollection
     * @return: AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    /**
     * @author:luqi
     * @description: 登陆认证
     * @date:2018/9/3_14:34
     * @param: AuthenticationToken
     * @return: AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        String phone = statelessToken.getUsername();
        String clientDigest = statelessToken.getClientDigest();
        String key = RedisConstant.USER_TOKEN + phone;
        //判断缓存中是否有登陆信息
        if (mRedisUtil.hasKey(key)) {
            //判断token是否一致
            String cacheToken = mRedisUtil.get(key);
            if (!cacheToken.equals(clientDigest)) {
                //代表者token被刷新，通知需要重新登陆，
                // 异地登陆或主动刷新了token调用了autoLogin接口
                throw new DisabledAccountException();
            }
            return new SimpleAuthenticationInfo(phone, clientDigest, this.getName());
        } else {
            //没有登录 或 过期需要重新登陆
            return null;
        }
    }
}
