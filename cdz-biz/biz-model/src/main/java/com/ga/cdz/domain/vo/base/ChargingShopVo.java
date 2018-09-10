package com.ga.cdz.domain.vo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.ChargingShop;
import com.ga.cdz.domain.group.admin.IMChargingShopGroup;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @author:wanzhongsu
 * @description: 商户管理vo
 * @date:2018/9/10 12:50
 */
public class ChargingShopVo {
    /**
     * 商户ID
     */
    private Integer shopId;
    /**
     * 商户编码
     */
    @NotBlank(groups = {IMChargingShopGroup.add.class, IMChargingShopGroup.update.class}, message = "商户编码不能为空")
    @Pattern(groups = {IMChargingShopGroup.add.class, IMChargingShopGroup.update.class}, regexp = RegexConstant.SHOP_CODE, message = "商户编码格式不对")
    private String shopCode;
    /**
     * 商户名称
     */
    @NotBlank(groups = {IMChargingShopGroup.add.class, IMChargingShopGroup.update.class}, message = "商户名称不能为空")
    private String shopName;
    /**
     * 商户联系人
     */
    @NotBlank(groups = {IMChargingShopGroup.add.class, IMChargingShopGroup.update.class}, message = "联系人不能为空")
    private String shopContact;
    /**
     * 商户联系电话
     */
    @NotBlank(groups = {IMChargingShopGroup.add.class, IMChargingShopGroup.update.class}, message = "商户联系电话不能为空")
    @Pattern(groups = {IMChargingShopGroup.add.class, IMChargingShopGroup.update.class}, regexp = RegexConstant.REGEX_PHONE, message = "电话格式不对")
    private String shopTel;
    /**
     * 商户状态 0 删除 1 正常
     */
    private ChargingShop.ShopState shopState;
    /**
     * 更新时间
     */
    private LocalDateTime updateDt;
    /**
     * 插入时间
     */
    private LocalDateTime insertDt;


}
