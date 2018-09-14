package com.ga.cdz.domain.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UserMemberDTO {

  /**
   * 用户id
   */
  private Integer userId;
  /**
   * 类型 1个人用户 2 单位
   */
  private Integer userType;
  /**
   * 用户真实姓名
   */
  private String userRealName;
//  /**
//   * 密码
//   */
//  private String userPwd;
  /**
   * 电话
   */
  private String userTel;
  /**
   * 昵称
   */
  private String userNickName;
  /**
   * 性别 0 女 1男
   */
  private UserInfo.UserSex userSex;
  /**
   * 省级编码
   */
  private String province;
  /**
   * 城市编码
   */
  private String city;
  /**
   * 县编码
   */
  private String country;

  /**
   * 用户状态
   */
  private Integer userState;

  /**
   * 插入时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date insertDt;

  /**
   * 用户余额
   */
  private BigDecimal usedPrice;
  /**
   * 会员卡积分
   */

  private Integer cardScore;
  /**
   * 汽车数量
   */
  private Integer carNum;

}
