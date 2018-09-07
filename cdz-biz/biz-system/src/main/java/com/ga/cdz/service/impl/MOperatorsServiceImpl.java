package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.OperatorsMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.vo.base.OperatorsVo;
import com.ga.cdz.service.IMOperatorsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
 */
@Service("mOperatorsService")
public class MOperatorsServiceImpl extends ServiceImpl<OperatorsMapper, Operators> implements IMOperatorsService {

    @Override
    public List<Operators> getOperatorList() {
        List<Operators> operators = baseMapper.selectList(new QueryWrapper<Operators>().orderByAsc("operators_id"));
        return operators;
    }

    @Override
    public Integer saveOperator(OperatorsVo vo) {
        Operators operators = new Operators();
        BeanUtils.copyProperties(vo, operators);
        Operators hasName = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().eq(Operators::getOperatorsName, operators.getOperatorsName()));
        if (!ObjectUtils.isEmpty(hasName)) {
            throw new BusinessException("运营商名称已存在");
        }
        Operators hasCode = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().eq(Operators::getOperatorsCode, operators.getOperatorsCode()));
        if (!ObjectUtils.isEmpty(hasCode)) {
            throw new BusinessException("运营商编码已存在");
        }
        int result = baseMapper.insert(operators);
        return result;
    }

    @Override
    public Integer removeOperator(OperatorsVo vo) {
        int result = baseMapper.deleteById(vo.getOperatorsId());
        return result;
    }

    @Override
    public String nextOperatorCode() {
        Operators operators = baseMapper.selectOne(new QueryWrapper<Operators>().lambda().orderByDesc(Operators::getOperatorsCode).last("limit 1"));
        String nextCode = null;
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
