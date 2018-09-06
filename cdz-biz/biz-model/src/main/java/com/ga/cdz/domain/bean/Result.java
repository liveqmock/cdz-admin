package com.ga.cdz.domain.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.ga.cdz.domain.enums.ResultEnum;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author:luqi
 * @description:  请求回包
 * @date:2018/9/4_16:00
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@Accessors(fluent = true)
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result() {

    }

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public static Result success() {
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result fail() {
        return new Result(ResultEnum.FAIL);
    }

    public static Result unkonw() {
        return new Result(ResultEnum.UNKONW_ERROR);
    }

    public static Result custom() {
        return new Result();
    }
}
