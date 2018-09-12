package com.ga.cdz.domain.redis;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ChargingTypeRD {

    /**
     * 充电方式ID
     */
    private Integer cgtypeId;
    /**
     * 充电方式名称
     */
    private String cgtypeName;
    /**
     * 充电方式编码
     */
    private String cgtypeCode;
    /**
     * 充电方式状态 0删除  1正常
     */
    private Integer cgtypeState;
    /**
     * 插入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insretDt;

}
