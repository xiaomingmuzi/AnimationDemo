package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;
import android.util.Log;

import com.lixm.animationdemo.bean.Point;

/**
 * @author Lixm
 * @date 2017/4/14
 * @detail
 */

public class PathEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint= (Point) startValue;
        Point endPoint= (Point) endValue;
        float x=startPoint.getX()+fraction*(endPoint.getX()-startPoint.getX()) ;
        float y=startPoint.getY()+fraction*(endPoint.getY()-startPoint.getY());
        Point point=new Point(x,y);

        Log.i("tag","-----------");
        return point;
    }
}
