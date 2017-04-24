package com.lixm.animationdemo.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.animator.BezierTypeEvaluator;
import com.lixm.animationdemo.animator.ColorEvaluator;
import com.lixm.animationdemo.animator.DeToAcInterpolator;
import com.lixm.animationdemo.animator.PointEvaluator;
import com.lixm.animationdemo.bean.Point;

import static android.R.attr.start;
import static android.R.attr.startY;
import static android.R.attr.tag;
import static com.lixm.animationdemo.customview.MyAnimView.RADIUS;

/**
 * @author Lixm
 * @date 2017/4/14
 * @detail
 */

public class MyAnimPathView extends View {
    private String TAG = getClass().getName();
    private Context mContext;
    //    private Button btn;
    private int width;
    private int height;
    private Paint mPaint;
    private Path mPath;
    private PointF startPoint;
    private PointF endPoint;
    private PointF controlPoint;
    private String color;

    public MyAnimPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        Log.i(TAG, "===========onCreate========");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.w(TAG, "========onMeasure=======");
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        initView();
    }

    private void initView() {
//        LayoutInflater.from(mContext).inflate(R.layout.bezier_layout,this);
//        btn= (Button) findViewById(R.id.button);


//        PointF startPoint=new PointF(width/2,height);
//        PointF endPoint=new PointF(width/2,0);
//        ValueAnimator valueAnimator=ValueAnimator.ofObject(new BezierTypeEvaluator(),startPoint,endPoint);
//        valueAnimator.setDuration(3000);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                PointF point= (PointF) animation.getAnimatedValue();
//                btn.setX(point.x);
//                btn.setY(point.y);
//            }
//        });
//        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "=========onDraw=========");
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPath = new Path();
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(10);
            mPaint.setStyle(Paint.Style.STROKE);
            startPoint = new PointF(0, 0);
            endPoint = new PointF(980, 980);
            controlPoint = new PointF(width / 3, height / 4);
            startAnimation();
        }

        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        mPath.reset();
        //起点
//        mPath.moveTo(startPoint.x, startPoint.y);
        //mPath
//        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
//        mPath.cubicTo(90,90,250,250,980,980);
//        mPath.moveTo(100, 500);
        mPath.cubicTo(100, 500, 300, 100,980, 980);
        //画path
        canvas.drawPath(mPath, mPaint);
        //画控制点
        canvas.drawPoint(controlPoint.x, controlPoint.y, mPaint);
        //画线
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y, mPaint);
        canvas.drawLine(endPoint.x, endPoint.y, controlPoint.x, controlPoint.y, mPaint);
    }

    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                PointF currentPoint = (PointF) animation.getAnimatedValue();
//                startPoint = currentPoint;
//                invalidate();
//            }
//        });
        ObjectAnimator animator1 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000ff", "#ff0000");
        AnimatorSet animSet = new AnimatorSet();
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
