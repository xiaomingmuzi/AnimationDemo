package com.lixm.animationdemo.bean;

import org.xutils.common.util.LogUtil;

/**
 * Describe:
 * <p>
 *     强软弱虚创建对象
 *
 * Author: Lixm
 * Date: 2018/6/27
 */
public class Grocery {
    private String TAG=getClass().getName();
    private static final int SIZE=10000;
    //属性d使得每个Grocery对象占用较多的内存，有80k左右
    private double[] d=new double[SIZE];
    private String id;

    public Grocery(String id) {
        this.id = id;
    }

    public String toString(){
        return id;
    }

    public void finalize(){
        LogUtil.i(TAG,"Finalizing "+id);
    }
}
