package com.ga.cdz.domain.dto.api;

import com.ga.cdz.domain.entity.ChargingOrderComment;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: liuyi
 * @Description: 充电站评论
 * @Date: 2018/9/17_16:46
 */
@Data
@Accessors
public class ChargingStationCommentDTO {

    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 评分
     */
    private Integer commentCode;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 上一级评论ID
     */
    private Integer commentPid;

    /**
     * 评论类型
     */
    private Integer commentType;

    /**
     * 评论状态
     */
    private Integer commentState;

    /**
     * 用户名称
     */
    private String userRealName;

    /**
     * @param chargingOrderComment
     * @return
     * @Author: liuyi
     * @Description: 赋值ChargingOrderComment
     * @Date: 2018/9/17_16:48
     */
    public void setChargingOrderComment(ChargingOrderComment chargingOrderComment) {
        this.commentId = chargingOrderComment.getCommentId();
        this.userId = chargingOrderComment.getUserId();
        this.commentCode = chargingOrderComment.getCommentCode();
        this.commentContent = chargingOrderComment.getCommentContent();
        this.commentPid = chargingOrderComment.getCommentPid();
        this.commentType = chargingOrderComment.getCommentType();
        this.commentState = chargingOrderComment.getCommentCode();
    }

}
