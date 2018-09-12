package com.ga.cdz.domain.dto.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
public class ChargingDeviceSubDTO {

  /**
   * 枪编号
   */
  private Integer deviceSubId;
  /**
   * 充电桩ID
   */
  private Integer deviceId;
  /**
   * 枪名称
   */

  private String deviceSubName;
  /**
   * 0故障，1空闲，2占用
   */
  private Integer deviceSubState;

  /**
   * 订单Id
   */
  private String orderId;
  /**
   * 充电开始时间
   */
  private Date charginBeginDt;

  /**
   * 用户名称
   */
  private String userRealName;
  /**
   * 用户ID
   */
  private Integer userId;
  /**
   * 更新时间
   */
  private Date updateDt;
  /**
   * 插入时间
   */

  private Date insertDt;

}
