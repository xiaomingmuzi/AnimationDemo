package com.lixm.animationdemo.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.activity.event_bus.EventBus1Activity;
import com.lixm.animationdemo.customview.FlowLayout;
import com.lixm.animationdemo.utils.MD5;
import com.lixm.liveplayerlibrary.LivePlayerActivity;
import com.lixm.liveplayerlibrary.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;


/**
 * http://blog.csdn.net/guolin_blog/article/details/43536355  上篇
 * http://blog.csdn.net/guolin_blog/article/details/43816093  中篇
 * http://blog.csdn.net/guolin_blog/article/details/44171115  下篇
 */
public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    private FlowLayout mFlowLayout;
    private String[] names = new String[]{"圆形动画进度", "简单的animation实现", "anko测试", "地铁票计算", "PointAnimation实现", "bezier", "myheart",
            "圆形进度条", "属性动画代替帧动画", "Flash动画", "支付密码框", "GreenDao数据库测试",
            "Dialog展示", "recyclerView测试", "json2xml测试", "json测试", "音频录音", "短视频播放",
            "手势Demo", "PlayerView测试", "Random测试", "FixureProgressBar", "浏览器接口测试",
            "Butterknife插件测试", "获取证书信息", "音频录音动画", "JNIDemo", "Kotlin天气预报界面",
            "MessengerDemo", "IBookManager", "全屏详情", "全屏滑动", "发送消息", "音频播放",
            "主题更换1", "主题更换2", "DialogFragment测试", "输入框滑动冲突", "EventBus测试", "手势解锁View",
            "压缩图片", "Glide学习", "Gson解析","跑马灯"

    };
    private Class<?>[] classes = new Class[]{CircularPercentagActivity.class, ObjectAnimation1Activity.class, AnkoActivity.class, SubwayActivity.class, ObjectAnimation2Activity.class, BezierActivity.class, MyHeartViewActivity.class,
            CircleProgressBarActivity.class, ObjectAnimationFrameActivity.class, FlashActivity.class, PayPassportActivity.class, GreenDaoActivity.class,
            DialogActivity.class, CollegeActivity.class, JsonXmlActivity.class, JsonBeanActivity.class, RecordSoundActivity.class, LivePlayerActivity.class,
            GestureDemoActivity.class, CollegePlayerActivity.class, RandomActivity.class, FixurePositionProgressBarActivity.class, WebViewActivity.class,
            ButterknifeActivity.class, CertificateFactoryActivity.class, AudioRecoderActivity.class, JNIDemoActivity.class, WeatherMainActivity.class,
            MessengerActivity.class, BookManagerctivity.class, FullScreenDisplayStockInformationActivity.class, FullScrollLayoutActivity.class, MessageActivity.class, MediaPlayerActivity.class,
            ApkThemeActivity.class, ApkThemeJavaActivity.class, DialogFragmentActivity.class, EditTextActivity.class, EventBus1Activity.class, GestureSecretActivity.class,
            UserImageActivity.class, GlideTestActivity.class, GsonDemoActivity.class,MarqueeActivity.class
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
//        mFlowLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                startActivity(checkedId);
//            }
//        });
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android
                .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        //创建一个强引用
        String str=new String("hello");//1
        //创建引用队列；表明队列中存放String对象的引用ReferenceQueue;
        ReferenceQueue rq=new ReferenceQueue();//2
        //创建一个弱引用，它引用"hello"对象，并且与rq引用队列关联 表明WeakReference会弱引用String对象
        WeakReference wf=new WeakReference(str,rq);//3
        str=null;//4 取消"hello" 对象的强引用
        //两次催促垃圾回收器工作，提高“hello”对象被回收的可能
        System.gc();//5
        System.gc();//6
        String str1= (String) wf.get();//7 假如“hello”对象没有被回收，str1引用“hello”对象；
        // 假如“hello”对象没有被回收，rq.poll()返回null
        Reference ref=rq.poll();//8

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //创建文件夹
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        saveInSdCard("vote_1517451025.amr");
                    }
                    break;
                }
        }
    }


    private void startActivity(int poi) {
        Intent intent = new Intent(this, classes[poi]);
        startActivity(intent);
    }

    private void addData() {
        /**
         * 放数据
         */
        if (names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                //创建textView，并设置属性
                final RadioButton radioButton = new RadioButton(this);
                radioButton.setPadding(10, 5, 10, 5);
                radioButton.setId(i);
                Bitmap a = null;
                radioButton.setButtonDrawable(new BitmapDrawable(a));
//                textView.setTextSize(UIUtils.dp2Px(14));
                radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setTextColor(getResources().getColor(R.color.choose_bank_txt));
                radioButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.bank_item_choose));
                //给textView设置内容
                radioButton.setText(names[i]);
                if (i == 0) {
                    radioButton.setChecked(true);
                }
                //把textView放到flowLayout中
                mFlowLayout.addView(radioButton);
                final int finalI = i;
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (radioButton.isChecked()) {
                            startActivity(finalI);
                        }
                    }
                });
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

    /*
     * 保存到sb卡内
     * @param fileName 必须是完整文件名（文件名+格式）
     * @param bitmap
     */
    private void saveInSdCard(String filename) {
        InputStream fileStream = null;
        try {
            //获取指定Assets文件流
            fileStream = getResources().getAssets().open(filename);

            //检查是否存在sd卡
            String status = Environment.getExternalStorageState();
            if (!status.equals(Environment.MEDIA_MOUNTED)) {
                LogUtil.e("=================检查是否存在sd卡=================");
                Toast.makeText(this, "请插入sd卡", Toast.LENGTH_LONG).show();
                return;
            }

        /*
         * 在Android中1.5、1.6的sdcard目录为/sdcard，而Android2.0以上都是/mnt/sdcard，因此如果我们在保存时直接写具体目录会不妥，因此我们可以使用:
         * Environment.getExternalStorageDirectory();获取sdcard目录；
         */
            String directory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.lixm.animationdemo/lindiSecret";
            //在文件夹下加入获取的文件
            File file = new File(directory, filename);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                LogUtil.i("文件路径：" + file.getAbsolutePath());
                //文件输出流
                FileOutputStream out = new FileOutputStream(file);
                byte[] data = new byte[1024];
                int length = fileStream.read(data);
                while (length > 0) {
                    out.write(data, 0, length);
                    length = fileStream.read(data);
                }
                out.flush();
                out.close();
            } else {
                LogUtil.w("文件已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
