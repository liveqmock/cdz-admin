package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.admin.ChargingShopSelectVo;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingShopService;
import com.ga.cdz.util.MUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 商户管理服务实现类
 * @date: 2018/9/10 12:41
 */
@Service("mChargingShopService")
public class MChargingShopServiceImpl extends ServiceImpl<ChargingShopMapper, ChargingShop> implements IMChargingShopService {
    /**
     * 工具类
     */
    @Resource
    MUtil mUtil;
    @Override
    public IPage<ChargingShopDTO> getChargingShopPage(PageVo<ChargingShopSelectVo> vo) {
        //分页获取商户
        Page<ChargingShopDTO> page = new Page<>(vo.getIndex(), vo.getSize());
        ChargingShopSelectVo param = new ChargingShopSelectVo();
        BeanUtils.copyProperties(vo.getData(), param);
        List<ChargingShopDTO> list = baseMapper.getShopList(page, param);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<ChargingShop> getListByName(ChargingShopVo vo) {
        return baseMapper.selectList(new QueryWrapper<ChargingShop>().lambda().like(ChargingShop::getShopName, vo.getShopName()));
    }

    @Override
    @Transactional
    public boolean updateShopById(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        BeanUtils.copyProperties(vo, chargingShop);
        //对密码进行加密
        chargingShop.setShopPwd(mUtil.MD5(chargingShop.getShopPwd()));
        //登录名是否存在验证
        ChargingShop hasLogin = baseMapper.selectOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, chargingShop.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasLogin) && (hasLogin.getShopId() != chargingShop.getShopId())) {
            throw new BusinessException("登录名已存在");
        }
        //商户已存在
        List<ChargingShop> hasName = baseMapper.selectList(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopName, chargingShop.getShopName()));
        List<ChargingShop> hasCode = baseMapper.selectList(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopCode, chargingShop.getShopCode()));
        if (hasName.size() > 1 || hasCode.size() > 1) {
            throw new BusinessException("商户名称或编码已存在");
        } else if ((hasName.size() == 1 && hasCode.size() == 1) && (hasCode.get(0).getShopId() != chargingShop.getShopId()) && (hasName.get(0).getShopId() != chargingShop.getShopId())) {
            throw new BusinessException("商户名称或编码已存在");
        } else if (hasCode.size() == 1 && (hasCode.get(0).getShopId() != chargingShop.getShopId())) {
            throw new BusinessException("商户编码已存在");
        } else if (hasName.size() == 1 && (hasName.get(0).getShopId() != chargingShop.getShopId())) {
            throw new BusinessException("商户名称已存在");
        }
        chargingShop.setShopState(ChargingShop.ShopState.NORMAL);
        boolean result = updateById(chargingShop);
        return result;
    }

    @Override
    @Transactional
    public boolean deleteShopById(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        chargingShop = getById(vo.getShopId());
        //判断该商户是否存在
        if (ObjectUtils.isEmpty(chargingShop)) {
            throw new BusinessException("商户id在数据库中不存在");
        }
        //删除
        chargingShop.setShopState(ChargingShop.ShopState.DELETE);
        boolean result = updateById(chargingShop);
        return result;
    }

    @Override
    @Transactional
    public boolean saveChargingShop(ChargingShopVo vo) {
        ChargingShop chargingShop = new ChargingShop();
        BeanUtils.copyProperties(vo, chargingShop);
        //对密码进行加密
        chargingShop.setShopPwd(mUtil.MD5(chargingShop.getShopPwd()));
        //判断登录名、商户名、商户编码是否已存在数据库中且状态为正常
        ChargingShop hasLogin = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasLogin) && (hasLogin.getShopState() == ChargingShop.ShopState.NORMAL)) {
            throw new BusinessException("登录名在数据库中已存在");
        }
        ChargingShop hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasName) && (hasName.getShopState() == ChargingShop.ShopState.NORMAL)) {
            throw new BusinessException("商户名称在数据库中已存在");
        }
        ChargingShop hasCode = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasCode) && (hasCode.getShopState() == ChargingShop.ShopState.NORMAL)) {
            throw new BusinessException("商户编码在数据库中已存在");
        }
        boolean allExists = (!ObjectUtils.isEmpty(hasLogin) && !ObjectUtils.isEmpty(hasName) && !ObjectUtils.isEmpty(hasCode));
        boolean notEq = allExists && (hasCode.getShopId() != hasName.getShopId() || hasCode.getShopId() != hasLogin.getShopId() || hasName.getShopId() != hasLogin.getShopId());

        if (notEq) {
            throw new BusinessException("商户编码、登录名或商户名称已存在");
        } else if (allExists || !ObjectUtils.isEmpty(hasName)) {
            //判断是否存在数据库中，只是状态删除了
            hasName.setShopState(ChargingShop.ShopState.NORMAL);
            updateById(hasName);
            return true;
        }
        //保存商户，初始化状态为正常
        chargingShop.setShopState(ChargingShop.ShopState.NORMAL);
        boolean result = save(chargingShop);
        return result;
    }
}
