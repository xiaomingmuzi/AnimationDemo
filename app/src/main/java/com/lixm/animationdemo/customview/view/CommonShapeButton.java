package com.lixm.animationdemo.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;

import com.lixm.animationdemo.R;


/**
 * Describe:自定义View，省略shape文件设置背景颜色、圆角、按下交互等
 * <p>
 * Author: Lixm
 * Date: 2018/9/11
 */
public class CommonShapeButton extends AppCompatButton {

    /**
     * shape模式
     * 矩形（rectangle）、椭圆形（oval）、线性（line）、环形（ring）
     */
    private int mShapeMode = 0;
    private int mFillColor = 0;//填充颜色
    private int mPressedColor = 0; //按压颜色
    private int mStrokeColor = 0;//描边颜色
    private int mStrokeWidth = 0; //描边宽度
    private int mCornerRadius = 0; //圆角半径
    private int mCornerPosition = 0;//圆角位置
    private boolean mActiveEnable = false; //点击动效
    private int mStartColor = 0; //起始颜色
    private int mEndColor = 0; //结束颜色

    private static final int TOP_LEFT = 1;
    private static final int TOP_RIGHT = 2;
    private static final int BOTTOM_RIGHT = 4;
    private static final int BOTTOM_LEFT = 8;

    /**
     * 渐变方向
     * 0-GradientDrawable.Orientation.TOP_BOTTOM
     * 1-GradientDrawable.Orientation.LEFT_RIGHT
     */
    private int mOrientation = 0;
    /**
     * drawable位置
     * -1-null、0-left、1-top、2-right、3-bottom
     */
    private int mDrawablePosition = -1;

    /**
     * 普通shape样式
     */
    private GradientDrawable normalGradientDrawable = new GradientDrawable();

    /**
     * 按压shape样式
     */
    private GradientDrawable pressedGradientDrawable = new GradientDrawable();

    /**
     * shape样式集合
     */
    private StateListDrawable stateListDrawable = new StateListDrawable();

    //button内容总宽度
    private float contentWidth = 0f;
    //button内容总高度
    private float contentHeight = 0f;

