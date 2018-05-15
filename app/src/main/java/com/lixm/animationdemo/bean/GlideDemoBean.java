package com.lixm.animationdemo.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2018/5/15
 * @detail
 */

public class GlideDemoBean implements Serializable {
    @SerializedName(value="type")
    private String type;
    @SerializedName(value = "url")
    private String imgUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
