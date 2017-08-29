package com.lixm.animationdemo.utils;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public interface BaseMediaPlayerListener {
    void onLoading();

    void onLoadFailed();

    void onFinishLoading();

    void onError(int what, String message);

    void onStartPlay();

    void onPlayComplete();

    void onResumed();

    void onPaused();

    void onStopped();
}
