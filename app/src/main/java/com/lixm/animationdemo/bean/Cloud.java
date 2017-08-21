package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2017/8/21
 * @detail
 */

public class Cloud implements Serializable {
    private int enable; //1, //可选, 默认1
    private String serverList;// //可选, 强制忽略serverList，直接连server
    private String server;// "ws://cloud.chivox.com:8080", // 必须,云服务地址
    private int connectTimeout;// 20, //可选, 默认是20s, 建立连接的超时时间
    private int serverTimeout;// 60 //可选, 默认是60s, 响应的超时时间

    public Cloud() {
    }

    public Cloud(int enable, String serverList, String server, int connectTimeout, int serverTimeout) {
        this.enable = enable;
        this.serverList = serverList;
        this.server = server;
        this.connectTimeout = connectTimeout;
        this.serverTimeout = serverTimeout;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getServerList() {
        return serverList;
    }

    public void setServerList(String serverList) {
        this.serverList = serverList;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getServerTimeout() {
        return serverTimeout;
    }

    public void setServerTimeout(int serverTimeout) {
        this.serverTimeout = serverTimeout;
    }

    @Override
    public String toString() {
        return "Cloud{" +
                "enable=" + enable +
                ", serverList='" + serverList + '\'' +
                ", server='" + server + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", serverTimeout=" + serverTimeout +
                '}';
    }
}
