package com.lixm.animationdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.lixm.animationdemo.utils.Contants;

import org.xutils.common.util.LogUtil;

/**
 * @author Lixm
 * @date 2017/11/24
 * @detail
 */

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Contants.MSG_FROM_CLIENT:
                    LogUtil.w("receive msg from Client：" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message relpyMessage = Message.obtain(null, Contants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "恩，你的消息我已收到，稍后会回复你。");
                    LogUtil.i("bundle："+bundle.getString("reply"));
                    relpyMessage.setData(bundle);
                    try {
                        client.send(relpyMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.w("===onBind==");
        return mMessenger.getBinder();
    }
}
