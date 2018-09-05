package com.lixm.animationdemo.Other;

import org.xutils.common.util.LogUtil;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/31
 */
public class B extends A {//定义子类，继承
// 抽象类

    @Override
    public void print() {//覆写抽象方法
        LogUtil.i("hello world！！！");
    }
}
