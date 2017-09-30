package com.lixm.animationdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

/**
 * @author Lixm
 * @date 2017/9/29
 * @detail 预设进度条允许播放的位置，实现试看几分钟的需求提醒
 */

public class FixurePositionProgressBar extends View {

    private static final String TAG = "FixureProgressBar";
    private Context mContext;
    private int mMaxProgress = 100;//预设进度条最大值
    private int mCurrentProgress = 0;//当前的进度条位置
    private int mReachedBarColor;//进度条背景色
    private int mUnReachedBarColor;//进度颜色
    private float mReachedBarHeight;//进度条高度
    private float mUnReachedBarHeight;//进度高度
    private int mFixurePosition;//预设位置
    private int mFixureColor;//预设位置，颜色值

    private final int default_reach_color = Color.rgb(66, 145, 241);
    private final int default_unreach_color = Color.rgb(204, 204, 204);
    private final int default_fixure_color = Color.rgb(255, 0, 0);
    private final int default_fixure_position = 10;
    private final int FIXURE_POSITION_VISIBLE = 0;

    private RectF mUnreachedRectF = new RectF(0, 0, 0, 0);
    private RectF mReachedRectF = new RectF(0, 0, 0, 0);
    private RectF mFixureRectF = new RectF(0, 0, 0, 0);
    private Paint mReachBarePaint;
    private Paint mUnReachBarPaint;
    private Paint mFixurePaint;

    private OnProgressBarListener onProgressBarListener;
    private boolean mIfDrawFixure = true;//是否需要绘制预设点
    private boolean mDrawUnreachedBar = true;//需要绘制当前进度条
    private boolean mDrawReachedBar = true;//需要绘制进度条背景


    public enum FixurePositionVisibility {
        Visible, Invisible
    }

    public FixurePositionProgressBar(Context context) {
        this(context, null);
    }

    public FixurePositionProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public FixurePositionProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mReachedBarHeight = dp2px(4.0f);
        mUnReachedBarHeight = dp2px(6.0f);

        //load styled attributes
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FixurePositionProgressBar,
                defStyleAttr, 0);
        mReachedBarColor = attributes.getColor(R.styleable.FixurePositionProgressBar_progress_reached_color, default_reach_color);
        mUnReachedBarColor = attributes.getColor(R.styleable.FixurePositionProgressBar_progress_unreached_color, default_unreach_color);

        mReachedBarHeight = attributes.getDimension(R.styleable.FixurePositionProgressBar_progress_reached_bar_height, mReachedBarHeight);
        mUnReachedBarHeight = attributes.getDimension(R.styleable.FixurePositionProgressBar_progress_unreached_bar_height, mUnReachedBarHeight);

        mFixurePosition = attributes.getInteger(R.styleable.FixurePositionProgressBar_progress_fixure_position, default_fixure_position);
        mFixureColor = attributes.getInteger(R.styleable.FixurePositionProgressBar_progress_fixure_color, default_fixure_color);

        int fixureVisible = attributes.getInt(R.styleable.FixurePositionProgressBar_progress_fixure_position_visibility, FIXURE_POSITION_VISIBLE);
        if (fixureVisible != FIXURE_POSITION_VISIBLE)
            mIfDrawFixure = false;

        setProgress(attributes.getInt(R.styleable.FixurePositionProgressBar_progress_current, mCurrentProgress));
        setMax(attributes.getInt(R.styleable.FixurePositionProgressBar_progress_max, mMaxProgress));

        attributes.recycle();
        initializePainters();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWith) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWith ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWith ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWith) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (getProgress() >= mFixurePosition || mUnreachedRectF.right == mReachedRectF.right) {
//            if (onProgressBarListener != null)
//                onProgressBarListener.onProgressFinish(getProgress());
//            return;
//        }
        if (mIfDrawFixure) {
            //计算预设点的位置
            calculateDrawFixurePosition();
        }
            //计算没有预设点的进度条
            calculateDrawWithoutFixure();

        if (mDrawUnreachedBar) {//画整体进度条背景
            canvas.drawRect(mUnreachedRectF, mUnReachBarPaint);
        }

        if (mIfDrawFixure) {
            //画预设点
            canvas.drawRect(mFixureRectF, mFixurePaint);
        }

        if (mDrawReachedBar) {//画当前进度
            canvas.drawRect(mReachedRectF, mReachBarePaint);
            LogUtil.e("---------"+mReachedRectF.left);
            if (onProgressBarListener != null)
                onProgressBarListener.onProgressChange(getProgress(), getMax());
        }
    }

    /**
     * 计算进度条绘制的背景
     */
    private void calculateDrawWithoutFixure() {

        mUnreachedRectF.left = mReachedRectF.left;
        mUnreachedRectF.right = getWidth() - getPaddingRight();
        mUnreachedRectF.top = getHeight() / 2.0f - mUnReachedBarHeight / 2.0f;
        mUnreachedRectF.bottom = getHeight() / 2.0f + mUnReachedBarHeight / 2.0f;

        mReachedRectF.left = getPaddingLeft();
        mReachedRectF.top = getHeight() / 2.0f - mReachedBarHeight / 2.0f;
        mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getMax() * 1.0f) * getProgress() + getPaddingLeft();
        mReachedRectF.bottom = getHeight() / 2.0f + mReachedBarHeight / 2.0f;

    }

    /**
     * 设置预设点的位置
     */
    private void calculateDrawFixurePosition() {
        mFixureRectF.left = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getMax() * 1.0f) * getFixurePosition() + getPaddingLeft();
        mFixureRectF.right = mFixureRectF.left + 10 < mUnreachedRectF.right ? mFixureRectF.left + 4 : mUnreachedRectF.right;
//        mFixureRectF.left =100;
//        mFixureRectF.right = 104;
        mFixureRectF.top = mReachedRectF.top;
        mFixureRectF.bottom = mReachedRectF.bottom;
    }


    /**
     * 初始化画笔
     */
    private void initializePainters() {
        mReachBarePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mReachBarePaint.setColor(mReachedBarColor);

        mUnReachBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnReachBarPaint.setColor(mUnReachedBarColor);

        mFixurePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFixurePaint.setColor(mFixureColor);
    }

    public int getFixurePosition() {
        return mFixurePosition;
    }

    public void setFixurePosition(int fixurePosition) {
        this.mFixurePosition = fixurePosition;
    }

    public int getProgress() {
        return mCurrentProgress;
    }

    public void setProgress(int progress) {
        if (progress <= getMax() && progress >= 0) {
            this.mCurrentProgress = progress;
            LogUtil.w("====currentProgress："+progress);
            invalidate();
        }
    }


    public int getMax() {
        return mMaxProgress;
    }

    public void setMax(int maxProgress) {
        if (maxProgress > 0) {
            this.mMaxProgress = maxProgress;
            invalidate();
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle=new Bundle();
        return bundle;
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public void setOnProgressBarListener(OnProgressBarListener listener) {
        onProgressBarListener = listener;
    }

    public interface OnProgressBarListener {
        void onProgressChange(int current, int max);

        void onProgressFinish(int finishPoi);
    }
}
