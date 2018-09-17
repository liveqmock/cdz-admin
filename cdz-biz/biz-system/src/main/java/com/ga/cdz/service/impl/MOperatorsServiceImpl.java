package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.OperatorsMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.vo.base.OperatorsVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMOperatorsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 运营商信息服务接口实现类
 * @date:2018/9/10 9:35
 */
@Service("mOperatorsService")
public class MOperatorsServiceImpl extends ServiceImpl<OperatorsMapper, Operators> implements IMOperatorsService {

    @Override
    public IPage<Operators> getOperatorPage(PageVo<OperatorsVo> vo) {
        IPage page = new Page(vo.getIndex(), vo.getSize());
        Operators operators = new Operators();
        BeanUtils.copyProperties(vo.getData(), operators);
        LambdaQueryWrapper<Operators> wrapper = new QueryWrapper<Operators>().lambda().eq(Operators::getOperatorsState, Operators.OperatorsState.NORMAL)
                .like(Operators::getOperatorsName, operators.getOperatorsName()).like(Operators::getOperatorsCode, operators.getOperatorsCode())
                .orderByAsc(Operators::getOperatorsId);
        page = baseMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public List<Operators> getOperatorsListByName(OperatorsVo vo) {
        List<Operators> operators = baseMapper.selectList(new QueryWrapper<Operators>().lambda().like(Operators::getOperatorsName, vo.getOperatorsName()));
        return operators;
    }

    @Transactional
    @Override
    public Integer saveOperator(OperatorsVo vo) {
        Operators operators = new Operators();
        BeanUtils.copyProperties(vo, operators);
        //运营商名字是否已存在与数据库中
        Operators hasName = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().eq(Operators::getOperatorsState, Operators.OperatorsState.NORMAL).eq(Operators::getOperatorsName, operators.getOperatorsName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("运营商名称已存在");
        }
        //已存在，修改状态后保存
        hasName = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().eq(Operators::getOperatorsName, operators.getOperatorsName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            hasName.setOperatorsState(Operators.OperatorsState.NORMAL);
            updateById(hasName);
            return 1;
        }
        //运营商编码是否已存在与数据库中
        Operators hasCode = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().eq(Operators::getOperatorsCode, operators.getOperatorsCode()));
        if (!ObjectUtils.isEmpty(hasCode) && hasName.getOperatorsState() == Operators.OperatorsState.NORMAL) {
            throw new BusinessException("运营商编码已存在");
        }
        /**初始化运营商状态为可用*/
        operators.setOperatorsState(Operators.OperatorsState.NORMAL);
        //保存运营商信息
        int result = baseMapper.insert(operators);
        return result;
    }

    @Transactional
    @Override
    public Integer removeOperator(OperatorsVo vo) {
        Operators delete = getById(vo.getOperatorsId());
        delete.setOperatorsState(Operators.OperatorsState.DELETE);
        updateById(delete);
        return 1;
    }

    @Override
    public String nextOperatorCode() {
        //查询当前最大的运营商编码
        Operators operators = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().orderByDesc(Operators::getOperatorsCode).last("limit 1"));
        String nextCode = null;
        //获取下一个运营商编码
        if (ObjectUtils.isEmpty(operators)) {
            nextCode = "00";
        } else {
            nextCode = operators.getOperatorsCode();
            int value = Integer.parseInt(nextCode) + 1;
            if (value < 10) {
                nextCode = "0" + String.valueOf(value);
            } else {
                nextCode = String.valueOf(value);
            }
        }
        return nextCode;
    }
}
