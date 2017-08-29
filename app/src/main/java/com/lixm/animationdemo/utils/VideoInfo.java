package com.lixm.animationdemo.utils;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public class VideoInfo {
    private String CurrentStreamUrl;
    private int CurrentPosition;

    public VideoInfo(String currentStreamUrl, int currentPosition) {
        CurrentStreamUrl = currentStreamUrl;
        CurrentPosition = currentPosition;
    }

    public String getCurrentStreamUrl() {
        return CurrentStreamUrl;
    }

    public void setCurrentStreamUrl(String currentStreamUrl) {
        CurrentStreamUrl = currentStreamUrl;
    }

    public int getCurrentPosition() {
        return CurrentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        CurrentPosition = currentPosition;
    }
}
