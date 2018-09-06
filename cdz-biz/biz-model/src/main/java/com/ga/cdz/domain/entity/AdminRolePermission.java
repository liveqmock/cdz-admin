package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author lq
 * @since 2018-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_admin_role_permission")
public class AdminRolePermission extends Model<AdminRolePermission> {

    private static final long serialVersionUID = 1L;


    /**
     * 角色关联ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 权限关联ID
     */
    @TableField("perm_id")
    private Integer permId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
