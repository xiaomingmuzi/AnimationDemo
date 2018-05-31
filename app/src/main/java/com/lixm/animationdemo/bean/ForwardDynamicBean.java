package com.lixm.animationdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * chenJianQiang
 * 动态中，转发部分的bean
 */
public class ForwardDynamicBean implements Serializable {

    private String f_NickName; // 转发人的昵称
    private String f_GambitID; // 转发的话题的ID
    private String f_Content; // 转发的内容
    private String f_IsFlag;  //  转发的类型标签
    private ArrayList<String> f_DynamicPic; // 图片的集合
    private String f_blogUrl;  //转发的链接
    private String f_PlanName;//转发的方案名字
    private String f_PlanId;//转发的方案ID
    private String f_PlanExpectedRevenue;//代表预期收益
    private LinkListBean f_LinkBean;//封装跳转对象
    private String f_IsDelete;//是否删除  1是已删除 0是未删除
    private String f_IsShield;//用户是否被屏蔽 0 没被屏蔽 1被屏蔽

    //1.4.0新加。是否订阅了理财师：1，没有，0有
    private String f_IsView;
    //1.4.0新加。直播室的编号
    private String f_LiveMsgID;
    //1.4.0新加。直播室编号
    private String f_LiveProductID;
    //1.4.0新加。直播状态
    private String f_Iszhib;
    //1.4.0新加。被转发的动态的发布者的ID
    private String f_UserID;

    //搜索界面，用于判断转发的是否是名片。有值，就是名片。为空，就不是
    private String VoiceContent;

    //1.5.0新加。用于判断动态类型：1,普通;2,语音;3,视频
    private String f_isType;

    //1.5.0新加。音频或视频的网络地址
    private String f_videoUrl;

    //1.5.0新加。音频时间长度
    private String f_voice_time_length;

    //1.5.0新加。视频预览图
    private String f_videoImg;

    //以下是1.6.2开始，新方案中所需字段
    /**
     * 投资锦囊（新方案）的ID
     */
    private String new_plan_id;
    /**
     * 投资锦囊（新方案）的名字
     */
    private String new_plan_name;
    /**
     * 投资锦囊（新方案）的开始时间
     */
    private String new_plan_start_time;
    /**
     * 投资锦囊（新方案）的预期收益
     */
    private String new_plan_expected_revenue;
    /**
     * 投资锦囊（新方案）的价格
     */
    private String new_plan_price;
    /**
     * 投资锦囊（新方案）的风险止损线
     */
    private String new_plan_risk_revenue;
    /**
     * 投资锦囊（新方案）的状态:1 进行中；2 已完成
     */
    private String new_plan_status;
    /**
     * 投资锦囊（新方案）的期限，单位为周
     */
    private String new_plan_time_limit;

    /**
     * 创建投资锦囊（新方案）的专家昵称
     */
    private String PlanNickName;
    //以上是1.6.2开始，新方案中所需字段

    //1.6.4开始，解析forward中，名片对象
    private String userCard_UserID;
    private String userCard_IconUrl;
    private String userCard_NickName;
    //简介
    private String userCard_Profile;

    //1.6.4添加，转发中，封装的语音对象
    private String f_VoiceUrl;
    private String f_VoiceLength;

    //1.6.4添加，转发中，封装的短视频对象
    private String f_VideoImgUrl;
    private String f_VideoUrl;
    private String f_VideoLength;

    //1.7.5添加。封装的发现轮播图进入网页后转发出来的网页对象
    private String FindShareImgUrl;
    private String FindShareUrl;
    private String FindShareTitle;

    //1.8.3添加，总收益
    private String PlanTotalRevenue;
    //1.8.3添加。发布投资锦囊的人的头像
    private String plan_avatar;

    //收费动态的产品id。2.0.4添加
    private String f_PriceDynamicProductID;
    //收费动态的价格。2.0.4添加
    private String f_DynamicPrice;
    //收费动态的动态列表展示内容。2.0.4添加
    private String f_PriceDynamicContent;
    //收费动态的购买关系。2.0.4添加。0代表买 1代表没买
    private String f_PriceDynamicIsBuy;
    private String f_PriceDynamicID;

