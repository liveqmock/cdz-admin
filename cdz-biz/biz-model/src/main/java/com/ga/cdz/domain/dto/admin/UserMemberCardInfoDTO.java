package com.ga.cdz.domain.dto.admin;

import com.ga.cdz.domain.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author huanghaohao
 * @desc  会员卡信息DTO
 * @date 2018年9月9日 23点19分
 */
@Data
@Accessors(chain = true)
public class UserMemberCardInfoDTO {

  /**
   * 会员卡id
   */
  private Integer cardId;

  /**
   * 会员卡编码
   */

  private String cardCode;
  /**
   * 用户余额
   */

  private BigDecimal usedPrice;
  /**
   * 会员卡积分
   */

  private Integer cardScore;
  /**
   * 类型 1个人用户 2 单位
   */
  private Integer userType;
  /**
   * 用户真实姓名
   */
  private String userRealName;

  /**
   * 电话
   */
  private String userTel;

  /**
   * 性别 0 女 1男
   */
  private UserInfo.UserSex userSex;


  /**
   * 更新时间
   */

  private LocalDateTime updateDt;
  /**
   * 插入时间
   */
  private LocalDateTime insertDt;

  /**
   * 汽车数量
   */

  private Integer carNum;

  /**
   * 卡状态
   */
  private Integer cardState;

}