package com.ga.cdz.domain.dto.admin;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 关联分页查询demo DTO
 */
@Data
@Accessors(chain = true)
public class AdminDemoDTO {

    private Integer roleId;

    private String roleName;

    private Integer permId;

}
