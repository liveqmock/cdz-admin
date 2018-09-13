package com.ga.cdz.dao.charging;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.dto.admin.ChargingSuggestDTO;
import com.ga.cdz.domain.entity.ChargingSuggest;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 意见反馈mapper
 * @date:2018/9/12 20:12
 */
public interface ChargingSuggestMapper extends BaseMapper<ChargingSuggest> {
    List<ChargingSuggestDTO> getSuggestPage(IPage<ChargingSuggestDTO> page);
}
