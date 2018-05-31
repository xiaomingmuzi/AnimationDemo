package com.lixm.animationdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User: LXM
 * Date: 2016-04-15
 * Time: 10:19
 * Detail:装载LinkBaseBean的集合
 */
public class LinkListBean implements Serializable {
    /**
     * 标示对象类型
     * 0 标示个股或者博文链接，需跳转到 StockDetailActivity 颜色为蓝色
     * 1 标示用户名点击，需跳转到 DetailsActivity 颜色为蓝色
     * 2 A回复B形式，需跳转到 DetailsActivity 颜色为蓝色
     * 3 搜索内容，不许跳转，颜色为红色
     * 4 动态详情页，评论、回复评论的内容匹配关键字
     */
    private int type;
    private ArrayList<LinkBaseBean> linkBaseBeans;
    private CircleCommentBean circleCommentBean;

    public LinkListBean(int type) {
        this.type = type;
    }

    /**
     * 标示对象类型
     * 0 标示个股或者博文链接，需跳转到 StockDetailActivity 颜色为蓝色
     * 1 标示用户名点击，需跳转到 DetailsActivity 颜色为蓝色
     * 2 A回复B形式，需跳转到 DetailsActivity 颜色为蓝色
     * 3 搜索内容，不许跳转，颜色为红色
     */
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<LinkBaseBean> getLinkBaseBeans() {
        return linkBaseBeans;
    }

    public void setLinkBaseBeans(ArrayList<LinkBaseBean> linkBaseBeans) {
        this.linkBaseBeans = linkBaseBeans;
    }

    public CircleCommentBean getCircleCommentBean() {
        return circleCommentBean;
    }

    public void setCircleCommentBean(CircleCommentBean circleCommentBean) {
        this.circleCommentBean = circleCommentBean;
    }

    @Override
    public String toString() {
        return "LinkListBean{" +
                "type=" + type +
                ", linkBaseBeans=" + linkBaseBeans +
                ", circleCommentBean=" + circleCommentBean +
                '}';
    }
}
