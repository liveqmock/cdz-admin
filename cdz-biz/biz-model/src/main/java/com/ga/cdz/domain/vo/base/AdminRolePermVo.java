package com.ga.cdz.domain.vo.base;

import com.ga.cdz.domain.group.admin.IMAdminRoleGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huanghaohao
 * @date 2018年9月6日 17点01分
 * @desc
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class AdminRolePermVo {

    /**
     * 管理员角色ID
     */
    @NotNull(groups = {IMAdminRoleGroup.Update.class}, message = "角色ID 不能为空")
    private Integer roleId;
    /**
     * 管理员角色名称
     */
    private String roleName;

    /**
     *  该角色的权限信息
     * */
    /**
     * 权限ID
     */

    @NotNull(groups = {IMAdminRoleGroup.Update.class}, message = "权限ID 不能为空")
    private Integer permId;
    /**
     * 权限名称
     */
    private String permName;
    /**
     * 权限父Id
     */
    private Integer permParentId;
    /**
     * 权限 有效性
     */
    private Boolean isValid;

    /**
     * 子权限 集合
     */
    private List child;


}
