package com.ga.cdz.domain.vo.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghaohao
 * @date 2018-09-12 14:14
 * @desc 充电订单详情VO
 */

@Data
@Accessors(chain = true)
public class ChargingOrderSelectVo {

    private String orderId;
    /**
     * 用户ID
     */

    private Integer userId;
    /**
     * 充电站ID
     */

    private Integer stationId;

    /**
     * 充电站编号
     */
    private String stationCode;
    /**
     * 充电站名称
     */
    private String stationName;
    /**
     * 充电桩编码
     */
    private String deviceCode;
    /**
     * 充电桩名称
     */

    private String deviceName;
    /**
     * 充电类型
     */
    private String cgtypeName;
    /**
     * 充电价格
     */
    private Double chargingPrice;
    /**
     * 服务费
     */
    private Double servicePrice;

    /**
     * 充电桩ID
     */
    private Integer deviceId;
    /**
     * 枪编号
     */

    private Integer deviceSubId;
    /**
     * 总价格
     */

    private BigDecimal totalPrice;
    /**
     * 总能量
     */

    private Double totalEnergy;
    /**
     * 开始充电时间
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date charginBeginDt;
    /**
     * 结束充电时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date charginEndDt;


    private Integer orderState;
    /**
     * 更新时间
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDt;
    /**
     * 插入时间
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

    /**
     * 枪名称
     */
    private String deviceSubName;
    /**
     * 枪状态
     */
    private Integer deviceSubState;


}