    public CommonShapeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CommonShapeButton);
        mShapeMode = array.getInt(R.styleable.CommonShapeButton_csb_shapeMode, 0);
        mFillColor = array.getColor(R.styleable.CommonShapeButton_csb_fillColor, 0xffffffff);
        mPressedColor = array.getColor(R.styleable.CommonShapeButton_csb_pressedColor, 0xff666666);
        mStrokeColor = array.getColor(R.styleable.CommonShapeButton_csb_strokeColor, 0);
        mStrokeWidth = array.getDimensionPixelSize(R.styleable.CommonShapeButton_csb_strokeWidth, 0);
        mCornerRadius = array.getDimensionPixelSize(R.styleable.CommonShapeButton_csb_cornerRadius, 0);
        mActiveEnable = array.getBoolean(R.styleable.CommonShapeButton_csb_activeEnable, false);
        mCornerPosition = array.getInt(R.styleable.CommonShapeButton_csb_drawablePosition, -1);
        mDrawablePosition = array.getInt(R.styleable.CommonShapeButton_csb_drawablePosition, -1);
        mStartColor = array.getColor(R.styleable.CommonShapeButton_csb_startColor, 0xffffffff);
        mEndColor = array.getColor(R.styleable.CommonShapeButton_csb_endColor, 0xffffffff);
        mOrientation = array.getColor(R.styleable.CommonShapeButton_csb_orientation, 0);
        array.recycle();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mStartColor != 0xFFFFFFFF && mEndColor != 0xffffffff) {
            normalGradientDrawable.setColors(new int[]{mStartColor, mEndColor});
            switch (mOrientation) {
                case 0:
                    normalGradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    break;
                case 1:
                    normalGradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            }
        } else {
            normalGradientDrawable.setColor(mFillColor);
        }
        switch (mShapeMode) {
            case 0:
                normalGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                break;
            case 1:
                normalGradientDrawable.setShape(GradientDrawable.OVAL);
                break;
            case 2:
                normalGradientDrawable.setShape(GradientDrawable.LINE);
                break;
            case 3:
                normalGradientDrawable.setShape(GradientDrawable.RING);
                break;
        }
        if (mCornerPosition == -1) {
            normalGradientDrawable.setCornerRadius(mCornerRadius);
        } else {
            normalGradientDrawable.setCornerRadii(getCornerRadiusByPosition());
        }
        if (mStrokeColor != 0) {
            normalGradientDrawable.setStroke(mStrokeWidth, mStrokeColor);
        }

        Drawable drawable;
        if (mActiveEnable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = new RippleDrawable(ColorStateList.valueOf(mPressedColor), normalGradientDrawable, null);
            } else {
                pressedGradientDrawable.setColor(mPressedColor);
                switch (mShapeMode) {
                    case 0:
                        pressedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                        break;
                    case 1:
                        pressedGradientDrawable.setShape(GradientDrawable.OVAL);
                        break;
                    case 2:
                        pressedGradientDrawable.setShape(GradientDrawable.LINE);
                        break;
                    case 3:
                        pressedGradientDrawable.setShape(GradientDrawable.RING);
                        break;
                }
                pressedGradientDrawable.setCornerRadius(mCornerRadius);
                pressedGradientDrawable.setStroke(mStrokeWidth, mStrokeColor);
                //注意此处的add顺序,normal必须放在最后一个，否则其他状态无效
                //设置pressed状态
                stateListDrawable.addState(new int[]{
                        android.R.attr.state_pressed
                }, pressedGradientDrawable);
                //设置normal状态
                stateListDrawable.addState(new int[]{}, normalGradientDrawable);
                drawable = stateListDrawable;
            }
        } else drawable = normalGradientDrawable;
        setBackground(drawable);
    }

    private float[] getCornerRadiusByPosition() {
        float[] result = new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
        float cornerRadius = mCornerRadius;
        if (containsFlag(mCornerPosition, TOP_LEFT)) {
            result[0] = cornerRadius;
            result[1] = cornerRadius;
        }
        if (containsFlag(mCornerPosition, TOP_RIGHT)) {
            result[2] = cornerRadius;
            result[3] = cornerRadius;
        }
        if (containsFlag(mCornerPosition, BOTTOM_RIGHT)) {
            result[4] = cornerRadius;
            result[5] = cornerRadius;
        }
        if (containsFlag(mCornerPosition, BOTTOM_LEFT)) {
            result[6] = cornerRadius;
            result[7] = cornerRadius;
        }
        return result;
    }

    private boolean containsFlag(int flagSet, int flag) {
        return (flagSet | flag) == flagSet;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //如果xml中配置了drawable则设置padding让文字移动到边缘与drawable靠在一起
        //button中配置的drawable默认贴着边缘
        if (mDrawablePosition > -1) {
            if (getCompoundDrawables() != null) {
                Drawable drawable = getCompoundDrawables()[mDrawablePosition];
                if (drawable != null) {
                    switch (mDrawablePosition) {
                        case 0:
                        case 2:
                            //图片宽度
                            int drawableWidth = drawable.getIntrinsicWidth();
                            //获取文字宽度
                            float textWidth = getPaint().measureText(getText().toString());
                            //内容总宽度
                            contentWidth = textWidth + drawableWidth + getCompoundDrawablePadding();
                            int rightPadding = (int) (getWidth() - contentWidth);
                            //图片和文字全部靠在左侧
                            setPadding(0, 0, rightPadding, 0);
                            break;
                        case 1:
                        case 3:
                            //图片高度
                            int drawableHeight = drawable.getIntrinsicHeight();
                            //获取文字高度
                            Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
                            //单行高度
                            float singeLineHeight = (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
                            //总的行间距
                            int totalLineSpaceHeight = (int) ((getLineCount() - 1) * getLineSpacingExtra());
                            float textHeight = singeLineHeight * getLineCount() + totalLineSpaceHeight;
                            //内容总高度
                            contentHeight = textHeight + drawableHeight + getCompoundDrawablePadding();
                            //图片和文字全部靠在上侧
                            int bottomPadding = (int) (getHeight() - contentHeight);
                            setPadding(0, 0, 0, bottomPadding);
                            break;
                    }
                }
            }
        }
        //内容居中
        setGravity(Gravity.CENTER);
        //可点击
        setClickable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //让图片和文字居中
        if (contentWidth > 0 && (mDrawablePosition == 0 || mDrawablePosition == 2)) {
            canvas.translate((getWidth() - contentWidth) / 2, 0f);
        }
        if (contentHeight > 0 && (mDrawablePosition == 1 || mDrawablePosition == 3)) {
            canvas.translate(0f, (getHeight() - contentHeight) / 2);
        }
        super.onDraw(canvas);
    }
}
