package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingSuggestDTO;
import com.ga.cdz.domain.entity.ChargingSuggest;
import com.ga.cdz.domain.vo.base.PageVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 意见反馈服务
 * @date:2018/9/12 20:46
 */
public interface IMChargingSuggestService extends IService<ChargingSuggest> {
    List<ChargingSuggestDTO> getSuggestPage();
}
