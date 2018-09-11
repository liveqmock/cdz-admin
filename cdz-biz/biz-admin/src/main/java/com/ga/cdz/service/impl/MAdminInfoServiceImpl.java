package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.center.AdminInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.admin.AdminDemoDTO;
import com.ga.cdz.domain.entity.AdminInfo;
import com.ga.cdz.domain.vo.base.AdminInfoVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMAdminInfoService;
import com.ga.cdz.util.MUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author:luqi
 * @description: 管理员接口具体实现类
 * @date:2018/9/4_10:54
 */
@Slf4j
@Service("mAdminInfoService")
public class MAdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IMAdminInfoService {


    /***工具类*/
    @Resource
    MUtil mUtil;

    /***
     *  如果不满足请直接 抛出异常
     * */
    @Override
    @Transactional
    public boolean saveAdminIno(AdminInfoVo adminInfoVo) {
        /***关注数据是否有唯一索引请在代码层面逐一判断**/
        AdminInfo adminInfo = new AdminInfo();
        /**类的属性拷贝*/
        BeanUtils.copyProperties(adminInfoVo, adminInfo);
        /**判断账号是否存在，唯一索引判断*/
        AdminInfo hasAccount = baseMapper.selectOne(new QueryWrapper<AdminInfo>().lambda()
                .eq(AdminInfo::getAdminAccount, adminInfo.getAdminAccount()));
        if (!ObjectUtils.isEmpty(hasAccount)) {
            throw new BusinessException("账号已存在");
        }
        /**判断电话是否存在，唯一索引判断*/
        AdminInfo hasPhone = baseMapper.selectOne(new QueryWrapper<AdminInfo>().lambda()
                .eq(AdminInfo::getAdminTel, adminInfo.getAdminTel()));
        if (!ObjectUtils.isEmpty(hasPhone)) {
            throw new BusinessException("电话已存在");
        }
        /**可以注册，密码MD5加密*/
        String md5Pwd = mUtil.MD5(adminInfo.getAdminPwd());
        adminInfo.setAdminPwd(md5Pwd);
        /**初始化平台状态为可用*/
        adminInfo.setAdminState(AdminInfo.AdminState.ABLE);
        return save(adminInfo);
    }

    @Override
    @Transactional
    public boolean updateAdminInfoById(AdminInfoVo adminInfoVo) {
        /***关注数据是否有唯一索引请在代码层面逐一判断**/
        AdminInfo adminInfo = new AdminInfo();
        /**类的属性拷贝*/
        BeanUtils.copyProperties(adminInfoVo, adminInfo);
        AdminInfo dbAdminInfo = getById(adminInfo.getAdminId());
        if (ObjectUtils.isEmpty(dbAdminInfo)) {
            throw new BusinessException("数据不存在");
        }
        /**判断用户名是否唯一索引**/
        if (!StringUtils.isEmpty(adminInfo.getAdminAccount())) {
            AdminInfo hasAccount = baseMapper.selectOne(new QueryWrapper<AdminInfo>().lambda()
                    .eq(AdminInfo::getAdminAccount, adminInfo.getAdminAccount()));
            if (!ObjectUtils.isEmpty(hasAccount) && !hasAccount.getAdminId().equals(dbAdminInfo.getAdminId())) {
                throw new BusinessException("用户名已存在");
            }

        }
        /**判断电话是否唯一索引**/
        if (!StringUtils.isEmpty(adminInfo.getAdminTel())) {
            AdminInfo hasTel = baseMapper.selectOne(new QueryWrapper<AdminInfo>().lambda()
                    .eq(AdminInfo::getAdminTel, adminInfo.getAdminTel()));
            if (!ObjectUtils.isEmpty(hasTel) && !hasTel.getAdminId().equals(dbAdminInfo.getAdminId())) {
                throw new BusinessException("电话已存在");
            }
        }
        return updateById(adminInfo);
    }

    @Override
    public IPage<AdminInfo> getAdminInfoPageByCon(PageVo<AdminInfoVo> vo) {
        AdminInfo adminInfo = new AdminInfo();
        if (!ObjectUtils.isEmpty(vo.getData())) {
            BeanUtils.copyProperties(vo.getData(), adminInfo);
        }
        Page<AdminInfo> page = new Page<>(vo.getIndex(), vo.getSize());
        //注意这里是对应的数据的字段名称,设置了通过时间降序排列
        page.setDesc("insert_dt");
        QueryWrapper<AdminInfo> wrapper = new QueryWrapper<>(adminInfo);
        return page(page, wrapper);
    }

    @Override
    @Transactional
    public boolean removeAdminInfoById(Integer id) {
        AdminInfo hasAdminInfo = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(hasAdminInfo)) {
            throw new BusinessException("数据不存在");
        }
        return removeById(id);
    }

    @Override
    public Page<AdminDemoDTO> getAdminDemoDTOPage(PageVo<AdminDemoDTO> pageVo) {
        Page<AdminDemoDTO> page = new Page<>(pageVo.getIndex(), pageVo.getSize());
        List<AdminDemoDTO> list = baseMapper.selectAdminDemoDTOPage(page);
        page.setRecords(list);
        return page;
    }
}
