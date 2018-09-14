package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.UserSms;
import com.ga.cdz.domain.vo.admin.UserSmsAddVo;
import com.ga.cdz.domain.vo.base.PageVo;


/**
 * @author:wanzhongsu
 * @description: 消息服务
 * @date:2018/9/12 14:29
 */

public interface IMUserSmsService extends IService<UserSms> {
    /**
     * @author:wanzhongsu
     * @description: 查询消息
     * @date: 2018/9/12 17:00
     * @param: PageVo
     * @return: Result
     */
    IPage<UserSms> getSmsPage(PageVo<UserSmsAddVo> vo);

    /**
     * @author:wanzhongsu
     * @description: 保存消息
     * @date: 2018/9/12 18:08
     * @param: UserSmsAddVo
     * @return: 消息ID
     */
    int saveSms(UserSmsAddVo vo);
}
