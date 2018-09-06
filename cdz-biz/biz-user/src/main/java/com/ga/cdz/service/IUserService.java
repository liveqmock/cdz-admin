package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.UserInfo;

/**
 * @author:luqi
 * @description: 用户接口
 * @date:2018/9/4_10:59
 */
public interface IUserService extends IService<UserInfo> {

     /**
      * @author:luqi
      * @description: 获取user信息
      * @date:2018/9/4_10:59
      * @param: id
      * @return: User实体类
      */
     UserInfo getUserInfoById(Long id);

}
