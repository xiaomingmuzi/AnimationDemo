package com.lixm.animationdemo.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.FlowLayout;
import com.lixm.animationdemo.utils.MD5;
import com.lixm.liveplayerlibrary.LivePlayerActivity;

import java.util.ArrayList;


/**
 * http://blog.csdn.net/guolin_blog/article/details/43536355  上篇
 * http://blog.csdn.net/guolin_blog/article/details/43816093  中篇
 * http://blog.csdn.net/guolin_blog/article/details/44171115  下篇
 */
public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    private FlowLayout mFlowLayout;
    private ArrayList<String> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.channel);
        ApplicationInfo appInfo=null;
        try{
            appInfo=getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String channel=appInfo.metaData.getString("UMENG_CHANNEL");
            textView.setText(channel);
            textView.setText(MD5.getMD5LowerCase(MD5.getMD5String("Hello")));
        }catch (Exception e){
            e.printStackTrace();
        }

        mFlowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        mFlowLayout.setPadding(12, 12, 12, 12);
        mlist = new ArrayList<>();
        mlist.add("简单的animation实现");
        mlist.add("PointAnimation实现");
        mlist.add("bezier");
        mlist.add("myheart");
        mlist.add("圆形进度条");
        mlist.add("属性动画代替帧动画");
        mlist.add("Flash动画");
        mlist.add("支付密码框");
        mlist.add("GreenDao数据库测试");
        mlist.add("Dialog展示");
        mlist.add("recyclerView测试");
        mlist.add("json2xml测试");
        mlist.add("json测试");
        mlist.add("音频录音");
        mlist.add("短视频播放");
        mlist.add("手势Demo");
        mlist.add("PlayerView测试");
        mlist.add("Random测试");
        mlist.add("FixureProgressBar");
        mlist.add("浏览器接口测试");
        mlist.add("Butterknife插件测试");
        mlist.add("获取证书信息");
        addData();
        mFlowLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                FinancialToast.show(mContext, mDatas.get(checkedId).getName());
//                chooseBankBean = mDatas.get(checkedId);
                switch (checkedId) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ObjectAnimation1Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ObjectAnimation2Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, BezierActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, MyHeartViewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, CircleProgressBarActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ObjectAnimationFrameActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, FlashActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, PayPassportActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, DialogActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, CollegeActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, JsonXmlActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, JsonBeanActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, RecordSoundActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, LivePlayerActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, GestureDemoActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, CollegePlayerActivity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, RandomActivity.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, FixurePositionProgressBarActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                        break;
                    case 20:
                        startActivity(new Intent(MainActivity.this, ButterknifeActivity.class));
                        break;
                    case 21:
                        startActivity(new Intent(MainActivity.this, CertificateFactoryActivity.class));
                        break;
                }
            }
        });
    }

    private void addData() {
        /**
         * 放数据
         */
        if (mlist.size() > 0) {
            for (int i = 0; i < mlist.size(); i++) {
                //创建textView，并设置属性
                final RadioButton textView = new RadioButton(this);
                textView.setPadding(10, 5, 10, 5);
                textView.setId(i);
                Bitmap a = null;
                textView.setButtonDrawable(new BitmapDrawable(a));
//                textView.setTextSize(UIUtils.dp2Px(14));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(getResources().getColor(R.color.choose_bank_txt));
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.bank_item_choose));
                //给textView设置内容
                textView.setText(mlist.get(i));
                if (i == 0) {
                    textView.setChecked(true);
                }
                //把textView放到flowLayout中
                mFlowLayout.addView(textView);
            }
        }
    }

//    private void initBtn() {
//        findViewById(R.id.simple_animation_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,ObjectAnimation1Activity.class));//0
//            }
//        });
//
//        findViewById(R.id.point_animation_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,ObjectAnimation2Activity.class));//1
//            }
//        });
//
//        findViewById(R.id.bezier_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,BezierActivity.class));//2
//            }
//        });
//        findViewById(R.id.my_heart_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MyHeartViewActivity.class));//3
//            }
//        });
//
//        findViewById(R.id.circle_progress_bar_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,CircleProgressBarActivity.class));//4
//            }
//        });
//        findViewById(R.id.animation_frame_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,ObjectAnimationFrameActivity.class));//5
//            }
//        });
//        findViewById(R.id.animation_flash_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,FlashActivity.class));//6
//            }
//        });
//        findViewById(R.id.pay_test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PayPassportActivity.class));//7
//            }
//        });
//        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,GreenDaoActivity.class));//8
//            }
//        });
//        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,DialogActivity.class));//9
//            }
//        });
//        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,CollegeActivity.class));//10
//            }
//        });
//        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,JsonXmlActivity.class));
//            }
//        });
//        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,JsonBeanActivity.class));
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private boolean isExit;

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        } else {
            finish();
            FinishActivityManager.getManager().finishAllActivity();
        }
    }
}
