package com.lixm.animationdemo;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * @author Lixm
 * @date 2017/4/25
 * @detail
 */

public class UIUtils {
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
