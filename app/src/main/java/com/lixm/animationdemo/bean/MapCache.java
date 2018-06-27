package com.lixm.animationdemo.bean;

import java.lang.ref.SoftReference;
import java.util.Scanner;
import java.util.WeakHashMap;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/6/27
 */
public class MapCache {
    public static void main(String[] args)throws Exception{
//        int size=1000;//或者从命令行获得Size的大小
        System.out.println("请输入size大小：");
        Scanner sc=new Scanner(System.in);
        String sizeStr=sc.next();
        int size=Integer.parseInt(sizeStr);
        if (args.length>0) {
            size = Integer.parseInt(args[0]);
        }
        Key[] keys=new Key[size];//存放键对象的强引用
        WeakHashMap whm=new WeakHashMap();
        for (int i=0;i<size;i++){
            Key k=new Key(Integer.toString(i));
            Value v=new Value(Integer.toString(i));
            if (i%3==0){
                keys[i]=k;//使key对象持有强引用
            }
            whm.put(k,v);//是key对象持有弱引用
        }
        //催促垃圾回收器工作
        System.gc();//把CPU让给垃圾回收器线程
        Thread.sleep(8000);
    }

    private void getData() {
        Grocery grocery=new Grocery("Soft");
        SoftReference aSoft=new SoftReference(grocery);
        grocery=null;
        Grocery anotherRef=(Grocery) aSoft.get();
        SoftReference ref=null;
        while((ref =  (SoftReference) rq.poll()) != null) {
            //清除ref
        }
    }
}
