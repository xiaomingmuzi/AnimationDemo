package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2017/8/21
 * @detail
 */

public class Vad implements Serializable {
    private int enable;// 1, //可选, 默认1,仅在audioType为wav时有效(vad的参数优先在配置文件里配置)
    private String res;//"this-is-vad-res-path", //必须, 如果enable为1则必须
    private int speechLowSeek;// 60, //可选, 默认为资源里的配置, 单位帧, 一帧10ms, 表示静音敏感度, 静音等待时间
    private long sampleRate;// 16000, //可选, 默认为资源里的配置
    private int strip;// 1 //可选, 默认为1 即过滤首尾静音, 0表示仅用于判断发音结束 },

    public Vad() {
    }

    public Vad(int enable, String res, int speechLowSeek, long sampleRate, int strip) {
        this.enable = enable;
        this.res = res;
        this.speechLowSeek = speechLowSeek;
        this.sampleRate = sampleRate;
        this.strip = strip;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getSpeechLowSeek() {
        return speechLowSeek;
    }

    public void setSpeechLowSeek(int speechLowSeek) {
        this.speechLowSeek = speechLowSeek;
    }

    public long getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(long sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getStrip() {
        return strip;
    }

    public void setStrip(int strip) {
        this.strip = strip;
    }

    @Override
    public String toString() {
        return "Vad{" +
                "enable=" + enable +
                ", res='" + res + '\'' +
                ", speechLowSeek=" + speechLowSeek +
                ", sampleRate=" + sampleRate +
                ", strip=" + strip +
                '}';
    }
}
