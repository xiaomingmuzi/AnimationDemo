package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2017/8/21
 * @detail
 */

public class JsonContentBean implements Serializable {
    private String appKey;// "1351742***", //必须
    private String secretKey;// "a3bed5523bbc020cf4a****", //必须
    private String provision;//path-of-provision-profile;// //必须, 开发证书存放路径
    private Cloud cloud;
    private Vad vad;// { //可选, 如果没有vad项则表示禁用vad
    private Prof prof;//调试功能

    public JsonContentBean() {
    }

    public JsonContentBean(String appKey, String secretKey, String provision, Cloud cloud, Vad vad, Prof prof) {
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.provision = provision;
        this.cloud = cloud;
        this.vad = vad;
        this.prof = prof;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getProvision() {
        return provision;
    }

    public void setProvision(String provision) {
        this.provision = provision;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public Vad getVad() {
        return vad;
    }

    public void setVad(Vad vad) {
        this.vad = vad;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    @Override
    public String toString() {
        return "JsonContentBean{" +
                "appKey='" + appKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", provision='" + provision + '\'' +
                ", cloud=" + cloud +
                ", vad=" + vad +
                ", prof=" + prof +
                '}';
    }
}
