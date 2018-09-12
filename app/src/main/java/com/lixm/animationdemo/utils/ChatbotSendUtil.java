package com.lixm.animationdemo.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xutils.common.util.LogUtil;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class ChatbotSendUtil {

    public static void sendData(final String content) {
        new Thread() {
            @Override
            public void run() {
                final String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=b7ff98db082fd3530ae91bc1b0e79d20ec7af0e4f4760e1ff879daa3bf7f20bf";
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
                httppost.addHeader("Content-Type", "application/json; charset=utf-8");
                String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + content + "\"}}";
                try {
                    StringEntity se = new StringEntity(textMsg, "utf-8");
                    httppost.setEntity(se);
                    HttpResponse response = httpclient.execute(httppost);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        String result = EntityUtils.toString(response.getEntity(), "utf-8");
                        LogUtil.i("result：" + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}