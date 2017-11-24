package com.lixm.animationdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Lixm
 * @date 2017/8/17
 * @detail
 */

public class MeiZi implements Parcelable {
    private String url;//图片地址
    private int page;//页数

    public MeiZi() {
    }

    public MeiZi(String url, int page) {
        this.url = url;
        this.page = page;
    }

    private MeiZi(Parcel in) {
        url = in.readString();
        page = in.readInt();
    }

    public static final Creator<MeiZi> CREATOR = new Creator<MeiZi>() {
        @Override
        public MeiZi createFromParcel(Parcel in) {
            return new MeiZi(in);
        }

        @Override
        public MeiZi[] newArray(int size) {
            return new MeiZi[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeInt(page);
    }

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
