package com.ga.cdz.domain.vo.api;

import com.ga.cdz.domain.entity.Banner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author: liuyi
 * @description: BannerVo
 * @date: 2018/9/11_13:48
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class BannerVo {
    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空")
    private Integer smsId;

    /**
     * 消息标题
     */
    @NotBlank(message = "消息标题不能为空")
    private String smsTitle;

    /**
     * 消息图片
     */
    @NotBlank(message = "消息图片不能为空")
    private String smsPic;

    /**
     * 消息URL
     */
    @NotBlank(message = "消息URL不能为空")
    private String smsUrl;

    /**
     * 消息类型 1 系统消息 2 广告banner
     */
    @NotNull(message = "消息类型不能为空")
    private Banner.SmsType smsType;

    /**
     * 更新时间
     */
    private LocalDateTime updateDt;

    /**
     * 插入时间
     */
    private LocalDateTime insertDt;
}
