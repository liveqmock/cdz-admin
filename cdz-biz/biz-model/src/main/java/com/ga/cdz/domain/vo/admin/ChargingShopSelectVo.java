package com.ga.cdz.domain.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author:wanzhongsu
 * @description: 查询返回的实体类
 * @date:2018/9/11 10:36
 */

@Data
@Accessors(chain = true)
public class ChargingShopSelectVo {
    /**
     * 商户编码
     */
    private String shopCode;
    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 站名称
     */
    private String stationName;

    /**
     * 商户联系人
     */
    private String shopContact;
}
