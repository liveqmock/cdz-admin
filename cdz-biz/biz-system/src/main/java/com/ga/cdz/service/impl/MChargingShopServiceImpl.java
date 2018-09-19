package com.ga.cdz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.constant.RedisConstant;
import com.ga.cdz.dao.charging.ChargingShopMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.AdminLoginDTO;
import com.ga.cdz.domain.dto.admin.ChargingShopDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.vo.admin.ChargingShopSelectVo;
import com.ga.cdz.domain.vo.base.ChargingShopVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingShopService;
import com.ga.cdz.util.MRedisUtil;
import com.ga.cdz.util.MUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:wanzhongsu
 * @description: 商户管理服务实现类
 * @date: 2018/9/10 12:41
 */
@Service("mChargingShopService")
public class MChargingShopServiceImpl extends ServiceImpl<ChargingShopMapper, ChargingShop> implements IMChargingShopService {
    /**
     * @author huanghaohao
     * @date 2018年9月19日 14点16分
     * @desc 工具类
     * @param vo
     * @return
     */
    @Resource
    MUtil mUtil;


    /***redis工具类**/
    @Resource
    MRedisUtil mRedisUtil;

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
        //判断登录名是否已存在数据库中且状态为正常
        ChargingShop hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopState, ChargingShop.ShopState.NORMAL).eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("登录名在数据库中已存在");
        }
        //判断登录名是否存在数据库中，只是状态删除了
        hasName = getOne(new QueryWrapper<ChargingShop>().lambda().eq(ChargingShop::getShopLogin, vo.getShopLogin()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setShopState(ChargingShop.ShopState.NORMAL);
            updateById(hasName);
            return true;
        }
        //保存商户，初始化状态为正常
        chargingShop.setShopState(ChargingShop.ShopState.NORMAL);
        boolean result = save(chargingShop);
        return result;
    }

    /**
     * @auhtor huanghaohao
     * @date 2018年9月19日 14点30分
     * @param chargingShopVo
     * @desc  登陆
     * @return
     */
    @Override
    public ChargingShopDTO login(ChargingShopVo chargingShopVo) {
        ChargingShop chargingShop= new ChargingShop();
        BeanUtils.copyProperties(chargingShopVo,chargingShop);
        String md5Pwd= mUtil.MD5(chargingShop.getShopPwd());
        ChargingShop hasChargingShop = baseMapper.selectOne(new QueryWrapper<ChargingShop>().lambda()
                .eq(ChargingShop::getShopLogin, chargingShop.getShopLogin()).eq(ChargingShop::getShopPwd, md5Pwd));
        if(ObjectUtils.isEmpty(hasChargingShop)){
            throw new BusinessException("账号或密码错误");
        }

        //token 生成 uuid 在md5
        String token=mUtil.MD5(mUtil.UUID16());
        //redisTokenKey
        String redisTokenKey = RedisConstant.SHOP_TOKEN + hasChargingShop.getShopLogin();
        ChargingShopDTO chargingShopDTO=new ChargingShopDTO();
        chargingShopDTO.setShopCode(hasChargingShop.getShopCode());
        chargingShopDTO.setShopContact(hasChargingShop.getShopContact());
        chargingShopDTO.setShopId(hasChargingShop.getShopId());
        chargingShopDTO.setShopName(hasChargingShop.getShopName());
        chargingShopDTO.setShopTel(hasChargingShop.getShopTel());
        chargingShopDTO.setShopLogin(hasChargingShop.getShopLogin());
        chargingShopDTO.setToken(token);
        chargingShopDTO.setInsertDt(hasChargingShop.getInsertDt());
        //token加入redis,7天过期
        mRedisUtil.put(redisTokenKey, token, 7L, TimeUnit.DAYS);

        return chargingShopDTO;
    }
}
