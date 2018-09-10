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
     * @description: 根据上级区域获取数据
     * @date: 2018/9/10_11:07
     * @param: String 父区域id
     * @return: 返回该上级区域包含的list
     */
    List<District> getListByParentId(int par_id);
}
