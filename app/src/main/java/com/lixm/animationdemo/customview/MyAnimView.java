package com.lixm.animationdemo.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.lixm.animationdemo.animator.ColorEvaluator;
import com.lixm.animationdemo.animator.DeToAcInterpolator;
import com.lixm.animationdemo.animator.PointEvaluator;
import com.lixm.animationdemo.bean.Point;

/**
 * @author Lixm
 * @date 2017/4/13
 * @detail
 */

public class MyAnimView extends View {
    private String TAG="MyAnimView";
    public static final float RADIUS=50f;
    private Point currentPoint;
    private Paint mPaint;
    private String color;


    public MyAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint==null){
            currentPoint=new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas){
        float x=currentPoint.getX();
        float y=currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    private void startAnimation(){
//        Point startPoint=new Point(RADIUS,RADIUS);
//        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        Point startPoint=new Point(getWidth()/2,RADIUS);
        Point endPoint=new Point(getWidth()/2,getHeight()-RADIUS);
        ValueAnimator animator=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint= (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
//        animator.setDuration(5000);
//        animator.start();
        ObjectAnimator animator1= ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#0000ff","#ff0000");
        AnimatorSet animSet=new AnimatorSet();
        animSet.play(animator).with(animator1);
        animSet.setDuration(5000);
//        animSet.setInterpolator(new AccelerateInterpolator(2f));
//        animSet.setInterpolator(new BounceInterpolator());
        animSet.setInterpolator(new DeToAcInterpolator());
        animSet.start();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }
}
