package com.ga.cdz.domain.dto.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: liuyi
 * @description: 充电站分页列表
 * @date: 2018/9/11_16:56
 */
@Data
@Accessors(chain = true)
public class ChargingStationPageDTO implements Comparable<ChargingStationPageDTO> {

    /**
     * 充电站ID
     */
    private Integer stationId;

    /**
     * 站名称
     */
    private String stationName;

    /**
     * 维度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 距离----距离用户的距离
     */
    private Double distance;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 当前价格
     */
    private Double nowPrice;

    /**
     * 图片
     */
    private String picUrl;

    /**
     * 标签
     */
    private String tags;

    /**
     * 正在使用的快充
     */
    private Integer fastNum;

    /**
     * 正在使用的慢充
     */
    Integer slowNum;

    /**
     * 快充总数
     */
    Integer fastTotal;

    /**
     * 慢冲总数
     */
    Integer slowTotal;

    /**
     * sttpeId
     */
    private Integer sttpeId;


    @Override
    public int compareTo(ChargingStationPageDTO dto) {
        if (Double.compare(this.distance, dto.getDistance()) == 1) {
            return 1;
        } else if (Double.compare(this.distance, dto.getDistance()) == -1) {
            return -1;
        } else {
            return 0;
        }
    }
}
