package com.ga.cdz.domain.vo.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ga.cdz.domain.group.admin.IMChargingDeviceGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author huanghaohao
 * @date 2018-09-11 14:16
 * @desc 充电桩Vo
 */
@Data
@Accessors(chain = true)
public class ChargingDeviceVo {
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
}
