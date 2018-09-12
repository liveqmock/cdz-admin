package com.ga.cdz.domain.entity;

import java.util.Date;
import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("t_charging_order_comment")
public class ChargingOrderComment extends Model<ChargingOrderComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;
    /**
     * 订单ID
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 评分
     */
    @TableField("comment_code")
    private Integer commentCode;
    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;
    /**
     * 上一级评论ID
     */
    @TableField("comment_pid")
    private Integer commentPid;
    /**
     * 评论类型
     */
    @TableField("comment_type")
    private Integer commentType;
    /**
     * 评论状态
     */
    @TableField("comment_state")
    private CommentState commentState;

    @TableField("update_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDt;
    /**
     * 插入时间
     */
    @TableField("insert_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDt;

    public enum CommentState implements IEnum<Integer> {
        REMOVE(0, "删除"),
        NORMAL(1, "正常");

        private Integer code;
        private String desc;

        CommentState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.code;
        }
    }


    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

}
