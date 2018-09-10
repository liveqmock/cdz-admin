package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.District;

import java.util.List;

/**
 * @author: liuyi
 * @description: 区域地址操作
 * @date: 2018/9/10_10:58
 */
public interface IDistrictService extends IService<District> {
    /**
     * @author: liuyi
     * @description: 得到所有的市级地区
     * @date: 2018/9/10_11:07
     * @return: 返回包含所有市级地区的list
     */
    List<District> getListAllCity();
}
