package com.lixm.animationdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Lixm
 * @date 2017/5/2
 * @detail https://github.com/Alex-Cin/CircleProgressBar
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
        initView();
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
//        firstProgressPaint.setStyle(Paint.Style.STROKE);
        firstProgressPaint.setStrokeWidth(firstProgressWidth);
        firstProgressPaint.setColor(firstProgressColor);

        secondProgressWidth = dp2Px(20);
        secondProgressColor = Color.parseColor("#6aa84f");
        secondProgressPaint = new Paint();
        secondProgressPaint.setAntiAlias(true);
        secondProgressPaint.setStyle(Paint.Style.STROKE);
        secondProgressPaint.setStrokeWidth(secondProgressWidth);
        secondProgressPaint.setColor(secondProgressColor);

        dotColor = Color.parseColor("#ff5722");
        dotDiameter = dp2Px(20);
        dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setAntiAlias(true);
        dotPaint.setColor(dotColor);

        firstRectF = new RectF(0, 0, 0, 0);
        secondRectF = new RectF(0, 0, 0, 0);
        maxRectF = new RectF(0, 0, 0, 0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int padding = getPaddingLeft() + getPaddingRight();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.max(result, size);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int padding = getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.max(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**偏移量*/
        float dMax = (dotDiameter - maxProgressWidth) * 0.5F;
        float dFirst = (dotDiameter - firstProgressWidth) * 0.5F;
        float dSecond = (dotDiameter - secondProgressWidth) * 0.5F;
        /*1.圆心（x,y）坐标值*/
        float centerX = (width - getPaddingLeft() - getPaddingRight()) / 2.0f;
        float centerY = (height - getPaddingTop() - getPaddingBottom()) / 2.0f;
        /*2.圆环半径*/
        float maxRadius = centerY - maxProgressWidth / 2;
        float firstRadius = centerY - firstProgressWidth / 2;
        float secondRadius = centerY - secondProgressWidth / 2;
        if (getWidth() >= getHeight()) {
            maxRadius = centerY - maxProgressWidth / 2;
            firstRadius = centerY - firstProgressWidth / 2;
            secondRadius = centerY - secondProgressWidth / 2;
        } else {
            maxRadius = centerX - maxProgressWidth / 2;
            firstRadius = centerX - firstProgressWidth / 2;
            secondProgressWidth = centerX - secondProgressWidth / 2;
        }
        maxRectF.left = centerX - maxRadius + dMax;
        maxRectF.right = centerX + maxRadius - dMax;
        maxRectF.top = centerY - maxRadius + dMax;
        maxRectF.bottom = centerY + maxRadius - dMax;

        firstRectF.left = centerX - firstRadius + dFirst;
        firstRectF.right = centerX + firstRadius - dFirst;
        firstRectF.top = centerY - firstRadius + dFirst;
        firstRectF.bottom = centerY + firstRadius - dFirst;

        secondRectF.left = centerX - secondRadius + dSecond;
        secondRectF.right = centerX + secondRadius - dSecond;
        secondRectF.top = centerY - secondRadius + dSecond;
        secondRectF.bottom = centerY + secondRadius - dSecond;

        canvas.drawArc(maxRectF, 0, 360, false, maxProgressPaint);

        float firstAngle = (float) 360 * firstProgress / (float) maxProgress;
        float secondAngle = (float) 360 * secondProgress / (float) maxProgress;
        float dotAngle = (float) (Math.PI * secondAngle / 180.0F);

        canvas.drawArc(firstRectF, -90, firstAngle, true, firstProgressPaint);
        canvas.drawArc(secondRectF, -90, secondAngle, false, secondProgressPaint);

        float dotCx = (float) (width * 0.5 + (width - dotDiameter) * 0.5 * Math.sin(dotAngle));
        float dotCy = (float) (height * 0.5 - (height - dotDiameter) * 0.5 * Math.cos(dotAngle));
        if (canDisplayDot) {
            canvas.drawCircle(dotCx, dotCy, dotDiameter * 0.5F, dotPaint);
        }
    }

    /**
     * @param dotDiameter 设置圆点的直径，单位dp
     */
    public void setDotDiameter(float dotDiameter) {
        this.dotDiameter = dp2Px(dotDiameter);
        dotPaint.setStrokeWidth(this.dotDiameter);
        invalidate();
    }

    /**
     * @param dotColor 设置dot的颜色
     */
    public void setDotColor(int dotColor) {
        this.dotColor = dotColor;
        dotPaint.setColor(dotColor);
    }

    /**
     * @param maxProgressColor
     */
    public void setMaxProgressColor(int maxProgressColor) {
        this.maxProgressColor = maxProgressColor;
        maxProgressPaint.setColor(maxProgressColor);
    }

    /**
     * 设置 Max 的宽度，单位 dp
     * 必须在 setDotDiameter 之后调用
     */
    public void setMaxProgressWidth(float maxProgressWidth) {
        this.maxProgressWidth = dp2Px(maxProgressWidth);
        maxProgressPaint.setStrokeWidth(this.maxProgressWidth);
        invalidate();
    }

    /**
     * 设置 第一个进度条 的宽度，单位 dp
     * 必须在 setDotDiameter 之后调用
     */
    public void setFirstProgressWidth(float firstProgressWidth) {
        this.firstProgressWidth = dp2Px(firstProgressWidth);
        if (this.firstProgressWidth > dotDiameter) {
            this.firstProgressWidth = dotDiameter;
        }
        firstProgressPaint.setStrokeWidth(this.firstProgressWidth);
        invalidate();
    }


    public void setFirstProgressColor(int firstProgressColor) {
        this.firstProgressColor = firstProgressColor;
        firstProgressPaint.setColor(firstProgressColor);
    }

    public int getFirstProgressColor() {
        return firstProgressColor;
    }

    /**
     * 设置 第二个进度条 的宽度，单位 dp
     * 必须在 setDotDiameter 之后调用
     */
    public void setSecondProgressWidth(float secondProgressWidth) {
        this.secondProgressWidth = dp2Px(secondProgressWidth);
        if (this.secondProgressWidth > dotDiameter) {
            this.secondProgressWidth = dotDiameter;
        }
        secondProgressPaint.setStrokeWidth(this.secondProgressWidth);
        invalidate();
    }


    /**
     * 给  firstProgress 设置颜色
     */
    public void setSecondProgressColor(int secondProgressColor) {
        this.secondProgressColor = secondProgressColor;
        secondProgressPaint.setColor(secondProgressColor);
    }

    public int getSecondProgressColor() {
        return secondProgressColor;
    }

    /**
     * 获取 firstProgress  的进度值
     */
    public int getFirstProgress() {
        return firstProgress;
    }

    /**
     * @param firstProgress 给firstProgress设置进度 [0,100]
     */
    public void setFirstProgress(int firstProgress,UpdateProgress updateProgress) {
        if (firstProgress > maxProgress) {
            firstProgress = maxProgress;
        } else if (firstProgress < 0) {
            firstProgress = 0;
        }

        if (secondProgress > firstProgress) {
            secondProgress = firstProgress;
            updateProgress.updateSecondProgress(secondProgress);
        } else if (secondProgress < 0) {
            secondProgress = 0;
        }

        this.firstProgress = firstProgress;
        invalidate();

    }

    public void setFirstProgress(int firstProgress, long delay) {
        new ProgressThread(firstProgress, whatFirstProgress, delay).start();
    }

    /**
     * 给  secondProgress 设置进度, [0,100 ]
     */
    public void setSecondProgress(int secondProgress,UpdateProgress updateProgress) {
        if (secondProgress > maxProgress) {
            secondProgress = maxProgress;
        } else if (secondProgress < 0) {
            secondProgress = 0;
        }
        if (secondProgress > firstProgress) {
            firstProgress = secondProgress;
            updateProgress.updateFirstProgress(firstProgress);
        }
        this.secondProgress = secondProgress;
        invalidate();
    }

    /**
     * 给  secondProgress 设置进度, [0,100 ]
     */
    public void setSecondProgress(int secondProgress, long delay) {
        new ProgressThread(secondProgress, whatSecondProgress, delay).start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    class ProgressThread extends Thread {
        private int progress;
        private int what;
        private long delay;

        public ProgressThread(int progress, int what, long delay) {
            this.progress = progress;
            this.what = what;
            this.delay = delay;
        }

        @Override
        public void run() {
            for (int i = 0; i < progress; i++) {
                Message msg = Message.obtain();
                msg.arg1 = i;
                msg.what = what;
                progressHandler.sendMessage(msg);
                SystemClock.sleep(delay / progress);
            }
        }
    }

    private final class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (whatFirstProgress == msg.what) {
                if (firstProgress > maxProgress) {
                    firstProgress = maxProgress;
                } else if (firstProgress < 0) {
                    firstProgress = 0;
                }

                firstProgress = msg.arg1;
                invalidate();
            } else if (whatSecondProgress == msg.what) {
                if (secondProgress > maxProgress) {
                    secondProgress = maxProgress;
                } else if (secondProgress < 0) {
                    secondProgress = 0;
                }
                secondProgress = msg.arg1;
                invalidate();

            }

        }
    }

    /**
     * 可以展示 小圆点
     */
    public void setCanDisplayDot(boolean canDisplayDot) {
        this.canDisplayDot = canDisplayDot;
        invalidate();
    }

    /**
     * 数据转换: dp---->px
     */
    private float dp2Px(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }

   public interface UpdateProgress{
        public void updateFirstProgress(int progress);
       public void updateSecondProgress(int progress);
    }
}
