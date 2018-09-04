package com.lixm.animationdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixm.animationdemo.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ObjectAnimation1Activity extends BaseActivity {

    private Context mContext;
    private TextView textView;
    private Button alphaBtn, rotationBtn, translationXBtn, scaleXBtn, animationSetBtn, animationSetXmlBtn, button_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation1);
        mContext = this;
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

        animationSetXmlBtn = (Button) findViewById(R.id.animation_set_xml_btn);
        animationSetXmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = AnimatorInflater.loadAnimator(ObjectAnimation1Activity.this, R.animator.num_ani);
                animator.setTarget(textView);
                animator.start();
            }
        });

        final ImageView imgGift = (ImageView) findViewById(R.id.imgGif);
        imgGift.setImageDrawable(mContext.getResources().getDrawable(R.drawable.level_gold_img_res));
        Button clapping = (Button) findViewById(R.id.clapping);
        clapping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(imgGift, "imageLevel", 1, 15);
                objectAnimator.setDuration(15 * 200);
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imgGift.setImageLevel(15);
                        Log.e("TAG", "========onAnimationEnd============");
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.e("TAG", "========onAnimationStart============");
                    }
                });
                objectAnimator.start();
            }
        });

        button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {

                        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=b7ff98db082fd3530ae91bc1b0e79d20ec7af0e4f4760e1ff879daa3bf7f20bf";
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
                        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

                        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"我就是我, 是不一样的烟火\"}}";
                        StringEntity se = null;
                        try {
                            se = new StringEntity(textMsg, "utf-8");
                            httppost.setEntity(se);

                            HttpResponse response = httpclient.execute(httppost);
                            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                                System.out.println(result);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

        });

    }
}