    /**
     * 2.4.9版本新加。知识专栏提示类型动态使用。许春生
     * 动态对应的专栏的详情页的url
     */
    private String F_KnowledgeUrl;
    private String F_KnowledgeTitle;
    /**
     * 2.4.9版本新加。知识专栏动态购买状态
     * 0：不需要购买、已购买
     * 1：需要购买
     */
    private String F_KSBuyStatus;

    public String getF_KSBuyStatus() {
        return F_KSBuyStatus;
    }

    public void setF_KSBuyStatus(String f_KSBuyStatus) {
        F_KSBuyStatus = f_KSBuyStatus;
    }

    public String getF_KnowledgeUrl() {
        return F_KnowledgeUrl;
    }

    public void setF_KnowledgeUrl(String f_KnowledgeUrl) {
        F_KnowledgeUrl = f_KnowledgeUrl;
    }

    public String getF_KnowledgeTitle() {
        return F_KnowledgeTitle;
    }

    public void setF_KnowledgeTitle(String f_KnowledgeTitle) {
        F_KnowledgeTitle = f_KnowledgeTitle;
    }

    private DynamicPlanBean dynamicPlanBean;

    public DynamicPlanBean getDynamicPlanBean() {
        return dynamicPlanBean;
    }

    public void setDynamicPlanBean(DynamicPlanBean dynamicPlanBean) {
        this.dynamicPlanBean = dynamicPlanBean;
    }

    public String getF_PriceDynamicID() {
        return f_PriceDynamicID;
    }

    public void setF_PriceDynamicID(String f_PriceDynamicID) {
        this.f_PriceDynamicID = f_PriceDynamicID;
    }

    public String getF_PriceDynamicProductID() {
        return f_PriceDynamicProductID;
    }

    public void setF_PriceDynamicProductID(String f_PriceDynamicProductID) {
        this.f_PriceDynamicProductID = f_PriceDynamicProductID;
    }

    public String getF_DynamicPrice() {
        return f_DynamicPrice;
    }

    public void setF_DynamicPrice(String f_PriceDynamicPrice) {
        this.f_DynamicPrice = f_PriceDynamicPrice;
    }

    public String getF_PriceDynamicContent() {
        return f_PriceDynamicContent;
    }

    public void setF_PriceDynamicContent(String f_PriceDynamicContent) {
        this.f_PriceDynamicContent = f_PriceDynamicContent;
    }

    public String getF_PriceDynamicIsBuy() {
        return f_PriceDynamicIsBuy;
    }

    public void setF_PriceDynamicIsBuy(String f_PriceDynamicIsBuy) {
        this.f_PriceDynamicIsBuy = f_PriceDynamicIsBuy;
    }

    public String getPlan_avatar() {
        return plan_avatar;
    }

    public void setPlan_avatar(String plan_avatar) {
        this.plan_avatar = plan_avatar;
    }

    public String getPlanTotalRevenue() {
        return PlanTotalRevenue;
    }

    public void setPlanTotalRevenue(String planTotalRevenue) {
        PlanTotalRevenue = planTotalRevenue;
    }

    public String getFindShareImgUrl() {
        return FindShareImgUrl;
    }

    public void setFindShareImgUrl(String findShareImgUrl) {
        FindShareImgUrl = findShareImgUrl;
    }

    public String getFindShareUrl() {
        return FindShareUrl;
    }

    public void setFindShareUrl(String findShareUrl) {
        FindShareUrl = findShareUrl;
    }

    public String getFindShareTitle() {
        return FindShareTitle;
    }

    public void setFindShareTitle(String findShareTitle) {
        FindShareTitle = findShareTitle;
    }

    /**
     * 转发中动态图片对象集合
     */
    private ArrayList<DynamicPicBean> f_dynamicPicBeanList;

    public ArrayList<DynamicPicBean> getF_dynamicPicBeanList() {
        return f_dynamicPicBeanList;
    }

    public void setF_dynamicPicBeanList(ArrayList<DynamicPicBean> f_dynamicPicBeanList) {
        this.f_dynamicPicBeanList = f_dynamicPicBeanList;
    }

    public String getF_VoiceUrl() {
        return f_VoiceUrl;
    }

    public void setF_VoiceUrl(String f_VoiceUrl) {
        this.f_VoiceUrl = f_VoiceUrl;
    }

