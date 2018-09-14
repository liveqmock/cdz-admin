package com.ga.cdz.domain.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author:hanghaohao
 * @description: 订单评论DTO
 * @date:2018/9/14 14:04
 */
@Data
@Accessors
public class ChargingOrderCommentDTO {
  /**
   * 评论ID
   */
  private Integer commentId;
  /**
   * 订单ID
   */
  private String orderId;
  /**
   * 用户ID
   */
  private Integer userId;
  /**
   * 评分
   */
  private Integer commentCode;
  /**
   * 评论内容
   */
  private String commentContent;
  /**
   * 上一级评论ID
   */
  private Integer commentPid;
  /**
   * 评论类型
   */

  private Integer commentType;
  /**
   * 评论状态
   */

  private Integer commentState;

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
   * 用户名称
   */

  private String  userRealName;
  /**
   * 站名
   */
  private String  stationName;
  /**
   * 站码
   */

  private  String stationCode;

  /**
   * 桩码
   */
  private String  deviceCode;
  /**
   * 桩名
   */
  private String deviceName;
  /**
   * 枪码
   */
  private  Integer deviceSubId;

  /**
   * 枪名
   */
  private String  deviceSubName;

  /**
   * 充电类型
   */
  private  String  cgtypeName;


}
