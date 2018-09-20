package com.ga.cdz.domain.vo.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.group.admin.IMChargingShopGroup;
import com.ga.cdz.domain.group.admin.IMChargingStationGroup;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 商户管理vo
 * @date:2018/9/10 12:50
 */
@Data
@Accessors(chain = true)
public class ChargingShopVo {
    /**
     * 商户ID
     */
    @NotNull(groups = {IMChargingShopGroup.Delete.class, IMChargingStationGroup.Update.class}, message = "商户ID不能为空")
    private Integer shopId;
    /**
     * 商户编码
     */
    @NotBlank(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class}, message = "商户编码不能为空")
    @Pattern(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class}, regexp = RegexConstant.SHOP_CODE, message = "商户编码格式不对")
    private String shopCode;
    /**
     * 登录名
     */
    @NotBlank(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class, IMChargingShopGroup.login.class}, message = "登录名不能为空")
    private String shopLogin;
    /**
     * 商户名称
     */
    @NotBlank(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class}, message = "商户名称不能为空")
    private String shopName;
    /**
     * 商户联系人
     */
    @NotBlank(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class}, message = "联系人不能为空")
    private String shopContact;
    /**
     * 登录密码
     */
    @NotBlank(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.login.class}, message = "登录密码不能为空")
    private String shopPwd;
    /**
     * 商户联系电话
     */
    @NotBlank(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class}, message = "商户联系电话不能为空")
    @Pattern(groups = {IMChargingShopGroup.Add.class, IMChargingShopGroup.update.class}, regexp = RegexConstant.REGEX_PHONE, message = "电话格式不对")
    private String shopTel;
    /**
     * 商户状态 0 删除 1 正常
     */
    private ChargingShop.ShopState shopState;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDt;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;


}
