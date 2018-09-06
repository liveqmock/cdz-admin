package com.ga.cdz.domain.group.admin;

/**
 * @author:luqi
 * @description: 后台AdminInfo验证规则组
 * @date:2018/9/5_11:46
 */
public interface IMAdminInfoGroup {

    /**
     * @author:luqi
     * @description: 后台登陆组
     * @date:2018/9/5_11:57
     */
    interface Login{}

    /**
     * @author:luqi
     * @description: 后台管理员添加组
     * @date:2018/9/5_11:47
     */
    interface Add{}

    /**
     * @author:luqi
     * @description: 后台管理员更新组
     * @date:2018/9/5_11:47
     */
    interface Update{}

    /**
     * @author:luqi
     * @description: 后台管理员删除
     * @date:2018/9/5_11:47
     */
    interface Remove{}

}
