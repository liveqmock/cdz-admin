package com.ga.cdz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.domain.vo.base.UserCarsInfoVo;

/**
 * @author:luqi
 * @description: 客户端用户车辆接口
 * @date:2018/9/13_10:21
 */
public interface IUserCarsInfoService extends IService<UserCarsInfo> {

    /**
     * @author:luqi
     * @description: 获取车辆分页
     * @date:2018/9/13_10:23
     * @param: 分页条件
     * @return: 分页
     */
    IPage<UserCarsInfo> getUserCarsInfoPage(PageVo<UserCarsInfoVo> pageVo);


    /**
     * @author:luqi
     * @description: 获取
     * @date:2018/9/13_10:23
     * @param:
     * @return:
     */
    void saveUserCarsInfo(UserCarsInfoVo userCarsInfoVo);

    /**
     * @author:luqi
     * @description: 删除
     * @date:2018/9/13_10:32
     * @param:
     * @return:
     */
    void removeUserCarsInfoByCarNo(UserCarsInfoVo userCarsInfoVo);


}
