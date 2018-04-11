package com.lixm.animationdemo.bean;

import android.os.Bundle;

/**
 * @author Lixm
 * @date 2018/3/28
 * @detail 定义消息事件类
 */

public class MessageEvent {
    private Bundle message;

    public MessageEvent(Bundle message) {
        this.message = message;
    }

    public Bundle getMessage() {
        return message;
    }

    public void setMessage(Bundle message) {
        this.message = message;
    }
}
