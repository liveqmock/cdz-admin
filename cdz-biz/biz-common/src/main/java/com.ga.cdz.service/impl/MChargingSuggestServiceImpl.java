package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingSuggestMapper;
import com.ga.cdz.domain.dto.admin.ChargingSuggestDTO;
import com.ga.cdz.domain.entity.ChargingSuggest;
import com.ga.cdz.service.IMChargingSuggestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 意见反馈服务实现类
 * @date:2018/9/12 20:46
 */
@Service("mChargingSuggestService")
public class MChargingSuggestServiceImpl extends ServiceImpl<ChargingSuggestMapper, ChargingSuggest> implements IMChargingSuggestService {

    @Override
    public List<ChargingSuggestDTO> getSuggestPage() {
        List<ChargingSuggestDTO> list=baseMapper.getSuggestPage();
        return list;
    }
}
