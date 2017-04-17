package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;

import com.lixm.animationdemo.bean.Point;

/**
 * Created by zhuxuanmuyu on 2017/4/16.
 */

public class BezierTypeEvaluator implements TypeEvaluator<Point> {

    private int width;
    private int height;

    public void BezierTypeEvaluator(int width, int height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        final float t = fraction;
        float oneMinusT = 1.0f - t;
        Point point = new Point();

        Point point0 = (Point) startValue;

        Point point1 = new Point();
        point1.setX(width);
        point1.setY(0);

        Point point2 = new Point();
        point2.setX(0);
        point2.setY(height);


        Point point3 = (Point) endValue;

        point.setX(oneMinusT * oneMinusT * oneMinusT * (point0.getX())
                + 3 * oneMinusT * oneMinusT * t * (point1.getX())
                + 3 * oneMinusT * t * t * (point2.getX())
                + t * t * t * (point3.getX()));

        point.setY( oneMinusT * oneMinusT * oneMinusT * (point0.getY())
                + 3 * oneMinusT * oneMinusT * t * (point1.getY())
                + 3 * oneMinusT * t * t * (point2.getY())
                + t * t * t * (point3.getY()));
        return point;
    }
}
