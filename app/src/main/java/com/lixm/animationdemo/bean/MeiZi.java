package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2017/8/17
 * @detail
 */

public class MeiZi implements Serializable {
    private String url;//图片地址
    private int page;//页数

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
