package com.lixm.animationdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * chenJianQiang
 * 动态bean
 * 1.6.4新建
 */
public class DynamicBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String UserID;  // 动态里面用户的ID
    private String UserIconUrl;
    private String NickName;

    /**
     * 发表动态的用户名，唯一
     */
    private String UserName;

    //1是普通用户，2是认证专家
    private String Status;  // 专家认证
    //sourceType  1：普通用户：2 博客专家 ：3 ：僵尸粉；4：黄金专家；5自媒体
    private String SourceType;
    private String Target; //专家服务的项目

    private String DynamicID;  //动态的ID
    private String IconUrl; //用户的头像
    private String Content;  //内容
    private ArrayList<String> DynamicPic;  //动态的一组图片
    private String ForwardCount;  //转发数量
    private String PraiseCount;   //点赞数
    private String CommentCount;  // 评论数
    private String attent; // 是否关注  0
    private String isFlag;  //是否转发话题
    private String GambitID; // 话题的ID
    private String AddTime; //动态的添加时间

    private LinkListBean praisePersons; //点赞的人的集合

    private String znickname; // 被转发者的名字
    private String isprise;  //是否点赞  true为点赞
    private ForwardDynamicBean forwardDynamicBean;  // 转发的动态
    private String isFriend; //是否是好友

    private String BlogUrl;  //博客的链接
    private LinkListBean linkListBean;//个股和文字链接
    private String IsShield;//用户是否被屏蔽 0 没被屏蔽 1被屏蔽
    //1.4.0新加。是否订阅了理财师：1，没有，0有
    private String IsView;

    //1.4.0新加。直播室的编号
    private String LiveMsgID;

    //1.4.0新加。直播室的产品ID
    private String LiveProductID;

    //1.4.0新加。阅读量
    private String BrowseNum;

    //阅读数
    private int Read_Num = 0;

    /**
     * 0没有 , 1 点赞动画 2 取消点赞动画 3 动画结束 4请求成功 5请求失败
     */
    private int praiseAnimStatus;

    //直播状态
    private String Iszhib;
    //2.0.3新增视频的产品id，为实现直播室半开放订阅购买
    private String LivingProductID;

    //之前版本有，1.5.0中，如果是音频动态，它表示音频时间长度
    private String ArticleID;

    //1.5.0新加。用于判断动态类型：1,普通;2,语音;3,视频
    private String isType;

    //代表是否收藏。0代表收藏，1代表未收藏
    private String IsCollect;

    //代表是否是自媒体来源
    private String Source;

    //自媒体中，跳转所需的Url
    private String Url;

    //以下是1.6.2开始，自媒体视频中所需字段
    private String MediaPic;

    //1.6.3开始   查看自己的个人详情页需要  是否置顶。0是默认不置顶。1是置顶
    private String IsTop;

    /**
     * 视频直播间是否是正在直播状态
     */
    private String LiveVideoIsOpen;

    /**
     * 混合搜索中使用。记录有多少个动态
     */
    private int dynamicNumber;

    //1.6.4添加
    private String VoiceUrl;
    private String VoiceLength;

    //1.6.4添加
    private String VideoImgUrl;
    private String VideoUrl;
    private String VideoLength;

    //视频直播封面的url
    private String LiveVideoImgUrl;

    /**
     *1.6.6添加。视频直播的回放视频地址。有值，就是回放类型
     */
    private String VideoAddress;

    /**
     * 回放类型视频的观看数
     */
    private String WatchCount;

    //动态价格
    private String Price;

    //VIP皇冠的图片链接。2.2.4版本（2017/8/30）新加
    private String VipPic="";

    //话题列表页置顶条目专用。2.3.5（2018/1/5）新加
    private String isTopicTop;

    /**
     * 2.4.9版本新加。知识专栏提示类型动态使用。许春生
     * 动态对应的专栏的详情页的url
     */
    private String KnowledgeUrl;
    /**
     * 2.4.9版本新加。知识专栏提示类型动态使用。许春生
     * 动态对应的专栏的title
     */
    private String KnowledgeTitle;

    /**
     * 2.4.9版本新加。知识专栏动态购买状态
     * 0：不需要购买、已购买
     * 1：需要购买
     */
    private String KSBuyStatus;

    public String getKSBuyStatus() {
        return KSBuyStatus;
    }

    public void setKSBuyStatus(String KSBuyStatus) {
        this.KSBuyStatus = KSBuyStatus;
    }

    public String getKnowledgeUrl() {
        return KnowledgeUrl;
    }

    public void setKnowledgeUrl(String knowledgeUrl) {
        KnowledgeUrl = knowledgeUrl;
    }

    public String getKnowledgeTitle() {
        return KnowledgeTitle;
    }

    public void setKnowledgeTitle(String knowledgeTitle) {
        KnowledgeTitle = knowledgeTitle;
    }

    public String getIsTopicTop() {
        return isTopicTop;
    }

    public void setIsTopicTop(String isTopicTop) {
        this.isTopicTop = isTopicTop;
    }

    public String getVipPic() {
        return VipPic;
    }

    public void setVipPic(String vipPic) {
        VipPic = vipPic;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }


    public String getLivingProductID() {
        return LivingProductID;
    }

    public void setLivingProductID(String livingProductID) {
        LivingProductID = livingProductID;
    }

    public String getWatchCount() {
        return WatchCount;
    }

    public void setWatchCount(String watchCount) {
        WatchCount = watchCount;
    }

    /**
     * 动态图片对象集合
     */
    private ArrayList<DynamicPicBean> dynamicPicBeanList;

    /**
     * 专家是否在直播。1：视频直播中   2：图文直播中  3：没直播
     * 2.3.1添加
     */
    private String IsOpening;

    public String getIsOpening() {
        return IsOpening;
    }

    public void setIsOpening(String isOpening) {
        IsOpening = isOpening;
    }

    public String getVideoAddress() {
        return VideoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        VideoAddress = videoAddress;
    }

    public ArrayList<DynamicPicBean> getDynamicPicBeanList() {
        return dynamicPicBeanList;
    }

    public void setDynamicPicBeanList(ArrayList<DynamicPicBean> dynamicPicBeanList) {
        this.dynamicPicBeanList = dynamicPicBeanList;
    }

    public String getLiveVideoImgUrl() {
        return LiveVideoImgUrl;
    }

    public void setLiveVideoImgUrl(String liveVideoImgUrl) {
        LiveVideoImgUrl = liveVideoImgUrl;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserIconUrl() {
        return UserIconUrl;
    }

    public void setUserIconUrl(String userIconUrl) {
        UserIconUrl = userIconUrl;
    }

    public String getVideoLength() {
        return VideoLength;
    }

    public void setVideoLength(String videoLength) {
        VideoLength = videoLength;
    }

    public String getVideoImgUrl() {
        return VideoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        VideoImgUrl = videoImgUrl;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String getVoiceLength() {
        return VoiceLength;
    }

    public void setVoiceLength(String voiceLength) {
        VoiceLength = voiceLength;
    }

    public String getVoiceUrl() {
        return VoiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        VoiceUrl = voiceUrl;
    }

    public String getIsTop() {
        return IsTop;
    }

    public void setIsTop(String isTop) {
        IsTop = isTop;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    public String getMediaPic() {
        return MediaPic;
    }

    public void setMediaPic(String mediaPic) {
        MediaPic = mediaPic;
    }

    public int getDynamicNumber() {
        return dynamicNumber;
    }

    public void setDynamicNumber(int dynamicNumber) {
        this.dynamicNumber = dynamicNumber;
    }

    public String getLiveVideoIsOpen() {
        return LiveVideoIsOpen;
    }

    public void setLiveVideoIsOpen(String liveVideoIsOpen) {
        LiveVideoIsOpen = liveVideoIsOpen;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getIsCollect() {
        return IsCollect;
    }

    public void setIsCollect(String isCollect) {
        IsCollect = isCollect;
    }

    public String getArticleID() {
        return ArticleID;
    }

    public void setArticleID(String articleID) {
        ArticleID = articleID;
    }

    public String getIsType() {
        return isType;
    }

    public void setIsType(String isType) {
        this.isType = isType;
    }

    public int getRead_Num() {
        return Read_Num;
    }

    public void setRead_Num(int read_Num) {
        Read_Num = read_Num;
    }

    public String getBrowseNum() {
        return BrowseNum;
    }

    public void setBrowseNum(String browseNum) {
        BrowseNum = browseNum;
    }

//    public String getIszhib() {
//        return Iszhib;
//    }
//
//    public void setIszhib(String iszhib) {
//        Iszhib = iszhib;
//    }

    public String getLiveProductID() {
        return LiveProductID;
    }

    public void setLiveProductID(String liveProductID) {
        LiveProductID = liveProductID;
    }

    public String getLiveMsgID() {
        return LiveMsgID;
    }

    public void setLiveMsgID(String liveMsgID) {
        LiveMsgID = liveMsgID;
    }

    public String getIsView() {
        return IsView;
    }

    public void setIsView(String isView) {
        IsView = isView;
    }

    /**
     * 0 没被屏蔽 1被屏蔽
     *
     * @return
     */
    public String getIsShield() {
        return IsShield;
    }

    public void setIsShield(String isShield) {
        IsShield = isShield;
    }

    public LinkListBean getLinkListBean() {
        return linkListBean;
    }

    public void setLinkListBean(LinkListBean linkListBean) {
        this.linkListBean = linkListBean;
    }

    public String getBlogUrl() {
        return BlogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        BlogUrl = blogUrl;
    }

    public String getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(String isFriend) {
        this.isFriend = isFriend;
    }

    public ForwardDynamicBean getForwardDynamic() {
        return forwardDynamicBean;
    }

    public void setForwardDynamic(ForwardDynamicBean forwardDynamicBean) {
        this.forwardDynamicBean = forwardDynamicBean;
    }

    public String getIsprise() {
        return isprise;
    }

    public void setIsprise(String isprise) {
        this.isprise = isprise;
    }

    private ArrayList<CircleCommentBean> commentlist;  //评论的集合

    public ArrayList<CircleCommentBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(ArrayList<CircleCommentBean> commentlist) {
        this.commentlist = commentlist;
    }

    public String getZnickname() {
        return znickname;
    }

    public void setZnickname(String znickname) {
        this.znickname = znickname;
    }

    public String getDynamicID() {
        return DynamicID;
    }

    public void setDynamicID(String dynamicID) {
        DynamicID = dynamicID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public ArrayList<String> getDynamicPic() {
        return DynamicPic;
    }

    public void setDynamicPic(ArrayList<String> dynamicPic) {
        DynamicPic = dynamicPic;
    }

    public String getForwardCount() {
        return ForwardCount;
    }

    public void setForwardCount(String forwardCount) {
        ForwardCount = forwardCount;
    }

    public String getPraiseCount() {
        return PraiseCount;
    }

    public void setPraiseCount(String praiseCount) {
        PraiseCount = praiseCount;
    }

    public String getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(String commentCount) {
        CommentCount = commentCount;
    }

    public String getAttent() {
        return attent;
    }

    public void setAttent(String attent) {
        this.attent = attent;
    }

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag;
    }

    public String getGambitID() {
        return GambitID;
    }

    public void setGambitID(String gambitID) {
        GambitID = gambitID;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String addTime) {
        AddTime = addTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public LinkListBean getPraisePersons() {
        return praisePersons;
    }

    public void setPraisePersons(LinkListBean praisePersons) {
        this.praisePersons = praisePersons;
    }

}
