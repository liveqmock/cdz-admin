package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.dao.center.DistrictMapper;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.dao.charging.ChargingStationMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.admin.ChargingStationSelectVo;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站列表服务实现类
 * @date:2018/9/10 15:09
 */
@Slf4j
@Service("mChargingStationService")
public class MChargingStationServiceImpl extends ServiceImpl<ChargingStationMapper, ChargingStation> implements IMChargingStationService {
    /**
     * 区域mapper
     */
    @Resource
    DistrictMapper districtMapper;

    //todo-ok Cedar 不需要的依赖请删除
    /**
     *
     */
    @Resource
    AdminInfoMapper adminInfoMapper;
    @Resource
    ChargingShopMapper chargingShopMapper;


    @Override
    public IPage<ChargingStationDTO> getStationPage(PageVo<ChargingStationSelectVo> vo, String name) {
        //构建分页信息
        Page<ChargingStationDTO> page = new Page<>(vo.getIndex(), vo.getSize());
        //复制
        ChargingStationSelectVo param = new ChargingStationSelectVo();
        BeanUtils.copyProperties(vo.getData(), param);
        //todo-ok huanghaohao 根据Admin的 adminAccount来判断，adminName判断不可行，此处应返回 一个AdminInfo对象 而不是 List集合
        AdminInfo adminInfo = adminInfoMapper.selectOne(new QueryWrapper<AdminInfo>().lambda().eq(AdminInfo::getAdminAccount, name));
        //todo-ok Cedar 此次不需要初始化 来自IDEA的提示
        List<ChargingStationDTO> lists;
        //todo-ok huanghaohao 判断修改
        if (!ObjectUtils.isEmpty(adminInfo)) {
            //为管理员帐号
            lists = baseMapper.getStationList(page, param, new ChargingShop());
        } else {
            ChargingShop chargingShop = chargingShopMapper.selectOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, name));
            lists = baseMapper.getStationList(page, param, chargingShop);
        }
        //查询后跨库查询区域名称
        lists.forEach(item -> {
            item.setScity(districtMapper.selectById(item.getCity()).getDistrictName());
            item.setSprovince(districtMapper.selectById(item.getProvince()).getDistrictName());
            item.setScountry(districtMapper.selectById(item.getCountry()).getDistrictName());
            item.setScounty(districtMapper.selectById(item.getCounty()).getDistrictName());
        });
        page.setRecords(lists);
        return page;
    }

    @Override
    public List<ChargingStation> getStationList(ChargingStationVo vo) {
        List<ChargingStation> chargingStations = baseMapper.selectList(new QueryWrapper<ChargingStation>().lambda().like(ChargingStation::getStationName, vo.getStationName()));
        return chargingStations;
    }


    //todo-ok Cendar 把方法返回类型修改为void ，业务异常请使用 BusinessException
    @Override
    @Transactional
    public void updateStationById(ChargingStationVo vo) {
        //todo-ok Cendar 站点名称和站点编号不能修改 请做条件判断
        ChargingStation chargingStation = new ChargingStation();
        BeanUtils.copyProperties(vo, chargingStation);
        //验证充电站名称是否已经存在
        ChargingStation hasName = baseMapper.selectOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationState, ChargingStation.StationState.NORMAL).eq(ChargingStation::getStationName, chargingStation.getStationName()));
        if (!ObjectUtils.isEmpty(hasName) && hasName.getStationId() != chargingStation.getStationId()) {
            throw new BusinessException("充电站名称已存在");
        }
        chargingStation.setStationCode(getById(chargingStation.getStationId()).getStationCode());
        //保存修改信息
        int result = this.baseMapper.updateById(chargingStation);
        if (result != 0) {
            return;
        } else {
            throw new BusinessException("保存失败");
        }
    }

    //todo-ok Cendar 把方法返回类型修改为void ，业务异常请使用 BusinessException
    @Override
    @Transactional
    public void deleteStationById(ChargingStationVo vo) {
        //根据传入的id判断该充电站是否存在
        ChargingStation hasAccount = getById(vo.getStationId());
        if (ObjectUtils.isEmpty(hasAccount)) {
            throw new BusinessException("充电站ID不存在");
        }
        //将充电站逻辑删除
        ChargingStation chargingStation = getById(vo.getStationId());
        chargingStation.setStationState(ChargingStation.StationState.DELETE);
        boolean result = updateById(chargingStation);
        if (!result) {
            throw new BusinessException("删除失败");
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 产生充电站编码
     * @date: 2018/9/20 14:42
     * @return: 充电站编码
     */
    private String nextStationCode() {
        //查询当前最大的充电站编码
        ChargingStation station = baseMapper.selectOne(new QueryWrapper<ChargingStation>().lambda().orderByDesc(ChargingStation::getStationCode).last("limit 1"));
        String nextCode;
        //获取下一个充电站编码
        if (ObjectUtils.isEmpty(station)) {
            nextCode = "0000";
        } else {
            nextCode = station.getStationCode();
            int value = Integer.parseInt(nextCode) + 1;
            nextCode = String.format("%04d", value);
            if (nextCode.equals("9999")) {
                throw new BusinessException("充电站编号已用完");
            }
        }
        return nextCode;
    }

    //todo-ok Cendar 添加站点 站点编号从0000开始，且该参数由后台自动生成，前台不需要传参，vo里面stationCode，添加时候不需要验证
    @Override
    @Transactional
    public int saveStation(ChargingStationVo vo) {
        //属性复制
        ChargingStation chargingStation = new ChargingStation();
        BeanUtils.copyProperties(vo, chargingStation);
        //根据名字查询该名称是否存在
        ChargingStation hasName = getOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationState, ChargingStation.StationState.NORMAL).eq(ChargingStation::getStationName, vo.getStationName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("该充电站名称已存在");
        }
        //产生充电站编码并保存
        String nextCode = nextStationCode();
        //todo-ok cedar  不需要联合判断，数据库已经创建组合索引
        chargingStation.setStationCode(nextCode);
        //todo-ok Cedar 不用考虑原有数据存在 新的站点  新的站点编码，站点名称可以与原来一致
        //保存商户，商户状态初始化为正常
        chargingStation.setStationState(ChargingStation.StationState.NORMAL);
        boolean value = save(chargingStation);
        //返回保存后的StationId值，否则-1
        if (value) {
            ChargingStation obj = baseMapper.selectOne(new QueryWrapper<ChargingStation>().lambda().eq(ChargingStation::getStationState, ChargingStation.StationState.NORMAL).eq(ChargingStation::getStationName, chargingStation.getStationName()));
            int result = obj.getStationId();
            return result;
        } else {
            return -1;
        }
    }


}
