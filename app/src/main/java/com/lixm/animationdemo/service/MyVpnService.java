package com.lixm.animationdemo.service;

import android.content.Intent;
import android.net.VpnService;
import android.os.IBinder;

/**
 * @author Lixm
 * @date 2017/10/25
 * @detail
 */

public class MyVpnService extends VpnService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
