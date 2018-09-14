package com.ga.cdz.domain.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author:wanzhongsu
 * @description: 意见反馈条件查询vo
 * @date:2018/9/12 20:20
 */
@Data
@Accessors(chain = true)
public class ChargingSuggestSelectVo {
    /**
     * 站编码
     */
    private String stationCode;
    /**
     * 站名称
     */
    private String stationName;
    /**
     * 充电桩编码
     */
    private String deviceCode;
    /**
     * 充电桩名称
     */
    private String deviceName;
    /**
     * 充电方式名称
     */
    private String cgtypeName;
    /**
     * 设备功率
     */
    private Integer devicePower;
    /**
     * 意见反馈内容
     */
    private String suggestContent;
    /**
     * 意见反馈附件路径
     */
    private String suggestPath;
}
