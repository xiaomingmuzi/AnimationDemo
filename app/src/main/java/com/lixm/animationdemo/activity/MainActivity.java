package com.lixm.animationdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lixm.animationdemo.R;

/**
 * http://blog.csdn.net/guolin_blog/article/details/43536355  上篇
 * http://blog.csdn.net/guolin_blog/article/details/43816093  中篇
 * http://blog.csdn.net/guolin_blog/article/details/44171115  下篇
 */
public class MainActivity extends BaseActivity {
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

        findViewById(R.id.bezier_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BezierActivity.class));
            }
        });
        findViewById(R.id.my_heart_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyHeartViewActivity.class));
            }
        });

        findViewById(R.id.circle_progress_bar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CircleProgressBarActivity.class));
            }
        });
        findViewById(R.id.animation_frame_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ObjectAnimationFrameActivity.class));
            }
        });
        findViewById(R.id.animation_flash_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FlashActivity.class));
            }
        });
        findViewById(R.id.pay_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PayPassportActivity.class));
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GreenDaoActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private boolean isExit;
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        } else {
            finish();
            FinishActivityManager.getManager().finishAllActivity();
        }
    }
}
