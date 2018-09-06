package com.ga.cdz.domain.dto.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author huanghaohao
 * @date 2018年9月6日 17点01分
 * @desc
 */

@Data
@Accessors(chain = true)
public class AdminRolePermDTO {

  /**
   * 管理员角色ID
   */
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
