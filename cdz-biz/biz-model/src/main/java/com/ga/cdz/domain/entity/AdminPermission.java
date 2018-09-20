package com.ga.cdz.domain.entity;


import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:luqi
 * @description: 管理人员权限表
 * @date:2018/9/5_9:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_admin_permission")
public class AdminPermission extends Model<AdminPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 注意该字段不能自增
     */
    @TableId(value = "perm_id")
    private Integer permId;
    /**
     * 权限名称
     */
    @TableField("perm_name")
    private String permName;

    /**
     * 权限编码规则如下
     * 例如订单管理模块，下级权限命名不能包含父级权限的code，切记！！
     * order:module (模块权限)
     * order:create(创建表单)
     * order:Delete（删除表单）
     */
    @TableField("perm_code")
    private String permCode;
    /**
     * 父级ID
     */
    @TableField("perm_parent_id")
    private Integer permParentId;


    @Override
    protected Serializable pkVal() {
        return this.permId;
    }

}
