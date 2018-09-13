package com.ga.cdz.domain.vo.base;

import com.ga.cdz.domain.entity.UserCarsInfo;
import com.ga.cdz.domain.group.api.IUserCarsInfoGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author:luqi
 * @description: UserCars表vo
 * @date:2018/9/13_10:02
 */
@Data
@Accessors
public class UserCarsInfoVo {

    /**
     * 用户id
     */
    @NotNull(groups = {IUserCarsInfoGroup.Add.class, IUserCarsInfoGroup.Remove.class}, message = "用户id不能为空")
    private Integer userId;
    /**
     * 车辆号码
     */
    @NotBlank(groups = {IUserCarsInfoGroup.Add.class, IUserCarsInfoGroup.Remove.class}, message = "车牌号不能为空")
    @Size(groups = {IUserCarsInfoGroup.Add.class, IUserCarsInfoGroup.Remove.class}, min = 1, max = 20, message = "车牌号不合适")
    private String carNo;

    /**
     * 车辆名称
     */
    @NotBlank(groups = {IUserCarsInfoGroup.Add.class}, message = "车辆名称不能为空")
    @Size(groups = {IUserCarsInfoGroup.Add.class}, min = 1, max = 20, message = "车辆名称不合适")
    private String carName;

    /**
     * 车辆型号
     */
    @NotBlank(groups = {IUserCarsInfoGroup.Add.class}, message = "车辆型号不能为空")
    @Size(groups = {IUserCarsInfoGroup.Add.class}, min = 1, max = 20, message = "车辆型号不合适")
    private String carModel;

    /**
     * 发动机编号
     */
    @NotBlank(groups = {IUserCarsInfoGroup.Add.class}, message = "发动机编号不能为空")
    @Size(groups = {IUserCarsInfoGroup.Add.class}, min = 1, max = 32, message = "发动机编号不合适")
    private String carEngine;

    /**
     * 车架号
     */
    @NotBlank(groups = {IUserCarsInfoGroup.Add.class}, message = "车架号不能为空")
    @Size(groups = {IUserCarsInfoGroup.Add.class}, min = 1, max = 20, message = "车架号不合适")
    private String carVin;

    /**
     * 电池编号
     */
    @NotBlank(groups = {IUserCarsInfoGroup.Add.class}, message = "电池编号不能为空")
    @Size(groups = {IUserCarsInfoGroup.Add.class}, min = 1, max = 20, message = "电池编号不合适")
    private String carBattery;

    /**
     * 车的状态
     */
    private UserCarsInfo.CarState carState;

    /**
     * 更新时间
     */
    private Date updateDt;

    /**
     * 插入时间
     */
    private Date insertDt;

}
