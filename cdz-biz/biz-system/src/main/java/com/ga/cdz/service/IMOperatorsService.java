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
 * @description: 运营商信息服务接口
 * @date:2018/9/4_10:49
 * @since 2018-09-07
 */
public interface IMOperatorsService extends IService<Operators> {
    /**
     * @author:wanzhongsu
     * @description: 获取运营商列表
     * @date: 2018/9/10 9:31
     * @param:
     * @return:
     */
    List<Operators> getOperatorList();

    /**
     * @author:wanzhongsu
     * @description: 添加运营商账号及信息
     * @date: 2018/9/10 9:32
     * @param:
     * @return:
     */
    Integer saveOperator(OperatorsVo vo);

    /**
     * @author:wanzhongsu
     * @description: 逻辑删除运营商信息
     * @date: 2018/9/10 9:32
     * @param:
     * @return:
     */
    Integer removeOperator(OperatorsVo vo);

    /**
     * @author:wanzhongsu
     * @description: 下一个运营商编码
     * @date: 2018/9/10 9:32
     * @param:
     * @return:
     */
    String nextOperatorCode();
}
