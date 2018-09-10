package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author huanghaohao
 * @date 2018-09-10 09:19
 * @desc userCarEntity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_card")
public class UserCardInfo extends Model<UserCardInfo> {
  private static final long serialVersionUID = 1L;

  /**
   * 会员卡id
   */
  @TableId(value = "card_id", type = IdType.AUTO)
  private Integer cardId;
  /**
   * 用户id
   */
  @TableField("user_id")
  private Integer userId;
  /**
   * 会员卡编码
   */
  @TableField("card_code")
  private String cardCode;
  /**
   * 用户余额
   */
  @TableField("used_price")
  private BigDecimal usedPrice;
  /**
   * 会员卡积分
   */
  @TableField("card_score")
  private Integer cardScore;
  @TableField("card_state")
  private Integer cardState;
  /**
   * 更新时间
   */
  @TableField("update_dt")
  private Date updateDt;
  /**
   * 插入时间
   */
  @TableField("insert_dt")
  private Date insertDt;

  @Override
  protected Serializable pkVal() {
    return this.cardId;
  }

  @Override
  public String toString() {
    return "UserCard{" +
            ", cardId=" + cardId +
            ", userId=" + userId +
            ", cardCode=" + cardCode +
            ", usedPrice=" + usedPrice +
            ", cardScore=" + cardScore +
            ", cardState=" + cardState +
            ", updateDt=" + updateDt +
            ", insertDt=" + insertDt +
            "}";
  }
}

