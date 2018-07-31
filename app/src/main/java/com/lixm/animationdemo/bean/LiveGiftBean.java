package com.lixm.animationdemo.bean;


import java.io.Serializable;

/**
 * User: LXM
 * Date: 2017-01-24
 * Time: 13:42
 * Detail:视频直播室礼物对象
 */
public class LiveGiftBean implements Serializable {

    private String GiftNum="3";//礼物数量
    private String GiftName;//礼物名称
    private String ProductPrice;//礼物价格
    private boolean IsCheck = false;//是否选中
    private String ProductID;//礼物类型
    private int poi;//在gridView中的位置
    private String ProductSeq;//礼物编号
    private String userNickName;//连发礼物区分组
//    private ObjectAnimator objectAnimator;//背景图片动画
    private int pageNum;//0,1

    public LiveGiftBean() {
    }

    public String getGiftNum() {
        return GiftNum;
    }

    public void setGiftNum(String giftNum) {
        GiftNum = giftNum;
    }

    public String getGiftName() {
        return GiftName;
    }

    public void setGiftName(String giftName) {
        GiftName = giftName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public boolean isCheck() {
        return IsCheck;
    }

    public void setCheck(boolean check) {
        IsCheck = check;
    }


    public int getPoi() {
        return poi;
    }

    public void setPoi(int poi) {
        this.poi = poi;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductSeq() {
        return ProductSeq;
    }

    public void setProductSeq(String productSeq) {
        ProductSeq = productSeq;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "LiveGiftBean{" +
                "GiftNum='" + GiftNum + '\'' +
                ", GiftName='" + GiftName + '\'' +
                ", ProductPrice='" + ProductPrice + '\'' +
                ", IsCheck=" + IsCheck +
                ", ProductID=" + ProductID +
                ", poi=" + poi +
                ", ProductSeq=" + ProductSeq +
                '}';
    }
}
