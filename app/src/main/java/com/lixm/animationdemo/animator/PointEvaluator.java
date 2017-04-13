package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;

import com.lixm.animationdemo.bean.Point;

/**
 * @author Lixm
 * @date 2017/4/13
 * @detail
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //fraction  完成度
        Point startPoint= (Point) startValue;
        Point endPoint= (Point) endValue;
        float x=startPoint.getX()+fraction*(endPoint.getX()-startPoint.getX()) ;
        float y=startPoint.getY()+fraction*(endPoint.getY()-startPoint.getY());
        Point point=new Point(x,y);
        return point;
    }
}
