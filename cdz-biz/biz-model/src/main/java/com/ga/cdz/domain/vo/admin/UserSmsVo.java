package com.ga.cdz.domain.vo.admin;


import com.ga.cdz.domain.entity.UserSms;
import com.ga.cdz.domain.group.admin.IMUserSmsGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class UserSmsVo {

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
    private LocalDateTime updateDt;
    /**
     * 插入时间
     */
    private LocalDateTime insertDt;

}
