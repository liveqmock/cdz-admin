package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.dao.charging.ChargingDeviceMapper;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingDeviceDTO;
import com.ga.cdz.domain.entity.ChargingDevice;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.service.IMChargingDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanghaohao
 * @date 2018-09-11 15:04
 * @desc 充电桩的实现类
 */
@Slf4j
@Service("mChargingDeviceService")
public class MChargingDeviceServiceImpl extends ServiceImpl<ChargingDeviceMapper, ChargingDevice> implements IMChargingDeviceService {
    @Resource
    AdminInfoMapper adminInfoMapper;
    @Resource
    UserInfoMapper userInfoMapper;

    /**
     * @author huanghaohao
     * @date 2018-09-11 15:10
     * @desc 插入新的充电桩
     */
    @Override
    public Integer insertNewChargingDevice(ChargingDeviceVo chargingDeviceVo) {
        ChargingDevice chargingDevice = new ChargingDevice();
        BeanUtils.copyProperties(chargingDeviceVo, chargingDevice);
        /**
         * 先查询数据是否重复
         */
        List<ChargingDevice> list = this.baseMapper.selectList(new QueryWrapper<ChargingDevice>().lambda().eq(ChargingDevice::getDeviceName, chargingDevice.getDeviceName()).eq(ChargingDevice::getDeviceCode, chargingDevice.getDeviceCode()));
        if (list.size() > 0) {
            throw new BusinessException("充电桩编码或者名称重复");
        }
        return this.baseMapper.insert(chargingDevice);
    }

    /**
     * @param chargingDeviceVo
     * @return
     * @author huanghaohao
     * @date 2018-09-11 15:42
     * @desc 查询充电桩列表
     */
    @Override
    public List<ChargingDeviceDTO> getChargingDeviceList(ChargingDeviceVo chargingDeviceVo) {
        ChargingDevice chargingDevice = new ChargingDevice();
        chargingDevice.setStationId(chargingDeviceVo.getStationId());
        return this.baseMapper.getChargingDeviceListDetail(chargingDevice);
//    return this.baseMapper.selectList(new QueryWrapper<ChargingDevice>().lambda().eq(ChargingDevice::getStationId,stationId));
    }

}
