package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

import com.lixm.animationdemo.bean.Point;

import static android.R.attr.width;

/**
 * Created by zhuxuanmuyu on 2017/4/16.
 */

public class BezierTypeEvaluator implements TypeEvaluator<PointF> {
private String TAG=getClass().getName();
    private PointF mPointF1;
    private PointF mPointF2;

    public BezierTypeEvaluator(PointF pointF1, PointF pointF2) {
        this.mPointF1 = pointF1;
        this.mPointF2 = pointF2;
    }


    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

//        final float t = fraction;
//        float oneMinusT = 1.0f - t;
//        PointF point = new PointF();
//
//        PointF point0 = (PointF)startValue;
//
//        PointF point1 = new PointF();
//        point1.set(width, 0);
//
//        PointF point2 = new PointF();
//        point2.set(0, height);
//
//        PointF point3 = (PointF)endValue;
//
//        point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
//                + 3 * oneMinusT * oneMinusT * t * (point1.x)
//                + 3 * oneMinusT * t * t * (point2.x)
//                + t * t * t * (point3.x);
//
//        point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
//                + 3 * oneMinusT * oneMinusT * t * (point1.y)
//                + 3 * oneMinusT * t * t * (point2.y)
//                + t * t * t * (point3.y);
        float time = 1 - fraction;
        PointF point = new PointF();// 结果

        point.x = time * time * time * startValue.x + 3 * mPointF1.x * time
                * time * fraction + 3 * mPointF2.x * fraction * fraction * time
                + endValue.x * fraction * fraction * fraction;

        point.y = time * time * time * startValue.y + 3 * mPointF1.y * time
                * time * fraction + 3 * mPointF2.y * fraction * fraction * time
                + endValue.y * fraction * fraction * fraction;
        Log.i(TAG,point.x+"===="+point.y);
        return point;
    }
}
