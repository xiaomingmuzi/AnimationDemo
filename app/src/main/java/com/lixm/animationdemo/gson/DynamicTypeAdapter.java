package com.lixm.animationdemo.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lixm.animationdemo.bean.CircleCommentBean;
import com.lixm.animationdemo.bean.DynamicBean;
import com.lixm.animationdemo.bean.DynamicPicBean;
import com.lixm.animationdemo.bean.ForwardDynamicBean;
import com.lixm.animationdemo.bean.LinkBaseBean;
import com.lixm.animationdemo.bean.LinkListBean;

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
        in.beginArray();
        List<DynamicNewBean.MerListBean> listBeans = new ArrayList<>();
        DynamicNewBean.MerListBean merListBean = new DynamicNewBean.MerListBean();
        merListBean.setDynamic(readDynamicBeans(in));
        in.endArray();
        return listBeans;
    }

    public List<DynamicBean> readDynamicBeans(JsonReader in) throws IOException {
        List<DynamicBean> dynamicBeans = new ArrayList<>();
        in.beginObject();
        in.beginArray();
        while (in.hasNext()) {
            switch (in.nextName()) {
//                case "Gabmit":
//                    break;
                case "Dynamic":
                    in.beginArray();
                    dynamicBeans.add(readDynamicBean(in));
                    in.endArray();
                    break;
            }
        }
        in.endArray();
        in.endObject();
        return dynamicBeans;
    }

    public DynamicBean readDynamicBean(JsonReader in) throws IOException {
        DynamicBean dynamicBean = new DynamicBean();
        in.beginArray();
        while (in.hasNext()) {
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
                        dynamicBean.setDynamicPicBeanList(readPic(in));
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
                        dynamicBean.setLinkListBean(readLinkBean(in, sourceType == 4 ? 3 : 0));
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
        }
        in.endArray();
        return dynamicBean;
    }

    private ArrayList<CircleCommentBean> readCommentBean(JsonReader in) throws IOException {
        ArrayList<CircleCommentBean> commentBeans = new ArrayList<>();
        in.beginArray();

        CircleCommentBean commentBean = new CircleCommentBean();

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "Content":
                    commentBean.setContent(in.nextString());
                    break;
                case "NickName":
                    commentBean.setReviewerA(in.nextString());
                    break;
                case "ToName":
                    commentBean.setReviewerTO(in.nextString());
                    break;
                case "NickNameId":
                    commentBean.setReviewerAID(in.nextString());
                    break;
                case "ToNameId":
                    commentBean.setReviewerBID(in.nextString());
                    break;
                case "CommentID":
                    commentBean.setCommentID(in.nextString());
                    break;
                case "data":
                    commentBean.setLinkListBean(readLinkBean(in, 4));
                    break;
            }
        }
        in.endArray();
        return commentBeans;
    }

    private ArrayList<DynamicPicBean> readPic(JsonReader in) throws IOException {
        ArrayList<DynamicPicBean> pics = new ArrayList<>();
        DynamicPicBean dynamicPicBean = new DynamicPicBean();
        in.beginArray();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "Img":
                    dynamicPicBean.setImgUrl(in.nextString());
                    break;
                case "width":
                    dynamicPicBean.setImg_width(in.nextString());
                    break;
                case "height":
                    dynamicPicBean.setImg_height(in.nextString());
                    break;
            }
            pics.add(dynamicPicBean);
        }
        in.endArray();
        return pics;
    }

    private ForwardDynamicBean readForward(JsonReader in) throws IOException {

        ForwardDynamicBean forwardDynamicBean = new ForwardDynamicBean();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "NickName":
                    forwardDynamicBean.setF_NickName(in.nextString());
                    break;
                case "GambitID":
                    forwardDynamicBean.setF_GambitID(in.nextString());
                    break;
                case "Content":
                    forwardDynamicBean.setF_Content(in.nextString());
                    break;
                case "BlogUrl":
                    forwardDynamicBean.setF_blogUrl(in.nextString());
                    break;

                case "IsView":
                    forwardDynamicBean.setF_IsView(in.nextString());
                    break;

                case "LiveMsgID":
                    forwardDynamicBean.setF_LiveMsgID(in.nextString());
                    break;
                case "LiveProductID":
                    forwardDynamicBean.setF_LiveProductID(in.nextString());
                    break;
                case "Iszhib":
                    forwardDynamicBean.setF_Iszhib(in.nextString());
                    break;
                case "UserID":
                    forwardDynamicBean.setF_UserID(in.nextString());
                    break;
                case "isType":
                    forwardDynamicBean.setF_isType(in.nextString());
                    break;
                case "ArticleID":
                    forwardDynamicBean.setF_voice_time_length(in.nextString());
                    break;
//                case "Plan":  操盘计划相关未注解
//                    forwardDynamicBean.set(in.nextString());
//                    break;
                case "NameCard":
                    in.beginObject();
                    if (in.hasNext()) {
                        switch (in.nextName()) {
                            case "UserID":
                                forwardDynamicBean.setUserCard_UserID(in.nextString());
                                break;
                            case "IconUrl":
                                forwardDynamicBean.setUserCard_IconUrl(in.nextString());
                                break;
                            case "NickName":
                                forwardDynamicBean.setUserCard_NickName(in.nextString());
                                break;
                            case "Profile":
                                forwardDynamicBean.setUserCard_Profile(in.nextString());
                                break;
                        }
                    }
                    in.endObject();
                    break;
                case "Voice":
                    in.beginObject();
                    switch (in.nextName()) {
                        case "VoiceUrl":
                            forwardDynamicBean.setF_VoiceUrl(in.nextString());
                            break;
                        case "VoiceLength":
                            forwardDynamicBean.setF_VoiceLength(in.nextString());
                            break;
                    }
                    in.endObject();
                    break;
                case "Video":
                    in.beginObject();
                    switch (in.nextName()) {
                        case "VideoImgUrl":
                            forwardDynamicBean.setF_VideoImgUrl(in.nextString());
                            break;
                        case "VideoLength":
                            forwardDynamicBean.setF_VoiceLength(in.nextString());
                            break;
                    }
                    in.endObject();
                    break;
                case "FindShare":
                    in.beginObject();
                    switch (in.nextName()) {
                        case "ImgUrl":
                            forwardDynamicBean.setFindShareImgUrl(in.nextString());
                            break;
                        case "Url":
                            forwardDynamicBean.setFindShareUrl(in.nextString());
                            break;
                        case "Title":
                            forwardDynamicBean.setFindShareTitle(in.nextString());
                            break;
                    }
                    in.endObject();
                    break;
                case "PriceDynam"://PriceDynam收费动态省略
                    break;
                case "DynamicPic":
                    in.beginArray();
                    ArrayList<DynamicPicBean> f_picBeanList = new ArrayList<DynamicPicBean>();
                    DynamicPicBean dynamicPicBean = new DynamicPicBean();

                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case "Img":
                                dynamicPicBean.setImgUrl(in.nextString());
                                break;
                            case "width":
                                dynamicPicBean.setImg_width(in.nextString());
                                break;
                            case "height":
                                dynamicPicBean.setImg_height(in.nextString());
                                break;
                        }
                        f_picBeanList.add(dynamicPicBean);
                    }
                    forwardDynamicBean.setF_dynamicPicBeanList(f_picBeanList);

                    in.endArray();
                    break;
                case "data":
                    forwardDynamicBean.setF_LinkBean(readLinkBean(in, sourceType == 4 ? 3 : 0));
                    break;


            }
        }
        in.endObject();
        return forwardDynamicBean;
    }

    private LinkListBean readLinkBean(JsonReader in, int type) throws IOException {
        LinkListBean linkListBean = new LinkListBean(type);
        ArrayList<LinkBaseBean> linkBeans = new ArrayList<>();
        LinkBaseBean linkBaseBean = new LinkBaseBean();
        in.beginArray();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "content":
                    linkBaseBean.setContent(in.nextString());
                    break;
                case "url":
                    linkBaseBean.setUrl(in.nextString());
                    break;
            }
            linkBeans.add(linkBaseBean);
        }
        linkListBean.setLinkBaseBeans(linkBeans);
        in.endArray();
        return linkListBean;
    }
}
