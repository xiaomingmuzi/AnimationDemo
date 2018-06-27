package com.lixm.animationdemo.bean;

import org.xutils.common.util.LogUtil;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Describe:
 * <p>
 * 强软弱虚创建对象
 * Author: Lixm
 * Date: 2018/6/27
 */
public class References {
    private static String TAG = "References";

    private static ReferenceQueue rq = new ReferenceQueue();

    public static void checkQueue() {
        Reference inq = rq.poll();
        //从队列中取出一个引用
        if (inq != null) {
            LogUtil.i(TAG, "In queue: " + inq + " : " + inq.get());
        }
    }

    public static void main(String[] args) {
        final int size = 10;
        //创建10个Grocery对象，以及10个软引用
        Set sa = new HashSet();
        for (int i = 0; i < size; i++) {
            SoftReference ref = new SoftReference(new Grocery("soft" + i), rq);
            LogUtil.i(TAG, "Just created soft: " + ref.get());
            sa.add(ref);
        }
        System.gc();
        checkQueue();

        LogUtil.i(TAG,"---------------------------------");
        //创建10个Grocery对象以及10个弱引用
        Set wa=new HashSet();
        for (int i=0;i<size;i++){
            WeakReference ref=new WeakReference(new Grocery("weak"+i),rq);
            LogUtil.i(TAG,"Just created weak: "+ref.get());
            wa.add(ref);
        }
        System.gc();
        checkQueue();

        System.out.println("=============================");
        //创建10个Grocery对象，以及10个虚引用
        Set pa=new HashSet();
        for (int i=0;i<size;i++){
            PhantomReference ref=new PhantomReference(new Grocery("Phantom "+i),rq);
            System.out.println("Just created Phantom :"+ref.get());
            pa.add(ref);
        }
        System.gc();
        checkQueue();
    }
}
