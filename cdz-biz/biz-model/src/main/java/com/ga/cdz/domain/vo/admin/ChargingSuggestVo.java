package com.ga.cdz.domain.vo.admin;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author:wanzhongsu
 * @description: 意见反馈vo
 * @date:2018/9/13 9:19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class ChargingSuggestVo {
    /**
     * 意见反馈ID
     */
    private Integer suggestId;
    /**
     * 意见反馈内容
     */
    private String suggestContent;
    /**
     * 充电站ID
     */
    private Integer stationId;
    /**
     * 充电桩ID
     */
    private Integer deviceId;
    /**
     * 插入时间
     */
    private Date insertDt;

}
