package com.lixm.animationdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
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
import com.lixm.liveplayerlibrary.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

import dalvik.system.PathClassLoader;


/**
 * http://blog.csdn.net/guolin_blog/article/details/43536355  上篇
 * http://blog.csdn.net/guolin_blog/article/details/43816093  中篇
 * http://blog.csdn.net/guolin_blog/article/details/44171115  下篇
 */
public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    private FlowLayout mFlowLayout;
    private String[] names = new String[]{"简单的animation实现", "anko测试", "地铁票计算", "PointAnimation实现", "bezier", "myheart",
            "圆形进度条", "属性动画代替帧动画", "Flash动画", "支付密码框", "GreenDao数据库测试",
            "Dialog展示", "recyclerView测试", "json2xml测试", "json测试", "音频录音", "短视频播放",
            "手势Demo", "PlayerView测试", "Random测试", "FixureProgressBar", "浏览器接口测试",
            "Butterknife插件测试", "获取证书信息", "音频录音动画", "JNIDemo", "Kotlin天气预报界面",
            "MessengerDemo", "IBookManager", "全屏详情", "全屏滑动", "发送消息", "音频播放",
            "主题更换"

    };
    private Class<?>[] classes = new Class[]{ObjectAnimation1Activity.class, AnkoActivity.class, SubwayActivity.class, ObjectAnimation2Activity.class, BezierActivity.class, MyHeartViewActivity.class,
            CircleProgressBarActivity.class, ObjectAnimationFrameActivity.class, FlashActivity.class, PayPassportActivity.class, GreenDaoActivity.class,
            DialogActivity.class, CollegeActivity.class, JsonXmlActivity.class, JsonBeanActivity.class, RecordSoundActivity.class, LivePlayerActivity.class,
            GestureDemoActivity.class, CollegePlayerActivity.class, RandomActivity.class, FixurePositionProgressBarActivity.class, WebViewActivity.class,
            ButterknifeActivity.class, CertificateFactoryActivity.class, AudioRecoderActivity.class, JNIDemoActivity.class, WeatherMainActivity.class,
            MessengerActivity.class, BookManagerctivity.class, FullScreenDisplayStockInformationActivity.class, FullScrollLayoutActivity.class, MessageActivity.class, MediaPlayerActivity.class,
            ApkThemeJavaActivity.class
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
        saveInSdCard("vote_1517451025.amr");

        try {
            dynamicLoadApk("com.lixm.animationdemo",this);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String directory = Environment.getExternalStorageDirectory().toString() + "/lindiSecret";
            File rootFile = new File(directory);
            //如不存在文件夹，则新建文件夹
            if (!rootFile.exists())
                rootFile.mkdirs();
            //在文件夹下加入获取的文件
            File file = new File(directory, filename);
            if (!file.exists())
                file.createNewFile();

            //文件输出流
            FileOutputStream out = new FileOutputStream(file);
            //先定义一个字节缓冲区，减少I/O次数，提高读写效率
            byte[] buffer = new byte[10240];
            int size = 0;
            while ((size = fileStream.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
            LogUtil.e("=================文件输出流=================");
            out.flush();
            out.close();
            fileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载已安装的apk
     * @param packageName 应用的包名
     * @param pluginContext 插件app的上下文
     * @return 对应资源的id
     */
    private int dynamicLoadApk(String packageName, Context pluginContext) throws Exception {
        //第一个参数为包含dex的apk或者jar的路径，第二个参数为父加载器
        PathClassLoader pathClassLoader = new PathClassLoader(pluginContext.getPackageResourcePath(),ClassLoader.getSystemClassLoader());
//        Class<?> clazz = pathClassLoader.loadClass(packageName + ".R$mipmap");//通过使用自身的加载器反射出mipmap类进而使用该类的功能
        //参数：1、类的全名，2、是否初始化类，3、加载时使用的类加载器
        Class<?> clazz = Class.forName(packageName + ".R$mipmap", true, pathClassLoader);
        //使用上述两种方式都可以，这里我们得到R类中的内部类mipmap，通过它得到对应的图片id，进而给我们使用
        Field field = clazz.getDeclaredField("ic_launcher");
        int resourceId = field.getInt(R.mipmap.class);
        LogUtil.i("resourceId："+resourceId);
        return resourceId;
    }

}
