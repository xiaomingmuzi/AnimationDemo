package com.lixm.animationdemo.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.animator.ColorEvaluator;
import com.lixm.animationdemo.utils.UIUtils;

import org.xutils.common.util.LogUtil;

/**
 * @author Lixm
 * @date 2018/6/13 17:16
 * @detail
 */

public class CircularPercentagView extends View {

    private Paint mPaint, mBgPaint, mCirclePaint, mEndBitPaint;
    private Path mCirclePath;

    private TextPaint mTxtPaint;

    private float ascent;
    private float descent;
    private float textOffset;
    private int width, height;

    private Bitmap mBgBitmap, mEndBit;
    private Rect mSrcRect;
    private RectF mDesRectF;
    private SweepGradient mSweepGradient;
    private AnimatorSet animSet;

    private int startAngle = 0;//起始角度
    private int sweepAngle = 0;//变化过程中角度
    private int endAngle = 135;//最终的颜色角度

    private int stroke_width;
    private String startColor, sweepColor, endColor;
    private int text_size;

    private float centerXY;//圆心
    private float radius;//半径
    private float bitmapWith = 0;//标志物宽度

    public CircularPercentagView(Context context) {
        this(context, null);
    }

    public CircularPercentagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularPercentagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularPercentagView,
                defStyleAttr, 0);
        stroke_width = array.getInteger(R.styleable.CircularPercentagView_stroke_width, 8);
        startColor = array.getString(R.styleable.CircularPercentagView_start_color);
        endColor = array.getString(R.styleable.CircularPercentagView_end_color);
        sweepColor=startColor;
        text_size = array.getInteger(R.styleable.CircularPercentagView_text_size, UIUtils.dip2px(12));
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);

        mBgPaint = new Paint();

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor(startColor));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setStrokeWidth(stroke_width);

        mCirclePath = new Path();

        mTxtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setAntiAlias(true);
        mTxtPaint.setColor(Color.parseColor(startColor));
        mTxtPaint.setTextAlign(Paint.Align.CENTER);
        mTxtPaint.setTextSize(UIUtils.dip2px(text_size));
        //文字的上坡度和下坡度。用于计算偏移量
        ascent = mTxtPaint.ascent();
        descent = mTxtPaint.descent();

        //偏移量，用于辅助文字居中
        textOffset = (ascent + descent) / 2;

        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.face);
        mSrcRect = new Rect(0, 0, mBgBitmap.getWidth(), mBgBitmap.getHeight());

        mEndBitPaint = new Paint();
        mEndBitPaint.setColor(Color.BLUE);
        mEndBitPaint.setStrokeCap(Paint.Cap.ROUND);
        mEndBitPaint.setAntiAlias(true);
        mEndBitPaint.setStrokeWidth(6);
        mEndBit = BitmapFactory.decodeResource(getResources(), R.mipmap.end_pic);
        bitmapWith = mEndBit.getWidth() / 2;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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
        super.onDraw(canvas);

        if (mDesRectF == null)
            mDesRectF = new RectF(16, 16, width - 16, height - 16);
        if (mSweepGradient == null)
            mSweepGradient = new SweepGradient(width / 2, height / 2, Color.parseColor(startColor), Color.parseColor(endColor));
        centerXY = width / 2;
        radius = centerXY - 16;

        canvas.drawCircle(width / 2, height / 2, width / 2, mPaint);
        canvas.drawBitmap(mBgBitmap, mSrcRect, mDesRectF, mBgPaint);

        mCirclePaint.setShader(mSweepGradient);
//        LogUtil.i("startAngle：" + startAngle + "  sweepAngle：" + sweepAngle);
//        mCirclePath.addArc(10, 10, width - 10, height - 10, startAngle, sweepAngle);
//        canvas.drawPath(mCirclePath, mCirclePaint);
        canvas.drawArc(mDesRectF,startAngle,sweepAngle,false,mCirclePaint);
        if (animSet == null) {
            startAnimation();
        }

        float x = (float) (centerXY + radius * Math.cos(sweepAngle * Math.PI / 180) - bitmapWith);
        float y = (float) (centerXY + radius * Math.sin(sweepAngle * Math.PI / 180) - bitmapWith);
        LogUtil.i("x：" + x + "  y：" + y);
        canvas.drawBitmap(mEndBit, x, y, mBgPaint);

        double d = Math.abs(sweepAngle) * 100 / 360;
        mTxtPaint.setColor(Color.parseColor(sweepColor));
        canvas.drawText((d + "%"), width  / 2, height  / 2 - textOffset, mTxtPaint);
    }


    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(startAngle, endAngle);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (Integer) animation.getAnimatedValue();
//                invalidate();
            }
        });
        animator.setRepeatCount(0);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), startColor, endColor);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LogUtil.w((String) animation.getAnimatedValue());
                setColor((String) animation.getAnimatedValue());
            }
        });
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator1.setRepeatCount(0);
        animSet = new AnimatorSet();
        animSet.playTogether(animator, animator1);
        animSet.setDuration(500);
        animSet.setInterpolator(new AccelerateInterpolator());
        animSet.start();
    }

    public String getColor() {
        return sweepColor;
    }

    public void setColor(String color) {
        sweepColor = color;
        invalidate();
    }

    public int getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(int endAngle) {
        this.endAngle = endAngle;
        sweepAngle = startAngle;
        animSet.start();
        invalidate();
    }
}
