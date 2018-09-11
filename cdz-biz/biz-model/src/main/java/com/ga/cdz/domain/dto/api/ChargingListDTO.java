package com.ga.cdz.domain.dto.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/11_16:56
 */
public class ChargingListDTO {


    class ChargingStation {
        /**
         * 充电站ID
         */
        @TableId(value = "station_id", type = IdType.AUTO)
        private Integer stationId;

        /**
         * 站名称
         */
        @TableField("station_name")
        private String stationName;

        /**
         * 设备数
         */
        @TableField("device_num")
        private Integer deviceNum;

        /**
         * 维度
         */
        @TableField("lat")
        private Double lat;

        /**
         * 经度
         */
        @TableField("lng")
        private Double lng;
    }

}
