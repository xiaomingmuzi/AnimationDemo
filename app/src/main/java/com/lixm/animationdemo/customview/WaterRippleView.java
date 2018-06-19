package com.lixm.animationdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.utils.UIUtils;


/**
 * Created by lxf on 2017/7/23.
 * 水波纹效果
 */
public class WaterRippleView extends View {

    private String TAG = "WaterRippleView";
    //控件的整体宽度（到波纹最远处）
    private int mWidth;
    //控件的整体高度（到波纹最远处）
    private int mHeight;
    //绘制波纹的画笔
    private Paint mPaint;

    //水波纹的个数。最多有几条波纹
    private int wavesCount;
    //一条水波纹的宽度
    private int wavesWidth;
    //中心图片的直径（宽度）
    private int centerDiameter;
    //做动画的时间，单位是毫秒
    private int runningTime = 50;
    //水波纹每次变化的增加的幅度
    private int runningStep = 5;

    //控件中心宽度一半 - 中心图片直径的一半
    private int mMaxStrokeWidth;

    private int[] mStrokeWidthArr;

    private boolean isRunning = false;

    public WaterRippleView(Context context) {
        this(context, null);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaterRippleView);
        int waveColor = typedArray.getColor(R.styleable.WaterRippleView_wavesColor,
                ContextCompat.getColor(context, R.color.water_ripple_color));

        wavesCount = typedArray.getInt(R.styleable.WaterRippleView_wavesCount, 2);
        centerDiameter = typedArray.getInt(R.styleable.WaterRippleView_centerDiameter, 2);
        wavesWidth = typedArray.getDimensionPixelSize(R.styleable.WaterRippleView_wavesWidth,
                16);
        isRunning = typedArray.getBoolean(R.styleable.WaterRippleView_wavesAutoRunning, false);
        runningTime = typedArray.getInt(R.styleable.WaterRippleView_runningTime, 100);
        runningStep = typedArray.getInt(R.styleable.WaterRippleView_runningStep, 5);
        typedArray.recycle();

        centerDiameter = UIUtils.dip2px(centerDiameter);

        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(waveColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mStrokeWidthArr != null && isRunning) return;
        int size = (wavesCount * wavesWidth + centerDiameter / 2) * 2;
        mWidth = resolveSize(size, widthMeasureSpec);
        mHeight = mWidth;
        setMeasuredDimension(mWidth, mHeight);
        mMaxStrokeWidth = (mWidth - centerDiameter) / 2;
        initArray();
    }

    private void initArray() {
        mStrokeWidthArr = new int[wavesCount];
        for (int i = 0; i < mStrokeWidthArr.length; i++) {
            mStrokeWidthArr[i] = -mMaxStrokeWidth / wavesCount * i;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isRunning) {
            drawRipple(canvas);
            postInvalidateDelayed(runningTime);
        }
    }


    private void drawRipple(Canvas canvas) {
        for (int strokeWidth : mStrokeWidthArr) {
            if (strokeWidth < 0) {
                continue;
            }
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setAlpha(255 - 255 * strokeWidth / mMaxStrokeWidth);
            canvas.drawCircle(mWidth / 2, mHeight / 2, centerDiameter / 2 + strokeWidth / 2,
                    mPaint);
        }

        for (int i = 0; i < mStrokeWidthArr.length; i++) {
            if ((mStrokeWidthArr[i] += runningStep) > mMaxStrokeWidth) {
                mStrokeWidthArr[i] = 0;
            }
        }
    }

    public void start() {
        if (isRunning) return;
        isRunning = true;
        postInvalidate();
    }

    public void stop() {
        if (!isRunning) return;
        isRunning = false;
        postInvalidate();
    }

    public boolean isRunning() {
        return isRunning;
    }

}

