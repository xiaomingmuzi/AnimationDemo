package com.lixm.animationdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.lixm.animationdemo.application.MyApplication1;


/**
 * @author Lixm
 * @date 2017/4/25
 * @detail
 */

public class UIUtils {
    public static int slide_status = -1;
    /**
     * 获取上下文Context
     *
     * @return
     */
    public static Context getContext() {
        return MyApplication1.getInstances();
    }
    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
