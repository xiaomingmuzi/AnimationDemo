package com.lixm.animationdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixm.animationdemo.R;

public class ObjectAnimation1Activity extends AppCompatActivity {

    private TextView textView;
    private Button alphaBtn, rotationBtn, translationXBtn, scaleXBtn, animationSetBtn,animationSetXmlBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation1);

        //        final ValueAnimator anim=ValueAnimator.ofInt(0,100);
//        anim.setDuration(100);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int currentValue= (int) animation.getAnimatedValue();
//                Log.i(TAG,"current value is "+currentValue);
//            }
//        });
//        anim.start();


        textView = (TextView) findViewById(R.id.text);

        //将一个TextView在5秒中内从常规变换成全透明，再从全透明变换成常规
        alphaBtn = (Button) findViewById(R.id.alpha_btn);
        alphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f, 1f);
                animator.setDuration(5000);
                animator.start();
            }
        });

        rotationBtn = (Button) findViewById(R.id.rotation_btn);
        rotationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "rotation", 0f, 360f);
                animator.setDuration(5000);
                animator.start();
            }
        });

        final float curTranslationX = textView.getTranslationX();
        translationXBtn = (Button) findViewById(R.id.translationX_btn);
        translationXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationX", curTranslationX, -500f, curTranslationX);
                animator.setDuration(5000);
                animator.start();
            }
        });

        scaleXBtn = (Button) findViewById(R.id.scaleX_btn);
        scaleXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //X方向放大三倍
                ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "scaleX", 0f, 3f, 0f);
                animator.setDuration(5000);
                animator.start();
            }
        });

        animationSetBtn = (Button) findViewById(R.id.animation_set_btn);
        animationSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorA = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f, 1f);
//                ObjectAnimator animatorR=ObjectAnimator.ofFloat(textView,"rotation",0f,360f);
                ObjectAnimator animatorT = ObjectAnimator.ofFloat(textView, "translationX", curTranslationX, -500f, curTranslationX);
//                ObjectAnimator animatorS=ObjectAnimator.ofFloat(textView,"scaleX",0f,3f,0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animatorA).with(animatorT);
                animatorSet.setDuration(5000);
                animatorSet.start();
//这里我们向addListener()方法中传入这个适配器对象，由于AnimatorListenerAdapter中已经将每个接口都实现好了，
// 所以这里不用实现任何一个方法也不会报错。那么如果我想监听动画结束这个事件，就只需要单独重写这一个方法就可以了
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
            }
        });

        animationSetXmlBtn= (Button) findViewById(R.id.animation_set_xml_btn);
        animationSetXmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator= AnimatorInflater.loadAnimator(ObjectAnimation1Activity.this,R.animator.num_ani);
                animator.setTarget(textView);
                animator.start();
            }
        });

    }
}