    public String getF_VoiceLength() {
        return f_VoiceLength;
    }

    public void setF_VoiceLength(String f_VoiceLength) {
        this.f_VoiceLength = f_VoiceLength;
    }

    public String getF_VideoImgUrl() {
        return f_VideoImgUrl;
    }

    public void setF_VideoImgUrl(String f_VideoImgUrl) {
        this.f_VideoImgUrl = f_VideoImgUrl;
    }

    public String getF_VideoUrl() {
        return f_VideoUrl;
    }

    public void setF_VideoUrl(String f_VideoUrl) {
        this.f_VideoUrl = f_VideoUrl;
    }

    public String getF_VideoLength() {
        return f_VideoLength;
    }

    public void setF_VideoLength(String f_VideoLength) {
        this.f_VideoLength = f_VideoLength;
    }

    public String getUserCard_UserID() {
        return userCard_UserID;
    }

    public void setUserCard_UserID(String userCard_UserID) {
        this.userCard_UserID = userCard_UserID;
    }

    public String getUserCard_IconUrl() {
        return userCard_IconUrl;
    }

    public void setUserCard_IconUrl(String userCard_IconUrl) {
        this.userCard_IconUrl = userCard_IconUrl;
    }

    public String getUserCard_NickName() {
        return userCard_NickName;
    }

    public void setUserCard_NickName(String userCard_NickName) {
        this.userCard_NickName = userCard_NickName;
    }

    public String getUserCard_Profile() {
        return userCard_Profile;
    }

    public void setUserCard_Profile(String userCard_Profile) {
        this.userCard_Profile = userCard_Profile;
    }
    //-------------------------------

    public String getPlanNickName() {
        return PlanNickName;
    }

    public void setPlanNickName(String planNickName) {
        PlanNickName = planNickName;
    }

    public String getNew_plan_id() {
        return new_plan_id;
    }

    public void setNew_plan_id(String new_plan_id) {
        this.new_plan_id = new_plan_id;
    }

    public String getNew_plan_name() {
        return new_plan_name;
    }

    public void setNew_plan_name(String new_plan_name) {
        this.new_plan_name = new_plan_name;
    }

    public String getNew_plan_start_time() {
        return new_plan_start_time;
    }

    public void setNew_plan_start_time(String new_plan_start_time) {
        this.new_plan_start_time = new_plan_start_time;
    }

    public String getNew_plan_expected_revenue() {
        return new_plan_expected_revenue;
    }

    public void setNew_plan_expected_revenue(String new_plan_expected_revenue) {
        this.new_plan_expected_revenue = new_plan_expected_revenue;
    }

    public String getNew_plan_price() {
        return new_plan_price;
    }

    public void setNew_plan_price(String new_plan_price) {
        this.new_plan_price = new_plan_price;
    }

    public String getNew_plan_risk_revenue() {
        return new_plan_risk_revenue;
    }

    public void setNew_plan_risk_revenue(String new_plan_risk_revenue) {
        this.new_plan_risk_revenue = new_plan_risk_revenue;
    }

    public String getNew_plan_status() {
        return new_plan_status;
    }

    public void setNew_plan_status(String new_plan_status) {
        this.new_plan_status = new_plan_status;
    }

    public String getNew_plan_time_limit() {
        return new_plan_time_limit;
    }

    public void setNew_plan_time_limit(String new_plan_time_limit) {
        this.new_plan_time_limit = new_plan_time_limit;
    }

    public String getF_videoImg() {
        return f_videoImg;
    }

    public void setF_videoImg(String f_videoImg) {
        this.f_videoImg = f_videoImg;
    }

    public String getF_voice_time_length() {
        return f_voice_time_length;
    }

    public void setF_voice_time_length(String f_voice_time_length) {
        this.f_voice_time_length = f_voice_time_length;
    }

    public String getF_isType() {
        return f_isType;
    }

    public void setF_isType(String f_isType) {
        this.f_isType = f_isType;
    }

    public String getF_videoUrl() {
        return f_videoUrl;
    }

    public void setF_videoUrl(String f_videoUrl) {
        this.f_videoUrl = f_videoUrl;
    }

