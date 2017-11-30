package com.lixm.animationdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lixm.animationdemo.utils.PARAM;

import org.xutils.common.util.LogUtil;

public class RefreshFiveSpeedService extends Service {
	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.i("-----------------RefreshFiveSpeedService启动--------------");

		RefreshFiveSpeedTimer timer = new RefreshFiveSpeedTimer(getApplication(), PARAM.stockData.getStockID());
		timer.cancel();
		timer.startTimerTask();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		LogUtil.i("-----------------RefreshFiveSpeedService销毁--------------");
		super.onDestroy();
	}

}
