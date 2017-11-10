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


/**
 * http://blog.csdn.net/guolin_blog/article/details/43536355  上篇
 * http://blog.csdn.net/guolin_blog/article/details/43816093  中篇
 * http://blog.csdn.net/guolin_blog/article/details/44171115  下篇
 */
public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    private FlowLayout mFlowLayout;
    private String[] names = new String[]{"简单的animation实现", "PointAnimation实现", "bezier", "myheart",
            "圆形进度条", "属性动画代替帧动画", "Flash动画", "支付密码框", "GreenDao数据库测试",
            "Dialog展示", "recyclerView测试", "json2xml测试", "json测试", "音频录音", "短视频播放",
            "手势Demo", "PlayerView测试", "Random测试", "FixureProgressBar", "浏览器接口测试",
            "Butterknife插件测试", "获取证书信息", "音频录音动画", "JNIDemo","Kotlin天气预报界面"
    };
    private Class<?>[] classes = new Class[]{ObjectAnimation1Activity.class, ObjectAnimation2Activity.class, BezierActivity.class, MyHeartViewActivity.class,
            CircleProgressBarActivity.class, ObjectAnimationFrameActivity.class, FlashActivity.class, PayPassportActivity.class, GreenDaoActivity.class,
            DialogActivity.class, CollegeActivity.class, JsonXmlActivity.class, JsonBeanActivity.class, RecordSoundActivity.class, LivePlayerActivity.class,
            GestureDemoActivity.class, CollegePlayerActivity.class, RandomActivity.class, FixurePositionProgressBarActivity.class, WebViewActivity.class,
            ButterknifeActivity.class, CertificateFactoryActivity.class, AudioRecoderActivity.class, JNIDemoActivity.class, WeatherMainActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.channel);
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("UMENG_CHANNEL");
            textView.setText(channel);
            textView.setText(MD5.getMD5LowerCase(MD5.getMD5String("Hello")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mFlowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        mFlowLayout.setPadding(12, 12, 12, 12);
        addData();
        mFlowLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                startActivity(checkedId);
            }
        });

    }

    private void startActivity(int poi) {
//        if (poi == classes.length) {
//
//        } else {
            Intent intent = new Intent(this, classes[poi]);
            startActivity(intent);
//        }
    }

    private void addData() {
        /**
         * 放数据
         */
        if (names.length > 0) {
            for (int i = 0; i < names.length; i++) {
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
                textView.setText(names[i]);
                if (i == 0) {
                    textView.setChecked(true);
                }
                //把textView放到flowLayout中
                mFlowLayout.addView(textView);
            }
        }
    }


    @Override
    public void onBackPressed() {
        exit();
    }

    private boolean isExit = false;

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再点一次退出APP", Toast.LENGTH_SHORT).show();
        } else {
            finish();
            FinishActivityManager.getManager().finishAllActivity();
        }
    }

}
