package com.lixm.animationdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixm.animationdemo.R;

/**
 * http://blog.csdn.net/guolin_blog/article/details/43536355  上篇
 * http://blog.csdn.net/guolin_blog/article/details/43816093  中篇
 * http://blog.csdn.net/guolin_blog/article/details/44171115  下篇
 */
public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.simple_animation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ObjectAnimation1Activity.class));
            }
        });

        findViewById(R.id.point_animation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ObjectAnimation2Activity.class));
            }
        });

    }
}
