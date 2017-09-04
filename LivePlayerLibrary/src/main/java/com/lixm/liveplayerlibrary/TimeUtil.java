package com.lixm.liveplayerlibrary;

import java.util.Formatter;
import java.util.Locale;

/**
 * @author LXM
 * @ClassName: TimeUtil
 * @Description: 有关时间的工具类
 * @date 2015-9-24 上午11:14:12
 */
public class TimeUtil {


    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public TimeUtil() {
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

    }
    /**
     * 时间 秒数 转成 时分秒 式的字符串
     * @param second
     * @return
     */
    public static String secondToString(int second) {
        String time = "";
        int temp1, temp2, temp3;
        if (second < 10) {  //几秒钟的视频
            time = "0:0" + second;  //0:05
        } else if (second < 60) {
            time = "0:" + second;//0:25
        } else if (second < 3600) {//当前剩余时间小于1小时
            temp1 = second % 60;
            if (temp1 < 10)
                time = (second / 60) + ":0" + second % 60;
            else
                time = (second / 60) + ":" + second % 60;
        } else {
            temp1 = second / 3600;
            temp2 = second % 3600 / 60;
            temp3 = second % 3600 % 60;
            if (temp2 < 10)
                time = temp1 + ":0" + temp2;
            else
                time = temp1 + ":" + temp2;
            if (temp3 < 10)
                time = time + ":0" + temp3;
            else
                time = time + ":" + temp3;
        }
        return time;
    }
}
