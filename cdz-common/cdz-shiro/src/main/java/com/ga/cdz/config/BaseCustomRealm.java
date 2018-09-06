package com.ga.cdz.config;

import com.ga.cdz.domain.bean.StatelessToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author:luqi
 * @description: realm基类
 * @date:2018/9/3_12:56
 */
public abstract class BaseCustomRealm extends AuthorizingRealm {
    //设置realm的名称
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }

    @Override
    protected abstract AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals);

    @Override
    protected abstract AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;
}
