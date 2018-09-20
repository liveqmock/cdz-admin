package com.ga.cdz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.vo.base.OperatorsVo;
import com.ga.cdz.domain.vo.base.PageVo;

import java.util.List;

/**
 * @author wanzhongsu
 * @description: 运营商信息服务接口
 * @date:2018/9/4_10:49
 */
public interface IMOperatorsService extends IService<Operators> {
    /**
     * @author:wanzhongsu
     * @description: 获取运营商列表
     * @date: 2018/9/10 9:31
     * @param: PageVo
     * @return: IPage
     */
    IPage<Operators> getOperatorPage(PageVo<OperatorsVo> vo);

    /**
     * @author:wanzhongsu
     * @description: 根据名称模糊查询运营商ID
     * @date: 2018/9/12 10:03
     * @param: OperatorsVo
     * @return: List
     */
    List<Operators> getOperatorsListByName(OperatorsVo vo);

    /**
     * @author:wanzhongsu
     * @description: 添加运营商账号及信息
     * @date: 2018/9/10 9:32
     * @param: OperatorsVo
     * @return: 返回整数 小于等于0 不成功，大于0 成功
     */
    void saveOperator(OperatorsVo vo);

    /**
     * @author:wanzhongsu
     * @description: 逻辑删除运营商信息
     * @date: 2018/9/10 9:32
     * @param: OperatorsVo
     * @return: 返回整数 小于等于0 不成功，大于0 成功
     */
    void removeOperator(OperatorsVo vo);
}
