package com.ga.cdz.domain.dto.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ga.cdz.domain.entity.ChargingOrderComment;
import com.ga.cdz.domain.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: liuyi
 * @Description: 充电站评论
 * @Date: 2018/9/17_16:46
 */
@Data
@Accessors(chain = true)
public class ChargingStationCommentDTO {

    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 评分
     */
    private Integer commentScore;

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
     * 评论时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentDt;

    /**
     * 用户名称
     */
    private String userRealName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * @param chargingOrderComment
     * @return
     * @Author: liuyi
     * @Description: 赋值ChargingOrderComment
     * @Date: 2018/9/17_16:48
     */
    public void setChargingOrderComment(ChargingOrderComment chargingOrderComment) {
        this.commentId = chargingOrderComment.getCommentId();
        this.commentScore = chargingOrderComment.getCommentCode();
        this.commentContent = chargingOrderComment.getCommentContent();
        this.commentPid = chargingOrderComment.getCommentPid();
        this.commentType = chargingOrderComment.getCommentType();
        this.commentState = chargingOrderComment.getCommentCode();
        this.commentDt = chargingOrderComment.getInsertDt();
    }

}
