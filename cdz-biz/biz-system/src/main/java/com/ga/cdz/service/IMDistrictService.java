package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.domain.vo.base.DistrictVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description:地区信息服务接口
 * @date: 2018/9/7 10:59
 */
public interface IMDistrictService extends IService<District> {
    /**
     * @author:wanzhongsu
     * @description: 根据上一级编码获取地区信息列表
     * @date: 2018/9/10 9:40
     * @param:par_id 上一级编码
     * @return:地区信息列表
     */
    List<District> getListByParentId(int parId);
}
