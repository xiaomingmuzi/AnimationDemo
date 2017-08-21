package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2017/8/21
 * @detail
 */

public class Prof implements Serializable {
    private int enable;// 1, //可选，调试开关，默认关闭
    private String output;// "log-file-path" //如开启调试功能，则必选，debug日志路径 } }

    public Prof(int enable, String output) {
        this.enable = enable;
        this.output = output;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Prof{" +
                "enable=" + enable +
                ", output='" + output + '\'' +
                '}';
    }
}
