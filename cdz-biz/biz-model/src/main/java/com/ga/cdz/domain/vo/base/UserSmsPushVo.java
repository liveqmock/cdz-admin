package com.ga.cdz.domain.vo.base;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author:luqi
 * @description: UserSmsPush 基类Vo
 * @date:2018/9/10_16:54
 */
@Data
@Accessors(chain = true)
public class UserSmsPushVo {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 设备标签
     */
    private String pushTag;

    /**
     * 设备别名
     */
    private String pushAlias;

    /**
     * 推送注册ID
     */
    private String pushRegid;

}
