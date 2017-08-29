package com.lixm.animationdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import org.xutils.common.util.LogUtil;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public class DeviceUtils {
    /**
     * 获得系统亮度
     *
     * @return
     */
    public static float getBrightnessPercent(Context context) {
        Activity activity = (Activity) context;
        WindowManager.LayoutParams layout = activity.getWindow().getAttributes();
        LogUtil.w("========MAX："+layout.screenBrightness);
        return layout.screenBrightness;
    }

    /**
     * 改变App当前Window亮度
     *
     * @param percent
     */
    public static void setBrightness(Context context, float percent) {
        if (!(context instanceof Activity)) {
            return;
        }
        if (percent < 0.01f) {
            percent = 0.01f;
        } else if (percent > 1.0f) {
            percent = 1.0f;
        }
        Activity activity = (Activity) context;
        WindowManager.LayoutParams layout = activity.getWindow().getAttributes();
        layout.screenBrightness = percent;
        activity.getWindow().setAttributes(layout);
    }

}
