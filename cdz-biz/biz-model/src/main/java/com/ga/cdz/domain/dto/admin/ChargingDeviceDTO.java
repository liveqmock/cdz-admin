package com.ga.cdz.domain.dto.admin;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author huanghaohao
 * @date 2018-09-11 16:05
 * @desc 充电桩DTO
 */
@Data
@Accessors(chain = true)
public class ChargingDeviceDTO {
  /**
   * 充电桩ID
   */
  private Integer deviceId;
  /**
   * 充电站ID
   */

  private Integer stationId;
  /**
   * 充电桩编码
   */

  private String deviceCode;
  /**
   * 充电桩名称
   */

  private String deviceName;
  /**
   * 充电桩充电方式
   */

  private Integer cgtypeId;
  /**
   * 设备功率
   */

  private Integer devicePower;
  /**
   * 设备枪个数
   */

  private Integer deviceSubnum;
  /**
   * 设备状态
   */
  private Integer deviceState;
  /**
   * 更新时间
   */

  private Date updateDt;
  /**
   * 插入时间
   */

  private Date insertDt;

  /**
   * 充电方式名称
   */
  private String cgtypeName;
  /**
   * 充电方式编码
   */
  private String cgtypeCode;
  /**
   * 充电方式状态 0删除  1正常
   */

  private ChargingType.CgtypeState cgtypeState;
  /**
   * 插入时间
   */

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime insretDt;

}
