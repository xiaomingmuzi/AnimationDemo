package com.lixm.animationdemo.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.PluginBean;
import com.lixm.liveplayerlibrary.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dalvik.system.PathClassLoader;

public class ApkThemeJavaActivity extends BaseActivity {

    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_theme);
        img = findViewById(R.id.img);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<HashMap<String, String>> datas = new ArrayList<>();
//                List<PluginBean> plugins = findAllPlugin();
//                if (plugins != null && !plugins.isEmpty()) {
//                    for (PluginBean bean : plugins) {
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("label", bean.getLabel());
//                        datas.add(map);
//                    }
                    try {
                        int recourseID = dynamicLoadApk("com.lixm.animationdemo", ApkThemeJavaActivity.this);
                        LogUtil.w("资源ID："+recourseID);
                        if (recourseID!=0){
                            img.setBackgroundResource(recourseID);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                } else {
//                    Toast.makeText(ApkThemeJavaActivity.this, "没有找到插件，请先下载！", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }

    /**
     * 查找手机内所有的插件
     *
     * @return 返回一个插件List
     */
    private List<PluginBean> findAllPlugin() {
        List<PluginBean> plugins = new ArrayList<>();
        PackageManager pm = getPackageManager();
        //通过包管理器查找所有已安装的apk文件
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo info : packageInfos) {
            //得到当前apk的包名
            String pkgName = info.packageName;
            //得到当前apk的sharedUserId
            String shareUesrId = info.sharedUserId;
            //判断这个apk是否是我们应用程序的插件
            if (shareUesrId != null && shareUesrId.equals("com.lixm.animationdemo") && !pkgName.equals(this.getPackageName())) {
                String label = pm.getApplicationLabel(info.applicationInfo).toString();//得到插件apk的名称
                PluginBean bean = new PluginBean(label, pkgName);
                plugins.add(bean);
            }
        }
        return plugins;
    }

    /**
     * 加载已安装的apk
     *
     * @param packageName   应用的包名
     * @param pluginContext 插件app的上下文
     * @return 对应资源的id
     */
    private int dynamicLoadApk(String packageName, Context pluginContext) throws Exception {
        //第一个参数为包含dex的apk或者jar的路径，第二个参数为父加载器
        PathClassLoader pathClassLoader = new PathClassLoader(pluginContext.getPackageResourcePath(), ClassLoader.getSystemClassLoader());
//        Class<?> clazz = pathClassLoader.loadClass(packageName + ".R$mipmap");//通过使用自身的加载器反射出mipmap类进而使用该类的功能
        //参数：1、类的全名，2、是否初始化类，3、加载时使用的类加载器
        String name=packageName + ".R$mipmap";
        LogUtil.w("name："+name);
        Class<?> clazz = Class.forName(name, true, pathClassLoader);
        //使用上述两种方式都可以，这里我们得到R类中的内部类mipmap，通过它得到对应的图片id，进而给我们使用
        Field field = clazz.getDeclaredField("add_stock_fund_default");
        int resourceId = field.getInt(R.mipmap.class);
        return resourceId;
    }

    private void threadPool(){
        int NUMBER_OF_CORES=Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME=1;
        TimeUnit KEEP_ALIVE_TIME_UNIT= TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue=new LinkedBlockingQueue<>();

        ExecutorService executorService=new ThreadPoolExecutor(NUMBER_OF_CORES,NUMBER_OF_CORES*2,
                KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT,taskQueue,new BackgroundThreadFactory(),new DefaultRejectedExecutionHandler());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<1000){
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    class BackgroundThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return null;
        }
    }
    class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }
}
