package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lixm.animationdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        findViewById(R.id.random_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("Random", "====id：" + getOutTradeNo());
            }
        });
    }

    private Product product = Product.CollegeCourse;

    public enum Product {
        CollegeCourse, Default, LiveGift, LiveText
    }

    /**
     * @Title: getOutTradeNo @Description: 生成订单号 @return 设定文件 @return String
     * 返回类型 @throws
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        String key = format.format(date);
        java.util.Random r = new java.util.Random();
        int random=r.nextInt(2147483647);
        Log.e("Random","random===="+random);
        key += random;
        String orderId = key.substring(0, 15);
        if (product == Product.CollegeCourse) {
            orderId = "DC_" + orderId;
        } else if (product == Product.LiveGift) {
            orderId = "DW_" + orderId;
        } else if (product == Product.LiveText) {
            orderId = "DL_" + orderId;
        }
        return orderId;
    }
}
