package com.lixm.animationdemo.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lixm.animationdemo.bean.CircleCommentBean;
import com.lixm.animationdemo.bean.DynamicBean;
import com.lixm.animationdemo.bean.ForwardDynamicBean;
import com.lixm.animationdemo.bean.LinkBaseBean;
import com.lixm.animationdemo.bean.LinkListBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lixm
 * @date 2018/5/31 15:30
 * @detail 动态数据解析
 */

public class DynamicTypeAdapter extends TypeAdapter<DynamicNewBean> {

    //1、动态列表；2、动态详情页；3、搜藏；4；搜索
    int sourceType = 0;

    public DynamicTypeAdapter(int sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public void write(JsonWriter out, DynamicNewBean value) throws IOException {

    }

    @Override
    public DynamicNewBean read(JsonReader in) throws IOException {
        DynamicNewBean dynamicNewBean = new DynamicNewBean();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "result":
                    dynamicNewBean.setResult(in.nextString());
                    break;
                case "msg":
                    dynamicNewBean.setMsg(in.nextString());
                    break;
                case "MerList":
                    dynamicNewBean.setMerList(readBeans(in));
                    break;
            }
        }
        in.endObject();
        return dynamicNewBean;
    }

    public List<DynamicNewBean.MerListBean> readBeans(JsonReader in) throws IOException {
        List<DynamicNewBean.MerListBean> listBeans = new ArrayList<>();
        DynamicNewBean.MerListBean merListBean = new DynamicNewBean.MerListBean();
        merListBean.setDynamic(readDynamicBeans(in));
        return listBeans;
    }

    public List<DynamicBean> readDynamicBeans(JsonReader in) throws IOException {
        List<DynamicBean> dynamicBeans = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            if (in.nextName().equals("Dynamic"))
                dynamicBeans.add(readDynamicBean(in));
        }
        in.endArray();
        return dynamicBeans;
    }

    public DynamicBean readDynamicBean(JsonReader in) throws IOException {
        DynamicBean dynamicBean = new DynamicBean();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "SourceType":
                    dynamicBean.setSourceType(in.nextString());
                    break;
                case "UserID":
                    dynamicBean.setUserID(in.nextString());
                    break;
                case "UserIconUrl":
                    dynamicBean.setUserIconUrl(in.nextString());
                    break;
                case "NickName":
                    dynamicBean.setNickName(in.nextString());
                    break;
                case "UserName":
                    dynamicBean.setUserName(in.nextString());
                    break;
                case "Status":
                    dynamicBean.setStatus(in.nextString());
                    break;
                case "VipPic":
                    dynamicBean.setVipPic(in.nextString());
                    break;
                case "Target":
                    dynamicBean.setTarget(in.nextString());
                    break;
                case "isType":
                    dynamicBean.setIsType(in.nextString());
                    break;
                case "IsFlag":
                    dynamicBean.setIsFlag(in.nextString());
                    break;
                case "DynamicID":
                    dynamicBean.setDynamicID(in.nextString());
                    break;
                case "AddTime":
                    dynamicBean.setAddTime(in.nextString());
                    break;
                case "LivingProductID":
                    dynamicBean.setLivingProductID(in.nextString());
                    break;
                case "IsTop":
                    dynamicBean.setIsTop(in.nextString());
                    break;
                case "IsOpening":
                    dynamicBean.setIsOpening(in.nextString());
                    break;
                case "CommentCount":
                    dynamicBean.setCommentCount(in.nextString());
                    break;
                case "PraiseCount":
                    dynamicBean.setPraiseCount(in.nextString());
                    break;
                case "ForwardCount":
                    dynamicBean.setForwardCount(in.nextString());
                    break;
                case "Commentlist":
                    dynamicBean.setCommentlist(readCommentBean(in));
                    break;
//                case "TopicID":
//                    dynamicBean.setto(in.nextString());
//                    break;
                case "Isprise":
                    dynamicBean.setIsprise(in.nextString());
                    break;
                case "Content":
                    dynamicBean.setContent(in.nextString());
                    break;
//                case "Title":
//                    dynamicBean.sett(in.nextString());
//                    break;
                case "BlogUrl":
                    dynamicBean.setBlogUrl(in.nextString());
                    break;
                case "DynamicPic":
                    dynamicBean.setDynamicPic(readPic(in));
                    break;
//                case "Iszhib":
//                    dynamicBean.z(in.nextString());
//                    break;
                case "Forward"://空时为数组
                    dynamicBean.setForwardDynamic(readForward(in));
                    break;
                case "IsView":
                    dynamicBean.setIsView(in.nextString());
                    break;
                case "LiveProductID":
                    dynamicBean.setLivingProductID(in.nextString());
                    break;
                case "IsShield":
                    dynamicBean.setIsShield(in.nextString());
                    break;
                case "Media"://空时为数组
                    dynamicBean.setMediaPic(in.nextString());
                    break;
                case "Voice":
                    in.beginObject();
                    dynamicBean.setVoiceUrl(in.nextString());
                    dynamicBean.setVoiceLength(in.nextString());
                    in.endObject();
                    break;
                case "Video":
                    in.beginObject();
                    dynamicBean.setVideoImgUrl(in.nextString());
                    dynamicBean.setVideoUrl(in.nextString());
                    dynamicBean.setVideoLength(in.nextString());
                    in.endObject();
                    break;
                case "Knowledge":
                    in.beginObject();
                    dynamicBean.setKnowledgeUrl(in.nextString());
                    dynamicBean.setKSBuyStatus(in.nextString());
                    in.endObject();
                    break;
                case "data":
                    dynamicBean.setLinkListBean(readLinkBean(in));
                    break;
                case "BrowseNum":
                    dynamicBean.setBrowseNum(in.nextString());
                    break;
                case "WatchCount":
                    dynamicBean.setWatchCount(in.nextString());
                    break;
            }
        }
        in.endObject();
        return dynamicBean;
    }

    private ArrayList<CircleCommentBean> readCommentBean(JsonReader in) throws IOException {
        ArrayList<CircleCommentBean> commentBeans = new ArrayList<>();
        in.beginArray();

//        CircleCommentBean commentBean = new CircleCommentBean();
//        commentBean.setContent(jo_comment.optString("Content"));
//        commentBean.setReviewerA(jo_comment.optString("NickName"));
//        commentBean.setReviewerTO(jo_comment.optString("ToName"));
//        commentBean.setReviewerAID(jo_comment.optString("NickNameId"));
//        commentBean.setReviewerBID(jo_comment.optString("ToNameId"));
//
//        commentBean.setCommentID(jo_comment.optString("CommentID"));
//
//        JSONArray ja_linkList = jo_comment.optJSONArray("data");
//        if (ja_linkList != null && ja_linkList.length() > 0) {
//            LinkListBean dynamic_comment_linkListBean = new LinkListBean(4);
//            ArrayList<LinkBaseBean> linkBeans = new ArrayList<>();
//
//            for (int j = 0; j < ja_linkList.length(); j++) {
//                JSONObject jo_linkData = ja_linkList.optJSONObject(j);
//                linkBeans.add(new LinkBaseBean(jo_linkData.optString("content"), jo_linkData.optString("url")));
//            }
//            dynamic_comment_linkListBean.setLinkBaseBeans(linkBeans);
//            commentBean.setLinkListBean(dynamic_comment_linkListBean);
//        }
//

//        while (in.hasNext()) {
//            switch ()
//        }
        in.endArray();
        return commentBeans;
    }

    private ArrayList<String> readPic(JsonReader in) {
        ArrayList<String> pics = new ArrayList<>();
        return pics;
    }

    private ForwardDynamicBean readForward(JsonReader in) throws IOException {


//        ForwardDynamicBean forwardDyna = new ForwardDynamicBean();
//        if (jo_forward != null && jo_forward.length() != 0) {
//
//            String isFlag = jo_forward.optString("IsFlag");
//            forwardDyna.setF_IsFlag(isFlag);
//
//            forwardDyna.setF_NickName(jo_forward.optString("NickName"));
//
//            forwardDyna.setF_GambitID(jo_forward.optString("GambitID"));
//            forwardDyna.setF_Content(jo_forward.optString("Content"));
//            forwardDyna.setF_blogUrl(jo_forward.optString("BlogUrl"));
//
//            //1.4.0新加字段
//            forwardDyna.setF_IsView(jo_forward.optString("IsView"));
//            forwardDyna.setF_LiveMsgID(jo_forward.optString("LiveMsgID"));
//            forwardDyna.setF_LiveProductID(jo_forward.optString("LiveProductID"));
//            forwardDyna.setF_Iszhib(jo_forward.optString("Iszhib"));
//            forwardDyna.setF_UserID(jo_forward.optString("UserID"));
//
//            //1.5.0新加。注：如果是音频文件，ArticleID值代表音频时间长度
//            forwardDyna.setF_isType(jo_forward.optString("isType"));
//            forwardDyna.setF_voice_time_length(jo_forward.optString("ArticleID"));
//
//            //1.6.2新加投资锦囊（新方案）
//            JSONObject new_plan_obj = jo_forward.optJSONObject("Plan");
//            if (new_plan_obj != null && !PARAM.checkStringIsEmpty(new_plan_obj.toString())) {
//                forwardDyna.setNew_plan_id(new_plan_obj.optString("PlanID"));
//                forwardDyna.setNew_plan_name(new_plan_obj.optString("PlanName"));
//                forwardDyna.setNew_plan_start_time(new_plan_obj.optString("PlanStartTime"));
//                forwardDyna.setNew_plan_expected_revenue(new_plan_obj.optString("PlanExpectedRevenue"));
//                forwardDyna.setNew_plan_price(new_plan_obj.optString("PlanPrice"));
//                forwardDyna.setNew_plan_risk_revenue(new_plan_obj.optString("PlanRiskRevenue"));
//                forwardDyna.setNew_plan_status(new_plan_obj.optString("PlanStatus"));
//                forwardDyna.setNew_plan_time_limit(new_plan_obj.optString("PlanTimeLimit"));
//                forwardDyna.setPlanNickName(new_plan_obj.optString("PlanNickName"));
//
//                //总收益
//                forwardDyna.setPlanTotalRevenue(new_plan_obj.optString("PlanTotalRevenue"));
//                //发布投资锦囊的专家的头像
//                forwardDyna.setPlan_avatar(new_plan_obj.optString("PlanIconUrl"));
//
//                DynamicPlanBean dynamicPlanBean = JsonParserPlanUtil.jsonParserPlan(new_plan_obj.toString());
//
//                if (dynamicPlanBean != null) {
//                    forwardDyna.setDynamicPlanBean(dynamicPlanBean);
//                }
//
//            }
//
//            //1.6.4，解析名片对象
//            JSONObject nameCard_obj = jo_forward.optJSONObject("NameCard");
//            if (nameCard_obj != null && !PARAM.checkStringIsEmpty(nameCard_obj.toString())) {
//                forwardDyna.setUserCard_UserID(nameCard_obj.optString("UserID"));
//                forwardDyna.setUserCard_IconUrl(nameCard_obj.optString("IconUrl"));
//                forwardDyna.setUserCard_NickName(nameCard_obj.optString("NickName"));
//                forwardDyna.setUserCard_Profile(nameCard_obj.optString("Profile"));
//            }
//
//            //1.6.4封装，语音对象
//            JSONObject f_Voice_obj = jo_forward.optJSONObject("Voice");
//            if (f_Voice_obj != null && !PARAM.checkStringIsEmpty(f_Voice_obj.toString())) {
//                forwardDyna.setF_VoiceUrl(f_Voice_obj.optString("VoiceUrl"));
//                forwardDyna.setF_VoiceLength(f_Voice_obj.optString("VoiceLength"));
//            }
//
//            //1.6.4封装，短视频对象
//            JSONObject f_Video_obj = jo_forward.optJSONObject("Video");
//            if (f_Video_obj != null && !PARAM.checkStringIsEmpty(f_Video_obj.toString())) {
//                forwardDyna.setF_VideoImgUrl(f_Video_obj.optString("VideoImgUrl"));
//                forwardDyna.setF_VideoUrl(f_Video_obj.optString("VideoUrl"));
//                forwardDyna.setF_VideoLength(f_Video_obj.optString("VideoLength"));
//            }
//
//            //1.7.5 发现页轮播图点击进入的网页分享到动态后的对象
//            JSONObject findShare = jo_forward.optJSONObject("FindShare");
//            if (findShare != null && !PARAM.checkStringIsEmpty(findShare.toString())) {
//                forwardDyna.setFindShareImgUrl(findShare.optString("ImgUrl"));
//                forwardDyna.setFindShareUrl(findShare.optString("Url"));
//                forwardDyna.setFindShareTitle(findShare.optString("Title"));
//            }
//
//            //2.0.4添加。收费动态对象
//            JSONObject f_PriceDynam_obj = jo_forward.optJSONObject("PriceDynam");
//            if (f_PriceDynam_obj != null && !PARAM.checkStringIsEmpty(f_PriceDynam_obj.toString())) {
//                forwardDyna.setF_PriceDynamicProductID(f_PriceDynam_obj.optString("ProductID"));
//                forwardDyna.setF_DynamicPrice(f_PriceDynam_obj.optString("Price"));
//                forwardDyna.setF_PriceDynamicContent(f_PriceDynam_obj.optString("Content"));
//                forwardDyna.setF_PriceDynamicIsBuy(f_PriceDynam_obj.optString("IsBuy"));
//                forwardDyna.setF_PriceDynamicID(f_PriceDynam_obj.optString("DynamicID"));
//            }
//
//            JSONArray jaFor_dynamicPic = jo_forward.optJSONArray("DynamicPic");
//            ArrayList<DynamicPicBean> f_picBeanList = new ArrayList<DynamicPicBean>();
//
//            if (jaFor_dynamicPic != null && jaFor_dynamicPic.length() != 0) {
//                for (int i = 0; i < jaFor_dynamicPic.length(); i++) {
//                    JSONObject joo_for_dynamicPic = jaFor_dynamicPic.optJSONObject(i);
//
//                    DynamicPicBean dynamicPicBean = new DynamicPicBean();
//                    dynamicPicBean.setImgUrl(joo_for_dynamicPic.optString("Img"));
//                    dynamicPicBean.setImg_width(joo_for_dynamicPic.optString("width"));
//                    dynamicPicBean.setImg_height(joo_for_dynamicPic.optString("height"));
//                    f_picBeanList.add(dynamicPicBean);
//                }
//            }
//            forwardDyna.setF_dynamicPicBeanList(f_picBeanList);
//
//            LinkListBean f_linkListBean = new LinkListBean(sourceType == 4 ? 3 : 0);
//            ArrayList<LinkBaseBean> linkBeans = new ArrayList<LinkBaseBean>();
//            JSONArray jaLinkList = jo_forward.optJSONArray("data");
//            if (jaLinkList != null && jaLinkList.length() != 0) {
//                for (int i = 0; i < jaLinkList.length(); i++) {
//                    JSONObject jo_linkData = jaLinkList.optJSONObject(i);
//                    linkBeans.add(new LinkBaseBean(jo_linkData.optString("content"), jo_linkData.optString("url")));
//                }
//            }
//
//            ArrayList<LinkBaseBean> f_linkBeans_person = new ArrayList<>();
//            JSONArray f_ja_userdata = jo_forward.optJSONArray("userdata");
//            if (null != f_ja_userdata && f_ja_userdata.length() != 0) {
//                for (int i = 0; i < f_ja_userdata.length(); i++) {
//                    JSONObject f_jo_uData = f_ja_userdata.optJSONObject(i);
//                    f_linkBeans_person.add(new LinkBaseBean("@" + f_jo_uData.optString("NickName"), ""));
//                }
//            }
//            linkBeans.addAll(0, f_linkBeans_person);
//            f_linkListBean.setLinkBaseBeans(linkBeans);
//            forwardDyna.setF_LinkBean(f_linkListBean);
//            forwardDyna.setF_IsDelete(jo_forward.optString("IsDelete"));
//            forwardDyna.setF_IsShield(jo_forward.optString("IsShield"));
//
//            //2.4.9新加。转发情况下，专栏的url
//            JSONObject f_Knowledge_obj = jo_forward.optJSONObject("Knowledge");
//            if (f_Knowledge_obj != null && !PARAM.checkStringIsEmpty(f_Knowledge_obj.toString())) {
//                forwardDyna.setF_KnowledgeTitle(f_Knowledge_obj.optString("Title"));
//                forwardDyna.setF_KnowledgeUrl(f_Knowledge_obj.optString("KnowledgeUrl"));
//                forwardDyna.setF_KSBuyStatus(f_Knowledge_obj.optString("Status"));
//            }
//
//            //最后一步。把转发布局中的数据放在整体数据中
//            dynamicBean.setForwardDynamic(forwardDyna);
//
//        }


        ForwardDynamicBean forwardDynamicBean = new ForwardDynamicBean();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "NickName":
                    forwardDynamicBean.setF_NickName(in.nextString());
                    break;


            }
        }
        in.endObject();
        return forwardDynamicBean;
    }

    private LinkListBean readLinkBean(JsonReader in) {
        LinkListBean linkListBean = new LinkListBean(sourceType == 4 ? 3 : 0);

        return linkListBean;
    }
}
