package com.lixm.animationdemo.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lenovo on 2016/5/25.
 */
public class DetailsImageview extends ImageView {
    private int height;
    private int width;
    /**
     * 圆环宽度
     */
    private int paint_width = 6;
    /**
     * 圆环颜色
     */
    private String colors = "#a0c7c7c7";
    private Paint paint;
    private Paint paint1;

    public DetailsImageview(Context context) {
        super(context);
        initpaint();
    }

    public DetailsImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initpaint();
    }

    public DetailsImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initpaint();
    }

    private void initpaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(paint_width);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(colors));
        paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        Drawable drawable = getDrawable();
        if (drawable != null) {
            try {

                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                int bitmap_width = bitmap.getWidth();
                int bitmap_height = bitmap.getHeight();

                Rect src = new Rect(0, 0, bitmap_width, bitmap_height);
                Rect dst = new Rect(paint_width, paint_width, width - paint_width, height - paint_width);

                canvas.drawBitmap(bitmap, src, dst, paint1);

            } catch (Exception e) {
                e.toString();
            }
        }

        canvas.drawCircle(width / 2, height / 2, width / 2 - paint_width / 2, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (width != 0) {
            width = getWidth();
            height = getHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        width = right - left;
        height = bottom - top;
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 设置圆环宽度
     *
     * @param width width
     */
    public void setCircleWidth(int width) {
        paint_width = width;
    }

    /**
     * 设置圆环颜色
     *
     * @param colorString 如"#a0c7c7c7"
     */
    public void setCircleColor(String colorString) {
        colors = colorString;
    }
}
