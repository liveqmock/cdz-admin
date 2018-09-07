package com.ga.cdz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.vo.base.OperatorsVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wanzhs
 * @since 2018-09-07
 */
public interface IMOperatorsService extends IService<Operators> {
    List<Operators> getOperatorList();

    Integer saveOperator(OperatorsVo vo);

    Integer removeOperator(OperatorsVo vo);

    String nextOperatorCode();
}
