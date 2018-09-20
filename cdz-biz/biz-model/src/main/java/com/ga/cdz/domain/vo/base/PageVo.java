package com.ga.cdz.domain.vo.base;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/**
 * @author:luqi
 * @description: 分页vo类
 * @date:2018/9/5_11:31
 */
@Data
@Accessors(chain = true)
public class PageVo<T> {
    @NotNull(message = "index不能为空")
    private Integer index;
    @NotNull(message = "size不能为空")
    private Integer size;
    private T data;
}
