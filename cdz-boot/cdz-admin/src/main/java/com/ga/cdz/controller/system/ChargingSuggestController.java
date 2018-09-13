package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingSuggestDTO;
import com.ga.cdz.domain.vo.admin.ChargingSuggestVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingSuggestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 意见反馈控制层
 * @date:2018/9/12 20:50
 */
@RestController
@RequestMapping("/charging/suggest")
public class ChargingSuggestController {
    /**
     * 意见反馈服务
     */
    @Resource
    IMChargingSuggestService mChargingSuggestService;

    /**
     * @author:wanzhongsu
     * @description: 意见反馈列表
     * @date: 2018/9/12 21:01
     * @param:
     * @return:
     */
    @PostMapping("/list")
    public Result getList(@RequestBody @Validated PageVo<ChargingSuggestVo> vo) {
        IPage<ChargingSuggestDTO> page = mChargingSuggestService.getSuggestPage(vo);
        return Result.success().data(page);
    }
}

