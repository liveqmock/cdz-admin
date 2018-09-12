package com.ga.cdz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lq
 * @since 2018-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charging_order_cmtattach")
public class ChargingOrderCmtattach extends Model<ChargingOrderCmtattach> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableField("comment_id")
    private Integer commentId;
    /**
     * 评论附件编号
     */
    @TableField("commentatc_idx")
    private Integer commentatcIdx;
    /**
     * 评论附件图片路径
     */
    @TableField("commentatc_path")
    private String commentatcPath;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
