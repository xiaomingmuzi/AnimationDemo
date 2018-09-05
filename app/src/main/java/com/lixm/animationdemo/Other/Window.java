package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public interface Window {//定义Window接口，表示窗口操作

    public void open();//打开

    public void close();//关闭

    public void activated();//窗口活动

    public void iconified();//窗口最小化

    public void deiconified();//窗口恢复大小
}
