package com.lixm.animationdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Lixm
 * @date 2017/5/2
 * @detail
 */

public class CircleProgressBar extends View {

    private Context mContext;
    private final int maxProgress = 100;
    private int firstProgress;
    private int secondProgress;
    private Paint maxProgressPaint;
    private Paint firstProgressPaint;
    private Paint secondProgressPaint;
    private Paint dotPaint;
    private int maxProgressColor;
    private int firstProgressColor;
    private int secondProgressColor;
    private int dotColor;
    private int width;
    private int height;
    private float maxProgressWidth;
    private float firstProgressWidth;
    private float secondProgressWidth;
    /**
     * 小圆点的直径
     */
    private float dotDiameter;

    private RectF maxRectF;
    private RectF firstRectF;
    private RectF secondRectF;

    private static final int whatFirstProgress = 100;
    private static final int whatSecondProgress = 101;
    private ProgressHandler progressHandler;
    /**
     * 是否展示小圆点
     */
    private boolean canDisplayDot;

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    private void initView() {
        canDisplayDot = true;

        progressHandler = new ProgressHandler();
        maxProgressWidth = dp2Px(20);
        maxProgressColor = Color.parseColor("#f3f3f3");
        maxProgressPaint = new Paint();
        maxProgressPaint.setAntiAlias(true);
        maxProgressPaint.setStyle(Paint.Style.STROKE);
        maxProgressPaint.setStrokeWidth(maxProgressWidth);
        maxProgressPaint.setColor(maxProgressColor);

        firstProgressWidth = dp2Px(20);
        firstProgressColor = Color.parseColor("#fd8957");
        firstProgressPaint = new Paint();
        firstProgressPaint.setAntiAlias(true);
        firstProgressPaint.setStrokeWidth(firstProgressWidth);
        firstProgressPaint.setColor(firstProgressColor);

        secondProgressWidth=dp2Px(20);
        secondProgressColor=Color.parseColor("#6aa84f");
        secondProgressPaint=new Paint();
        secondProgressPaint.setAntiAlias(true);
        secondProgressPaint.setStyle(Paint.Style.STROKE);
        secondProgressPaint.setStrokeWidth(secondProgressWidth);
        secondProgressPaint.setColor(secondProgressColor);

        dotColor=Color.parseColor("#ff5722");
        dotDiameter=dp2Px(20);
        dotPaint=new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setAntiAlias(true);
        dotPaint.setColor(dotColor);

        firstRectF=new RectF(0,0,0,0);
        secondRectF=new RectF(0,0,0,0);
        maxRectF=new RectF(0,0,0,0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=measureWidth(widthMeasureSpec);
        height=measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    private int measureWidth(int widthMeasureSpec){
        int result;
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int padding=getPaddingLeft()+getPaddingRight();
        if (mode==MeasureSpec.EXACTLY){
            result=size;
        }else{
            result=getSuggestedMinimumHeight();
            result+=padding;
            if (mode==MeasureSpec.AT_MOST){
                result=Math.max(result,size);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec){
        int result;
        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int padding=getPaddingTop()+getPaddingBottom();
        if (mode==MeasureSpec.EXACTLY){
            result=size;
        }else {
            result=getSuggestedMinimumHeight();
            result+=padding;
            if (mode==MeasureSpec.AT_MOST){
                result=Math.max(result,size);
            }
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        /**偏移量*/
        float dMax=(dotDiameter-maxProgressWidth)*0.5F;
        float dFirst=(dotDiameter-firstProgressWidth)*0.5F;
        float dSecond=(dotDiameter-secondProgressWidth)*0.5F;
        /*1.圆心（x,y）坐标值*/
        float centerX=(width-getPaddingLeft()-getPaddingRight())/2.0f;
        float centerY=(height-getPaddingTop()-getPaddingBottom())/2.0f;
        /*2.圆环半径*/
        float maxRadius=centerY-maxProgressWidth/2;
        float firstRadius=centerY-firstProgressWidth/2;
        float secondRadius=centerY-secondProgressWidth/2;
        if (getWidth()>=getHeight()){
            maxRadius=centerY-maxProgressWidth/2;
            firstRadius=centerY-firstProgressWidth/2;
            secondRadius=centerY-secondProgressWidth/2;
        }else{
            maxRadius=centerX-maxProgressWidth/2;
            firstRadius=centerX-firstProgressWidth/2;
            secondProgressWidth=centerX-secondProgressWidth/2;
        }
    }

    private final class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
        }
    }

    /**
     * 数据转换: dp---->px
     */
    private float dp2Px(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }
}
