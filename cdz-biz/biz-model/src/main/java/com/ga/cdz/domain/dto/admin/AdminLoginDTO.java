package com.ga.cdz.domain.dto.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author:luqi
 * @description: 后台登陆返回的实体类
 * @date:2018/9/5_16:17
 */
@Data
@Accessors(chain = true)
public class AdminLoginDTO {

    /**
     * adminId
     */
    private Integer adminId;

    /**
     * token
     */
    private String token;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 管理员的账号
     */
    private String adminAccount;

    /**
     * 权限code集合
     */
    private List<String> adminPermissionList;

}
