package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.AdminLoginDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.vo.base.AdminInfoVo;

/**
 * @author:luqi
 * @description:  认证服务接口
 * @date:2018/9/4_10:49
 */
public interface IMAccountService extends IService<AdminInfo> {


    /**
     * @author:luqi
     * @description:  登陆
     * @date:2018/9/3_14:34
     * @param:
     * @return:
     */
    AdminLoginDTO login(AdminInfoVo adminInfoVo);

}
