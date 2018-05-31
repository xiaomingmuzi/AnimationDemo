package com.lixm.animationdemo.bean;

import java.io.Serializable;

/***
 * @author Sandy
 * @ClassName: CirclrCommentBean
 * @Description: 理财圈里面的 评论的实体
 * @date 2015-10-12 下午4:46:39
 */
public class CircleCommentBean implements Serializable {

    private String content;  // 评论的内容
    private String CommentID;//评论编号

    private String reviewerA; // 评论者A的昵称
    private String reviewerAID;  //评论者的id
    private String IconUrl;// 评论者A的头像
    //1普通2专家3超管
    private String Status;// 评论者A的状态

    private String reviewerTO;  //回复评论A的人   评论者B
    private String reviewerBID;//回复评论的B的id
    private String ToIconUrl;
    private String ToStatus;

    private String AddTime;//评论的时间

    private LinkListBean linkListBean;//个股和文字链接

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getToIconUrl() {
        return ToIconUrl;
    }

    public void setToIconUrl(String toIconUrl) {
        ToIconUrl = toIconUrl;
    }

    public String getToStatus() {
        return ToStatus;
    }

    public void setToStatus(String toStatus) {
        ToStatus = toStatus;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String addTime) {
        AddTime = addTime;
    }

    public LinkListBean getLinkListBean() {
        return linkListBean;
    }

    public void setLinkListBean(LinkListBean linkListBean) {
        this.linkListBean = linkListBean;
    }

    public String getCommentID() {
        return CommentID;
    }

    public void setCommentID(String commentID) {
        CommentID = commentID;
    }

    public String getReviewerAID() {
        return reviewerAID;
    }

    public void setReviewerAID(String reviewerAID) {
        this.reviewerAID = reviewerAID;
    }

    public String getReviewerA() {
        return reviewerA;
    }

    public void setReviewerA(String reviewerA) {
        this.reviewerA = reviewerA;
    }

    public String getReviewerTO() {
        return reviewerTO;
    }

    public void setReviewerTO(String reviewerTO) {
        this.reviewerTO = reviewerTO;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReviewerBID() {
        return reviewerBID;
    }

    public void setReviewerBID(String reviewerBID) {
        this.reviewerBID = reviewerBID;
    }

    @Override
    public String toString() {
        return "CircleCommentBean{" +
                "reviewerA='" + reviewerA + '\'' +
                ", reviewerTO='" + reviewerTO + '\'' +
                ", reviewerBID='" + reviewerBID + '\'' +
                ", content='" + content + '\'' +
                ", reviewerAID='" + reviewerAID + '\'' +
                '}';
    }
}
