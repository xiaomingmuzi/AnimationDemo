package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * User: LXM
 * Date: 2016-04-15
 * Time: 10:04
 * Detail:处理MyTextView中点赞跳转逻辑
 */
public class LinkBaseBean implements Serializable {

    /**
     * 显示参数
     */
    private String content;
    /**
     * 跳转参数
     */
    private String url;

    public LinkBaseBean() {
    }

    public LinkBaseBean(String content, String url) {
        this.content = content;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LinkBaseBean{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
