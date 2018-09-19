package com.ga.cdz.domain.vo.api;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: liuyi
 * @Description: 订单界面的列表信息
 * @Date: 2018/9/19_11:11
 */
@Data
@Accessors
public class ChargingOrderPageListVo {

    /**
     * 页数
     */
    @NotNull(message = "pageIndex不能为空")
    private Integer pageIndex;

    /**
     * 一页的数据数
     */
    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;

    /**
     * 用户Id
     */
    @NotNull(message = "userId不能为空")
    private Integer userId;

}
