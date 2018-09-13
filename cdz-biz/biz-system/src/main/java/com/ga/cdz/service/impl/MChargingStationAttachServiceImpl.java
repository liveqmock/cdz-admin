package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingStationAttachMapper;
import com.ga.cdz.domain.entity.ChargingStationAttach;
import com.ga.cdz.service.IMChargingStationAttachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author:luqi
 * @description: 充电桩附件 实现类
 * @date:2018/9/11_20:03
 */
@Slf4j
@Service("mChargingStationAttachService")
public class MChargingStationAttachServiceImpl extends ServiceImpl<ChargingStationAttachMapper, ChargingStationAttach> implements IMChargingStationAttachService {


}
