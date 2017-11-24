package com.lixm.animationdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Lixm
 * @date 2017/11/24
 * @detail
 */

public class StudentBean implements Parcelable {

    public int userId;
    public String userName;
    public boolean isMale;

    public MeiZi meiZi;

    public StudentBean(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    private StudentBean(Parcel in){
        userId=in.readInt();
        userName=in.readString();
        isMale=in.readInt()==1;
        meiZi=in.readParcelable(Thread.currentThread().getContextClassLoader());
    }
    public static final Creator<StudentBean> CREATOR = new Creator<StudentBean>() {
        @Override
        public StudentBean createFromParcel(Parcel in) {
            return new StudentBean(in);
        }

        @Override
        public StudentBean[] newArray(int size) {
            return new StudentBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(isMale?1:0);
        dest.writeParcelable(meiZi,0);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public MeiZi getMeiZi() {
        return meiZi;
    }

    public void setMeiZi(MeiZi meiZi) {
        this.meiZi = meiZi;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", isMale=" + isMale +
                ", meiZi=" + meiZi +
                '}';
    }
}
