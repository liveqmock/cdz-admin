package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.District;
import com.ga.cdz.domain.vo.base.DistrictVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description:
 * @date: 2018/9/7 10:59
 */
public interface IMDistrictService extends IService<District> {
    List<District> getListByParentId(String par_id);
}
