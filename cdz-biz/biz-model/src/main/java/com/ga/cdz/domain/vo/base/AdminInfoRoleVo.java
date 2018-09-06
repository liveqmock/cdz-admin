package com.ga.cdz.domain.vo.base;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author:luqi
 * @description: AdminInfoRole Vo基类
 * @date:2018/9/6_13:42
 */
@Data
@Accessors(chain = true)
public class AdminInfoRoleVo {

    private Integer adminInfoRoleId;
    /**
     * admin关联ID
     */
    private Integer adminId;
    /**
     * role关联ID
     */
    private Integer roleId;
}
