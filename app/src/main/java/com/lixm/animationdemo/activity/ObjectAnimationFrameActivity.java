package com.lixm.animationdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.LevelImageView;


public class ObjectAnimationFrameActivity extends BaseActivity implements View.OnClickListener {
    private LevelImageView levelImageView;
    private ObjectAnimator levelAnimation;
    private Button eggBtn, goldBtn, upBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation_frame);
        levelImageView = (LevelImageView) findViewById(R.id.level_img);
        eggBtn = (Button) findViewById(R.id.egg);
        goldBtn = (Button) findViewById(R.id.gold);
        upBtn = (Button) findViewById(R.id.up);
        eggBtn.setOnClickListener(this);
        goldBtn.setOnClickListener(this);
        upBtn.setOnClickListener(this);


        levelImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        levelImageView.setImageResource(R.drawable.level_egg_img_res);


        levelAnimation = ObjectAnimator.ofInt(levelImageView, "imageLevel", 1, 13);
        //设置一个速度加速器.让动画看起来可以更贴近现实效果.
        levelAnimation.setInterpolator(new LinearInterpolator());
        levelAnimation.setDuration(2600);
        levelAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                levelImageView.setVisibility(View.GONE);
                super.onAnimationEnd(animation);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.egg:
                levelImageView.setVisibility(View.VISIBLE);
                levelImageView.setImageResource(R.drawable.level_egg_img_res);
                levelAnimation.start();
                break;
            case R.id.gold:
                levelImageView.setVisibility(View.VISIBLE);
                levelImageView.setImageResource(R.drawable.level_gold_img_res);
                levelAnimation.start();
                break;
            case R.id.up:
                levelImageView.setVisibility(View.VISIBLE);
                levelImageView.setImageResource(R.drawable.level_up_img_res);
                levelAnimation.start();
                break;
        }
    }
}
