package com.lixm.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.MyHeartView;

public class MyHeartViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hear_view);
        final MyHeartView myHeartView= (MyHeartView) findViewById(R.id.my_heart_view);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHeartView.addImg();
            }
        });

    }
}
