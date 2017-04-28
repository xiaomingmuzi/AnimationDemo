package com.lixm.animationdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.MyHeartView;

public class MyHeartViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hear_view);
        final MyHeartView myHeartView= (MyHeartView) findViewById(R.id.my_heart_view);


//        ImageView heartImg= (ImageView) findViewById(R.id.heartImg);
////        float endF= (float) Math.random();
//        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(heartImg,"scaleX",0.2f,1.0f);
//        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(heartImg,"scaleY",0.2f,1.0f);
//        objectAnimator.setRepeatCount(4);
//        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        objectAnimator1.setRepeatCount(4);
//        objectAnimator1.setRepeatMode(ValueAnimator.REVERSE);
//        final AnimatorSet animatorSet=new AnimatorSet();
//        animatorSet.playTogether(objectAnimator,objectAnimator1);
//        animatorSet.setDuration(500);


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHeartView.addImg();
//                animatorSet.start();
            }
        });
    }
}
