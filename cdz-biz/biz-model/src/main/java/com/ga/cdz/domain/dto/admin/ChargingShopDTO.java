package com.ga.cdz.domain.dto.admin;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author:wanzhongsu
 * @description: 商户管理DTO
 * @date:2018/9/11 18:06
 */
@Data
@Accessors(chain = true)
public class ChargingShopDTO {
    /**
     * 商户ID
     */
    private Integer shopId;
    /**
     * 商户编码
     */
    private String shopCode;
    /**
     * 登录名
     */
    private String shopLogin;
    /**
     * 商户名称
     */
    private String shopName;
    /**
     * 商户联系人
     */
    private String shopContact;
    /**
     * 商户联系电话
     */
    private String shopTel;
    /**
     * 站名称
     */
    private String stationName;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

    /**
     * token
     */
    private String token;
}
