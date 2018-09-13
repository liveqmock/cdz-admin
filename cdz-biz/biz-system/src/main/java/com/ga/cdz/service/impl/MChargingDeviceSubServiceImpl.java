package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingDeviceSubMapper;
import com.ga.cdz.domain.dto.admin.ChargingDeviceSubDTO;
import com.ga.cdz.domain.entity.ChargingDeviceSub;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.service.IMChargingDeviceSubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("mChargingDeviceSubService")
public class MChargingDeviceSubServiceImpl extends ServiceImpl<ChargingDeviceSubMapper, ChargingDeviceSub> implements IMChargingDeviceSubService {


    /**
     * @author huanghaohao
     * @date 2018-09-12 11:33
     * @desc 获取充电枪列表
     * @param chargingDeviceVo
     * @return
     */
    @Override
    public List<ChargingDeviceSubDTO> getChargingDeviceSubList(ChargingDeviceVo chargingDeviceVo) {
        return this.baseMapper.getChargingDeviceSubByDeviceId(chargingDeviceVo);
    }
}
