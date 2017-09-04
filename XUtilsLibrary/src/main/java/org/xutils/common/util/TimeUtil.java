package org.xutils.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: LXM
 * Date: 2016-04-14
 * Time: 13:53
 * Detail:时间工具类
 */
public class TimeUtil {
    /**
     * 获取系统当前日期时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        return date;
    }
}
