package com.ga.cdz.domain.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserMemberCarsInfoDTO {

  /**
   * 车id
   */
  private String carId;

  /**
   * 车编码
   */

  private String carCode;
  /**
   * 用户ID
   */

  private Integer userId;
  /**
   * 车辆型号
   */
  private String carName;
  /**
   * 车辆型号
   */
  private String carModel;
  /**
   * 电池编号
   */
  private String carBattery;
  /**
   * 点卡编码
   */
  private String cardCode;

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


}
