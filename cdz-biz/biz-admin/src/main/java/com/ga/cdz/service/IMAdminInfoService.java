package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.AdminPermission;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.domain.vo.base.PageVo;

import java.util.List;

/**
 * @author:luqi
 * @description: 关于Admin管理的Service（管理员信息管理）
 * @date:2018/9/4_10:49
 */
public interface IMAdminInfoService extends IService<AdminInfo> {

     /**
      * @author:luqi
      * @description: 添加AdminInfo
      * @date:2018/9/5_16:22
      * @param: AdminInfoVo
      * @return: boolean
      */
     boolean saveAdminIno(AdminInfoVo adminInfoVo);

     /**
      * @author:luqi
      * @description: 修改AdminInfo 通过id
      * @date:2018/9/5_16:22
      * @param: AdminInfoVo
      * @return: boolean
      */
     boolean updateAdminInfoById(AdminInfoVo adminInfoVo);

     /**
      * @author:luqi
      * @description: 修改AdminInfo 通过id
      * @date:2018/9/5_16:22
      * @param: AdminInfoVo
      * @return: boolean
      */
     IPage<AdminInfo> getAdminInfoPageByCon(PageVo<AdminInfoVo> vo);

     /**
      * @author:luqi
      * @description:  删除通过id
      * @date:2018/9/5_16:22
      * @param: AdminInfoVo
      * @return: boolean
      */
     boolean removeAdminInfoById(Integer id);



}
