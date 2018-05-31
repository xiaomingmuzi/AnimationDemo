package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * 动态列表、动态详情页中的投资锦囊bean
 * chenjianqiang
 * 2.0.8
 */

public class DynamicPlanBean implements Serializable {

    //发布这个投资锦囊的专家的头像
    private String dynamicPlanUserHeadUrl;
    //发布这个投资锦囊的专家的昵称
    private String dynamicPlanUserName;
    //投资锦囊总收益
    private String dynamicPlanTotalRevenue;
    //预期收益
    private String dynamicPlanExpectedRevenue;
    //方案名
    private String dynamicPlanName;
    //最长期限
    private String dynamicPlanMaxPeriod;
    //价格
    private String dynamicPlanPrice;
    //开始时间
    private String dynamicPlanStartTime;
    //投资锦囊的状态
    private String dynamicPlanStatus;
    //投资锦囊的ID
    private String dynamicPlanID;
    //发布投资锦囊的，专家本人的ID
    private String PlanExpertID;
    //综合搜索页面使用。用于记录综合搜索界面投资锦囊个数
    private int PlanNumber;

    public int getPlanNumber() {
        return PlanNumber;
    }

    public void setPlanNumber(int planNumber) {
        PlanNumber = planNumber;
    }

    public String getPlanExpertID() {
        return PlanExpertID;
    }

    public void setPlanExpertID(String planExpertID) {
        PlanExpertID = planExpertID;
    }

    public String getDynamicPlanID() {
        return dynamicPlanID;
    }

    public void setDynamicPlanID(String dynamicPlanID) {
        this.dynamicPlanID = dynamicPlanID;
    }

    public String getDynamicPlanUserHeadUrl() {
        return dynamicPlanUserHeadUrl;
    }

    public void setDynamicPlanUserHeadUrl(String dynamicPlanUserHeadUrl) {
        this.dynamicPlanUserHeadUrl = dynamicPlanUserHeadUrl;
    }

    public String getDynamicPlanUserName() {
        return dynamicPlanUserName;
    }

    public void setDynamicPlanUserName(String dynamicPlanUserName) {
        this.dynamicPlanUserName = dynamicPlanUserName;
    }

    public String getDynamicPlanTotalRevenue() {
        return dynamicPlanTotalRevenue;
    }

    public void setDynamicPlanTotalRevenue(String dynamicPlanTotalRevenue) {
        this.dynamicPlanTotalRevenue = dynamicPlanTotalRevenue;
    }

    public String getDynamicPlanExpectedRevenue() {
        return dynamicPlanExpectedRevenue;
    }

    public void setDynamicPlanExpectedRevenue(String dynamicPlanExpectedRevenue) {
        this.dynamicPlanExpectedRevenue = dynamicPlanExpectedRevenue;
    }

    public String getDynamicPlanName() {
        return dynamicPlanName;
    }

    public void setDynamicPlanName(String dynamicPlanName) {
        this.dynamicPlanName = dynamicPlanName;
    }

    public String getDynamicPlanMaxPeriod() {
        return dynamicPlanMaxPeriod;
    }

    public void setDynamicPlanMaxPeriod(String dynamicPlanMaxPeriod) {
        this.dynamicPlanMaxPeriod = dynamicPlanMaxPeriod;
    }

    public String getDynamicPlanPrice() {
        return dynamicPlanPrice;
    }

    public void setDynamicPlanPrice(String dynamicPlanPrice) {
        this.dynamicPlanPrice = dynamicPlanPrice;
    }

    public String getDynamicPlanStartTime() {
        return dynamicPlanStartTime;
    }

    public void setDynamicPlanStartTime(String dynamicPlanStartTime) {
        this.dynamicPlanStartTime = dynamicPlanStartTime;
    }

    public String getDynamicPlanStatus() {
        return dynamicPlanStatus;
    }

    public void setDynamicPlanStatus(String dynamicPlanStatus) {
        this.dynamicPlanStatus = dynamicPlanStatus;
    }
}
