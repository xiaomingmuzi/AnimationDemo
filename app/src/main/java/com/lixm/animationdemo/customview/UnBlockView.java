package com.lixm.animationdemo.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.utils.UIUtils;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

/**
 * @author Lixm
 * @date 2018/4/11
 * @detail 解屏手势根布局
 */

public class UnBlockView extends LinearLayout implements View.OnTouchListener {

    private Context mContext;
    private Paint mPaint;
    private Path mPath;
    private float mX;
    private float mY;

    private ArrayList<NodeView> mNodes;
    private ArrayList<NodeView> validNodes;
    private boolean isValid;

    private float topY;
    private int statusBarHeight1 = -1;
    private StringBuffer secret;


    public UnBlockView(Context context) {
        super(context);
        initView(context);
    }

    public UnBlockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        setOrientation(VERTICAL);

        setBackgroundColor(Color.parseColor("#00000000"));
        this.setOnTouchListener(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();

        secret = new StringBuffer("");
        mNodes = new ArrayList<>();
        validNodes = new ArrayList<>();
        getStatusBarHeight();
        initNodeView();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        topY = (UIUtils.getScreenHeight((Activity) mContext) - UIUtils.getScreenWidth((Activity) mContext)) / 2;//起点
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.topMargin = (int) topY;
        params.bottomMargin = (int) topY;
        params.gravity = Gravity.CENTER;
        setLayoutParams(params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                NodeView nodeView = isExists(mX, mY);
                if (nodeView != null) {
                    if (!validNodes.contains(nodeView)) {
                        validNodes.add(nodeView);
                        isValid = true;
                        nodeView.setImageResource(R.mipmap.gesture_choose);
                        int location[] = new int[2];
                        nodeView.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含statusBar
                        //移动到中心点
                        mPath.moveTo(location[0] + nodeView.getWidth() / 2, location[1] + nodeView.getHeight() / 2 - topY - statusBarHeight1);
                        secret.append(nodeView.getNumber());
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isValid) return true;
                float currentX = event.getX();
                float currentY = event.getY();

                NodeView nodeView2 = isExists(currentX, currentY);
                if (nodeView2 != null) {
                    if (!validNodes.contains(nodeView2)) {
                        validNodes.add(nodeView2);
                        if ((Math.abs(mX - currentX) > 3 || Math.abs(mY - currentY) > 3)) {
                            nodeView2.setImageResource(R.mipmap.gesture_choose);
                            int location[] = new int[2];
                            nodeView2.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含statusBar
                            mPath.lineTo(location[0] + nodeView2.getWidth() / 2, location[1] + nodeView2.getHeight() / 2 - topY - statusBarHeight1);

                            mX = currentX;
                            mY = currentY;
                            secret.append(nodeView2.getNumber());
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.w("密码：" + secret.toString());
                if (secret.toString().equals("14789")) {
                    Toast.makeText(mContext, "解锁成功！", Toast.LENGTH_SHORT).show();
                }
                reset();
                break;
        }
        invalidate();
        return true;
    }

    private void initNodeView() {

        for (int i = 1; i < 4; i++) {

            LinearLayout line = new LinearLayout(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            params.weight = 1;
            line.setBackgroundColor(Color.parseColor("#00ffffff"));
            params.gravity = Gravity.CENTER;
            line.setOrientation(LinearLayout.HORIZONTAL);
            line.setLayoutParams(params);


            for (int j = 1; j < 4; j++) {
                RelativeLayout item = new RelativeLayout(mContext);
                LinearLayout.LayoutParams itemParams = new LayoutParams(0, UIUtils.dip2px(50));

                itemParams.weight = 1;
                item.setLayoutParams(itemParams);

                line.addView(item);

                NodeView nodeView = new NodeView(mContext);
                RelativeLayout.LayoutParams nodeParams = new RelativeLayout.LayoutParams(UIUtils.dip2px(50), UIUtils.dip2px(50));
                nodeParams.addRule(CENTER_IN_PARENT);
                nodeParams.alignWithParent = true;
                nodeView.setLayoutParams(nodeParams);

                nodeView.setNumber(i * 3 - 3 + j);

                item.addView(nodeView);
                mNodes.add(nodeView);

            }
            addView(line);
        }
    }

    /**
     * 点在矩阵中
     *
     * @param x
     * @param y
     * @return
     */
    private NodeView isExists(float x, float y) {
        NodeView nodeView = null;
        for (int i = 0; i < mNodes.size(); i++) {
            NodeView item = mNodes.get(i);
            if (isInViewArea(item, x, y)) {
                nodeView = item;
                return nodeView;
            }
        }
        return nodeView;
    }

    /**
     * 判断一个点在某个view内
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private boolean isInViewArea(View view, float x, float y) {
        int location[] = new int[2];
        view.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含statusBar
        if (x > location[0] && x < location[0] + view.getWidth() && y > location[1] - topY && y < location[1] + view.getHeight() - topY) {
            return true;
        }
        return false;
    }

    private void reset() {
        secret.delete(0, secret.length());
        mPath.reset();
        isValid = false;
        for (NodeView item : mNodes) {
            item.setImageResource(R.mipmap.gesture_normal);
        }
        validNodes.clear();
    }

    /**
     * 获取状态栏高度——方法1
     */
    private void getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        LogUtil.e("状态栏-方法1:" + statusBarHeight1);
    }
}
