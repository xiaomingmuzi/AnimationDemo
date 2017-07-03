package com.lixm.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.FlashDataParser;
import com.lixm.animationdemo.customview.FlashView;

public class FlashActivity extends BaseActivity {

    FlashView mFlashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        mFlashView = (FlashView) findViewById(R.id.flashview);

        new Thread() {
            @Override
            public void run() {
                int num = 1;
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

//                    if (num%1==0) {
//                        mFlashView.reload("heiniao", "flashAnims");
//                        mFlashView.play("atk", FlashDataParser.FlashLoopTimeForever);
//                    } else if(num%2==0){
////                        mFlashView.reload("testDB", "flashAnims");
////                        mFlashView.play("applanbo", FlashDataParser.FlashLoopTimeForever);
//                        mFlashView.reload("test", "testflash");
//                        mFlashView.play("oneAnim", FlashDataParser.FlashLoopTimeForever);//这里的animationName就是flash中anims文件夹内的动画名称
//                    }else if(num%3==0){
//                        mFlashView.reload("cnfol", "testflash");
//                        mFlashView.play("rottenEgg", FlashDataParser.FlashLoopTimeForever);//这里的animationName就是flash中anims文件夹内的动画名称
//                    }
                    if (num % 2 == 0) {
                        mFlashView.reload("cnfol", "testflash");
                        mFlashView.play("rottenEgg", FlashDataParser.FlashLoopTimeForever);//这里的animationName就是flash中anims文件夹内的动画名称
                    } else if (num % 3 == 0) {
                        mFlashView.reload("brick", "testflash");
                        mFlashView.play("goldBrick", FlashDataParser.FlashLoopTimeForever);
                    } else {
                        mFlashView.reload("up", "testflash");
                        mFlashView.play("stockUp", FlashDataParser.FlashLoopTimeForever);
                    }
                    num++;
                }
            }
        }.start();
    }
}
