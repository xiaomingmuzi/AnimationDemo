package com.lixm.animationdemo.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.lixm.animationdemo.utils.PARAM;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class RefreshFiveSpeedTimer {
	 
 
	private Context context;
	private String id;

	public RefreshFiveSpeedTimer(Application context, String id) {
		this.context = context;
		this.id = id;
	}

	public void cancel() {
		if (PARAM.REFRESH_FIVESPEED_TIMER != null) {
			LogUtil.i("-----------------RefreshFiveSpeed_TIMER_取消----------------");
			PARAM.REFRESH_FIVESPEED_TIMER.cancel();
			PARAM.REFRESH_FIVESPEED_TIMER = null;
		} else {
			LogUtil.i("-----------RefreshFiveSpeed_本来就是null,无需取消---------");
		}
	}

	public void startTimerTask() {
		if (PARAM.REFRESH_FIVESPEED_TIMER == null) {
			LogUtil.i("------------------RefreshFiveSpeed_实例化-------------");
			PARAM.REFRESH_FIVESPEED_TIMER = new Timer();
		}
		TimerTask uploadTask = new TimerTask() {
			@Override
			public void run() {
				LogUtil.i("---------------运行RefreshFiveSpeed,开始更新-------------");
				doRefresh();
			}
		};
		PARAM.REFRESH_FIVESPEED_TIMER.schedule(uploadTask, 10000, 15 * 1000);
	}

	private void doRefresh() {
		RequestParams params=new RequestParams(PARAM.URL_TIMELY_QUOTE);
		params.addBodyParameter("action", "stockFive");
		params.addBodyParameter("stID", id);
		params.addBodyParameter("action", "stockFive");
		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String result) {
				try {
					JSONArray jsonArray = new JSONArray(result.toString());
					int length = jsonArray.length();
					Map<String, String> buyData = new LinkedHashMap<String, String>();
					Map<String, String> sellData = new LinkedHashMap<String, String>();
					for (int i = 0; i < length; i++) {
						JSONArray data = jsonArray.getJSONArray(i);
						String buyPrice = String.valueOf(data.get(0));
						String sellPrice = String.valueOf(data.get(1));
						String buyAmount = String.valueOf(data.get(2));
						String sellAmount = String.valueOf(data.get(3));
						buyData.put(buyPrice + "-" + i, buyAmount);
						sellData.put(sellPrice + "-" + i, sellAmount);
					}

					if (length > 0) {
						PARAM.stockData.setBuyData(buyData);
						PARAM.stockData.setSellData(sellData);

						Intent intent = new Intent();
						intent.setAction(PARAM.REFRESH_FIVE_SPEED);
						context.sendBroadcast(intent);
						LogUtil.i("RefreshFiveSpeed--发送广播");
					}

				} catch (Exception e) {
					LogUtil.e("<---" + getClass().getName() + "Json解析异常--->" + e.getMessage());
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {

			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {

			}
		});

	}
}
