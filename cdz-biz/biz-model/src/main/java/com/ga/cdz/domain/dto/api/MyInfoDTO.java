package com.ga.cdz.domain.dto.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * @author:luqi
 * @description: 我的界面 对应 dto
 * @date:2018/9/11_14:41
 */
@Data
@Accessors(chain = true)
public class MyInfoDTO {

    /**
     * userId
     */
    private Integer userId;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 用户价格
     */
    private BigDecimal userPrice;

    /**
     * 优惠券
     */
    private Integer coupon;

    /**
     * 积分
     */
    private Integer cardScore;

    /**
     * 碳减排
     */
    private BigDecimal carbon;

}
