package com.ga.cdz.domain.vo.admin;

import com.ga.cdz.domain.entity.UserCardInfo;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.group.admin.IMUserInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghaohao
 * @desc 会员卡信息Vo
 * @date 2018年9月9日 23点19分
 */
@Data
@Accessors(chain = true)
public class UserMemberCardInfoVo {

    /**
     * 会员卡id
     */
    @NotNull(groups = {IMUserInfoGroup.updateMemCardStat.class}, message = "更新card 状态时 ID 不能为空")
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
    private UserInfo.UserType userType;
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

    private Date updateDt;
    /**
     * 插入时间
     */
    private Date insertDt;

    /**
     * 汽车数量
     */

    private Integer carNum;
    /**
     * 卡状态
     */
    private UserCardInfo.CardState cardState;

}
