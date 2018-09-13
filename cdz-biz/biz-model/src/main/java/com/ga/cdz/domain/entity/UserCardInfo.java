package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    /**
     * 车的状态
     */
    @TableField("card_state")
    private CardState cardState;
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


    /**
     * 车的状态枚举
     */
    public enum CardState implements IEnum<Integer> {
        REMOVE(0, "删除"),
        NORMAL(1, "正常");

        private Integer value;
        private String desc;

        CardState(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.value;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
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

