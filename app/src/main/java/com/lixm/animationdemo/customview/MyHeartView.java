package com.lixm.animationdemo.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.UIUtils;
import com.lixm.animationdemo.animator.BezierTypeEvaluator;

import java.util.Random;

import static android.R.attr.x;
import static android.R.attr.y;
import static android.os.Build.VERSION_CODES.M;


/**
 * @author Lixm
 * @date 2017/4/24
 * @detail
 */

public class MyHeartView extends RelativeLayout {
    private String TAG=getClass().getName();
    private Context mContext;
    private Drawable[] mDrawables;
    private Drawable mDrawabeBlue,mDrawablePink,mDrawableGreen;

    private LayoutParams mDrawableLp;
    private Random random;
    private int screenWidth;
    private int screenHeight;


    public MyHeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        mDrawables=new Drawable[3];
        mDrawabeBlue=getResources().getDrawable(R.mipmap.heart3);
        mDrawablePink=getResources().getDrawable(R.mipmap.heart4);
        mDrawableGreen=getResources().getDrawable(R.mipmap.heart5);
        mDrawables[0]=mDrawabeBlue;
        mDrawables[1]=mDrawablePink;
        mDrawables[2]=mDrawableGreen;
        mDrawableLp=new LayoutParams(120, 120);
        mDrawableLp.addRule(CENTER_HORIZONTAL,TRUE);
        mDrawableLp.addRule(ALIGN_PARENT_BOTTOM,TRUE);
        random=new Random();
        screenWidth=UIUtils.getScreenWidth((Activity) mContext);
        screenHeight=UIUtils.getScreenHeight((Activity) mContext);
    }

    public void addImg(){
        for(int i=0;i<10;i++){
            ImageView heartImg = new ImageView(mContext);
            heartImg.setImageDrawable(mDrawables[random.nextInt(3)]);
            heartImg.setLayoutParams(mDrawableLp);
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            this.addView(heartImg);
            startAnimation(heartImg);
        }
    }

    private void startAnimation(ImageView heartImg){
        //实现贝塞尔曲线
//        PointF point1 = new PointF();
//        point1.set((UIUtils.getScreenWidth((Activity) mContext)-80)/4, (-80)/4*3 );
//
//        PointF point2 = new PointF();
//        point2.set((UIUtils.getScreenWidth((Activity) mContext)-80)/3,( UIUtils.getScreenHeight((Activity) mCntext)-80)/4 );
//50-80 随机值
int x=screenWidth/4;
        int y=screenWidth/4*3;
        int randomNum= (int) (50+Math.random()*30);

        BezierTypeEvaluator bezierEvaluator = new BezierTypeEvaluator(getPointF(2), getPointF(1));
        ValueAnimator va = ValueAnimator.ofObject(bezierEvaluator,new PointF(y+x/2 ,
               screenHeight - 120), new PointF((float) (y+Math.random()*x), 0));
        va.addUpdateListener(new UpdateListener(heartImg));
        va.setTarget(heartImg);
        va.setDuration(3000);
        // 设置缩放效果
        ObjectAnimator alpha = ObjectAnimator.ofFloat(heartImg,"alpha", 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(heartImg, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(heartImg,View.SCALE_Y, 0.2f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.playTogether(alpha, scaleX, scaleY);
        set.setInterpolator(new LinearInterpolator());

        AnimatorSet finalSet = new AnimatorSet();
//        finalSet.playSequentially(set);
//        finalSet.playSequentially(set, va);
        finalSet.playTogether(set,va);
        finalSet.addListener(new HeartAnimatorlistener(heartImg));
        finalSet.start();
    }

    /**
     * 获取中间的两个 点
     * @param scale
     */
    private PointF getPointF(int scale) {
        PointF pointF = new PointF();
//        pointF.x = random.nextInt((screenWidth - 100));//减去100 是为了控制 x轴活动范围,看效果 随意~~
        int x=screenWidth/4;
        int y=screenWidth/4*3;
        pointF.x=(float) (y+Math.random()*x);
        //再Y轴上 为了确保第二个点 在第一个点之上,我把Y分成了上下两半 这样动画效果好一些 也可以用其他方法
        pointF.y = random.nextInt((screenHeight - 100))/scale;
        return pointF;
    }
    private class UpdateListener implements ValueAnimator.AnimatorUpdateListener {

        View target;

        public UpdateListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointf = (PointF) animation.getAnimatedValue();
            target.setX(pointf.x);
            target.setY(pointf.y);
//            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    private class HeartAnimatorlistener implements Animator.AnimatorListener {

        private View target;

        public HeartAnimatorlistener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            MyHeartView.this.removeView(target);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
