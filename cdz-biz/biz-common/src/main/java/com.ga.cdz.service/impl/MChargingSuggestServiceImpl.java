package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingSuggestMapper;
import com.ga.cdz.domain.dto.admin.ChargingSuggestDTO;
import com.ga.cdz.domain.entity.ChargingSuggest;
import com.ga.cdz.domain.vo.admin.ChargingSuggestVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingSuggestService;
import org.springframework.beans.BeanUtils;
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
    public IPage<ChargingSuggestDTO> getSuggestPage(PageVo<ChargingSuggestVo> vo) {
        IPage<ChargingSuggestDTO> page = new Page<>(vo.getIndex(), vo.getSize());
        ChargingSuggestVo suggest = new ChargingSuggestVo();
        BeanUtils.copyProperties(vo.getData(), suggest);
        List<ChargingSuggestDTO> list = baseMapper.getSuggestPage(page, suggest);
        page.setRecords(list);
        return page;
    }
}
