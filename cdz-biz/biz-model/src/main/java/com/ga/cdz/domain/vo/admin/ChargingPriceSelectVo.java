package com.ga.cdz.domain.vo.admin;

import com.ga.cdz.domain.entity.ChargingPrice;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author:wanzhongsu
 * @description: 查询返回的实体类
 * @date:2018/9/11 10:36
 */

@Data
@Accessors(chain = true)
public class ChargingPriceSelectVo {
    /**
     * 商户编码
     */
    private String shopCode;
    /**
     * 商户名称
     */
    private String shopName;
    /**
     * 站编码
     */
    private String stationCode;
    /**
     * 站名称
     */
    private String stationName;
    /**
     * 价格类型 1 专场计费 2 非专场计费
     */
    private ChargingPrice.PriceType priceType;
}
