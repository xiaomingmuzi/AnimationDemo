package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * chenJianQiang
 * 动态图片bean，用于保存图片对象
 */
public class DynamicPicBean implements PicBean,Serializable {

    public String imgUrl;
    public String img_width;
    public String img_height;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImg_width() {
        return img_width;
    }

    public void setImg_width(String img_width) {
        this.img_width = img_width;
    }

    public String getImg_height() {
        return img_height;
    }

    public void setImg_height(String img_height) {
        this.img_height = img_height;
    }
}
