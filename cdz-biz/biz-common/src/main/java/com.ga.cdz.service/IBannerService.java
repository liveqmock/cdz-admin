package com.ga.cdz.service;

import com.ga.cdz.domain.entity.Banner;

import java.util.List;

/**
 * @author: liuyi
 * @description: Banner的服务接口
 * @date: 2018/9/11_11:37
 */
public interface IBannerService {

    /**
     * @author: liuyi
     * @description: 得到所有的Banner消息
     * @date: 2018/9/11_14:58
     * @return: 所有Banner消息
     */
    List<Banner> getListAllBanner();

}
