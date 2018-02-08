package com.lixm.animationdemo.application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.lixm.animationdemo.db.DaoMaster;
import com.lixm.animationdemo.db.DaoSession;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import org.litepal.LitePalApplication;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * @author Lixm
 * @date 2017/7/6
 * @detail
 */

public class MyApplication1 extends LitePalApplication {
    private static MyApplication1 mContext = null;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyApplication1 instances;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instances = this;
        setDatabase();
        //对xUtils进行初始化
        x.Ext.init(this);
        //是否是开发、调试模式
        x.Ext.setDebug(true, true);//是否输出debug日志，开启debug会影响性能
        //设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        //设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        //设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        //设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        //补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                LogUtil.w("补丁下载地址：" + patchFile);
//                Toast.makeText(getApplication(), "补丁下载地址：" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
//                Toast.makeText(getApplication(), String.format(Locale.getDefault(), "%s %d%%",
//                        Beta.strNotificationDownloading, (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
//                Toast.makeText(getApplication(), "补丁下载成功："+msg, Toast.LENGTH_SHORT).show();
                LogUtil.w("补丁下载成功：" + msg);
            }

            @Override
            public void onDownloadFailure(String msg) {
//                Toast.makeText(getApplication(), "补丁下载失败："+msg, Toast.LENGTH_SHORT).show();
                LogUtil.w("补丁下载失败：" + msg);
            }

            @Override
            public void onApplySuccess(String msg) {
//                Toast.makeText(getApplication(), "补丁应用成功："+msg, Toast.LENGTH_SHORT).show();
                LogUtil.w("补丁应用成功：" + msg);
            }

            @Override
            public void onApplyFailure(String msg) {
//                Toast.makeText(getApplication(), "补丁应用失败："+msg, Toast.LENGTH_SHORT).show();
                LogUtil.w("补丁应用失败：" + msg);
            }

            @Override
            public void onPatchRollback() {
                LogUtil.w("==============onPatchRollback======");
            }
        };
        //设置开发设备，默认为false，上传补丁如果下发范围指定为"开发设备"，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(getApplication(), true);
        //这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), "5ca40d59fb", true);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }

    public static MyApplication1 getInstances() {
        return instances;
    }

    // 对外暴露上下文
    public static MyApplication1 getApplication() {
        return mContext;
    }

    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "l_db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
