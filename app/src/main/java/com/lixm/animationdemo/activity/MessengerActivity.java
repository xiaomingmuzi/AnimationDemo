package com.lixm.animationdemo.activity;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.service.MessengerService;
import com.lixm.animationdemo.utils.Contants;

import org.xutils.common.util.LogUtil;

public class MessengerActivity extends FragmentActivity {

    private static final String TAG = "MessengerActivity";
    private Messenger mService;
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Contants.MSG_FROM_SERVICE:
                    LogUtil.i("receive msg from Service：" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }

        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null, Contants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello this is client.");
            msg.setData(data);
            msg.replyTo = mGetReplyMessenger;
            try {
                mService.send(msg);
                LogUtil.w("=========绑定Service成功，发送消息=======");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
        LogUtil.w("绑定Service");

    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();

    }

}