    public String getVoiceContent() {
        return VoiceContent;
    }

    public void setVoiceContent(String voiceContent) {
        VoiceContent = voiceContent;
    }

    public String getF_LiveProductID() {
        return f_LiveProductID;
    }

    public void setF_LiveProductID(String f_LiveProductID) {
        this.f_LiveProductID = f_LiveProductID;
    }

    public String getF_Iszhib() {
        return f_Iszhib;
    }

    public void setF_Iszhib(String f_Iszhib) {
        this.f_Iszhib = f_Iszhib;
    }

    public String getF_UserID() {
        return f_UserID;
    }

    public void setF_UserID(String f_UserID) {
        this.f_UserID = f_UserID;
    }

    public String getF_LiveMsgID() {
        return f_LiveMsgID;
    }

    public void setF_LiveMsgID(String f_LiveMsgID) {
        this.f_LiveMsgID = f_LiveMsgID;
    }

    public String getF_IsView() {
        return f_IsView;
    }

    public void setF_IsView(String f_IsView) {
        this.f_IsView = f_IsView;
    }

    /**
     * 0 没被屏蔽 1被屏蔽
     *
     * @return
     */
    public String getF_IsShield() {
        return f_IsShield;
    }

    public void setF_IsShield(String isShield) {
        f_IsShield = isShield;
    }

    public LinkListBean getF_LinkBean() {
        return f_LinkBean;
    }

    public void setF_LinkBean(LinkListBean f_LinkBean) {
        this.f_LinkBean = f_LinkBean;
    }

    public String getF_PlanExpectedRevenue() {
        return f_PlanExpectedRevenue;
    }

    public void setF_PlanExpectedRevenue(String f_PlanExpectedRevenue) {
        this.f_PlanExpectedRevenue = f_PlanExpectedRevenue;
    }

    public String getF_PlanName() {
        return f_PlanName;
    }

    public void setF_PlanName(String f_PlanName) {
        this.f_PlanName = f_PlanName;
    }

    public String getF_PlanId() {
        return f_PlanId;
    }

    public void setF_PlanId(String f_PlanId) {
        this.f_PlanId = f_PlanId;
    }

    public String getF_blogUrl() {
        return f_blogUrl;
    }

    public void setF_blogUrl(String f_blogUrl) {
        this.f_blogUrl = f_blogUrl;
    }

    public String getF_IsFlag() {
        return f_IsFlag;
    }

    public void setF_IsFlag(String f_IsFlag) {
        this.f_IsFlag = f_IsFlag;
    }

    public String getF_NickName() {
        return f_NickName;
    }

    public void setF_NickName(String f_NickName) {
        this.f_NickName = f_NickName;
    }

    public String getF_GambitID() {
        return f_GambitID;
    }

    public void setF_GambitID(String f_GambitID) {
        this.f_GambitID = f_GambitID;
    }

    public String getF_Content() {
        return f_Content;
    }

    public void setF_Content(String f_Content) {
        this.f_Content = f_Content;
    }

    public ArrayList<String> getF_DynamicPic() {
        return f_DynamicPic;
    }

    public void setF_DynamicPic(ArrayList<String> f_DynamicPic) {
        this.f_DynamicPic = f_DynamicPic;
    }

    public String getF_IsDelete() {
        return f_IsDelete;
    }

    public void setF_IsDelete(String f_IsDelete) {
        this.f_IsDelete = f_IsDelete;
    }

    @Override
    public String toString() {
        return "ForwardDynamic{" +
                "f_NickName='" + f_NickName + '\'' +
                ", f_GambitID='" + f_GambitID + '\'' +
                ", f_Content='" + f_Content + '\'' +
                ", f_IsFlag='" + f_IsFlag + '\'' +
                ", f_DynamicPic=" + f_DynamicPic +
                ", f_blogUrl='" + f_blogUrl + '\'' +
                ", f_PlanName='" + f_PlanName + '\'' +
                ", f_PlanId='" + f_PlanId + '\'' +
                ", f_PlanExpectedRevenue='" + f_PlanExpectedRevenue + '\'' +
                ", f_LinkBean=" + f_LinkBean +
                ", f_IsDelete='" + f_IsDelete + '\'' +
                '}';
    }
}
