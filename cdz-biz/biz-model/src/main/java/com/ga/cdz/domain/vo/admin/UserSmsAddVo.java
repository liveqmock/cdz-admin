package com.ga.cdz.domain.vo.admin;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.UserSms;
import com.ga.cdz.domain.group.admin.IMUserSmsGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 消息表
 * @date:2018/9/14 14:01
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class UserSmsAddVo {

    /**
     * 消息ID
     */
    @NotNull(groups = IMUserSmsGroup.Delete.class, message = "消息ID不能为空")
    private Integer smsId;
    /**
     * 消息标题
     */
    @NotBlank(groups = IMUserSmsGroup.Add.class, message = "消息标题不能为空")
    private String smsTitle;
    /**
     * 消息图片
     */
    private String smsPic;
    /**
     * 消息URL
     */
    @NotBlank(groups = IMUserSmsGroup.Add.class, message = "消息内容不能为空")
    private String smsUrl;
    /**
     * 消息类型 1系统消息 2 广告banner
     */
    private UserSms.SmsType smsType;
    /**
     * 消息类型整数值
     */
    private Integer SmsInt;
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
