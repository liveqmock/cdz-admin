package com.ga.cdz.domain.entity;


import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员角色关联表
 * </p>
 *
 * @author lq
 * @since 2018-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_admin_info_role")
public class AdminInfoRole extends Model<AdminInfoRole> {

    private static final long serialVersionUID = 1L;

    /**
     * admin关联ID
     */
    @TableField("admin_id")
    private Integer adminId;
    /**
     * role关联ID
     */
    @TableField("role_id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
