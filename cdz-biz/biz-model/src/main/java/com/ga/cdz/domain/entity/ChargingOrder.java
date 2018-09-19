package com.ga.cdz.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author lq
 * @since 2018-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_order")
public class ChargingOrder extends Model<ChargingOrder> {
    private static final long serialVersionUID = 1L;
    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.NONE)
    private String orderId;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 充电站ID
     */
    @TableField("station_id")
    private Integer stationId;
    /**
     * 充电桩ID
     */
    @TableField("device_id")
    private Integer deviceId;
    /**
     * 枪编号
     */
    @TableField("device_sub_id")
    private Integer deviceSubId;
    /**
     * 总价格
     */
    @TableField("total_price")
    private BigDecimal totalPrice;
    /**
     * 总能量
     */
    @TableField("total_energy")
    private Double totalEnergy;
    /**
     * 开始充电时间
     */
    @TableField("chargin_begin_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date charginBeginDt;
    /**
     * 结束充电时间
     */
    @TableField("chargin_end_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date charginEndDt;

    @TableField("order_state")
    private OrderState orderState;
    /**
     * 更新时间
     */
    @TableField("update_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDt;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

    /**
     * @author:luqi
     * @description: 订单枚举
     * @date:2018/9/12_10:40
     */
    public enum OrderState implements IEnum<Integer> {
        ERROR(-2, "异常"),
        REFUNDING(-1, "申请退款"),
        REMOVE(0, "删除"),
        INIT(1, "下单"),
        PAYING(2, "待支付"),
        PAYED(3, "支付，待评价"),
        FINISH(4, "完成"),
        REFUNDED(5, "退款已处理");

        private Integer code;
        private String desc;

        OrderState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.code;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
    }


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
