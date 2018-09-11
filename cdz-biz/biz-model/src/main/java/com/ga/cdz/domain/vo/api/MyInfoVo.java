package com.ga.cdz.domain.vo.api;

import com.ga.cdz.domain.group.api.IMyInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author:luqi
 * @description: 我的页面请求 vo
 * @date:2018/9/11_15:41
 */
@Data
@Accessors(chain = true)
public class MyInfoVo {

    /**
     * 用户id
     */
    @NotNull(groups = {IMyInfoGroup.Get.class}, message = "用户id不能为空")
    private Integer userId;
}
