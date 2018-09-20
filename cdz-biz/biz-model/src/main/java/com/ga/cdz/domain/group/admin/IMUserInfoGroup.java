package com.ga.cdz.domain.group.admin;

/**
 * @author:luqi
 * @description: 管理后台用户信息规则组
 * @date:2018/9/5_11:50
 */
public interface IMUserInfoGroup {

    /**
     * @author:luqi
     * @description: 获取用户根据id
     * @date:2018/9/5_11:51
     */
    interface GetById {
    }


    /**
     * @author:luqi
     * @description: 获取用户根据多条件
     * @date:2018/9/5_11:51
     */
    interface GetByCon {
    }

    /**
     * @author:luqi
     * @description: 获取用户列表根据多条件
     * @date:2018/9/5_11:55
     */
    interface GetListByCon {
    }

    /**
     * @author huanghaohao
     * @date 2018年9月9日 23点09分
     * @desc 更新user会员状态
     */
    interface updateMemSate {
    }

    /**
     * @author huanghaohao
     * @date 2018年9月9日 23点10分
     */
    interface updateMemCardStat {
    }

}
