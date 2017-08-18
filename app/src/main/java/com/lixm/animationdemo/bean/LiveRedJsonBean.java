package com.lixm.animationdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User: LXM
 * Date: 2017-02-17
 * Time: 10:54
 * Detail:视频直播发红包JSON对象
 */
public class LiveRedJsonBean implements Serializable {
    private String userid;
    private String SysRedID;
    private LiveSecondBean liveSecondBean;
    private LiveThreeBean liveThreeBean;
    private ArrayList<LiveFourBean> liveFourBeen;

    public static class LiveSecondBean{
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    public ArrayList<LiveFourBean> getLiveFourBeen() {
        return liveFourBeen;
    }

    public void setLiveFourBeen(ArrayList<LiveFourBean> liveFourBeen) {
        this.liveFourBeen = liveFourBeen;
    }

    public LiveThreeBean getLiveThreeBean() {
        return liveThreeBean;
    }

    public void setLiveThreeBean(LiveThreeBean liveThreeBean) {
        this.liveThreeBean = liveThreeBean;
    }

    public LiveSecondBean getLiveSecondBean() {
        return liveSecondBean;
    }

    public void setLiveSecondBean(LiveSecondBean liveSecondBean) {
        this.liveSecondBean = liveSecondBean;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSysRedID() {
        return SysRedID;
    }

    public void setSysRedID(String sysRedID) {
        SysRedID = sysRedID;
    }
}
