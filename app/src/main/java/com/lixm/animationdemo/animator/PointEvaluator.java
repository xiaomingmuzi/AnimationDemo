package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

import com.lixm.animationdemo.bean.Point;

/**
 * @author Lixm
 * @date 2017/4/13
 * @detail
 */

public class PointEvaluator implements TypeEvaluator {
    private String TAG=getClass().getName();
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //fraction  完成度
        PointF startPoint= (PointF) startValue;
        PointF endPoint= (PointF) endValue;
        float x=startPoint.x+fraction*(endPoint.x-startPoint.x) ;
        float y=startPoint.y+fraction*(endPoint.y-startPoint.y);
        Point point=new Point(x,y);
        Log.e(TAG,"=================PointEvaluator==========");


        return point;
    }
}
