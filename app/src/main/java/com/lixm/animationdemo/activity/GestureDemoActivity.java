package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;

public class GestureDemoActivity extends BaseActivity implements GestureDetector.OnGestureListener{
//在这个类中需要实现OnGestureListener相关的接口

    private TextView textview;
    //声明一个文本标签
    private float fontSize = 30;
    //声明一个用于指示字体大小的变量，初始值为30
    GestureDetector detector;
    //声明一个手势检测器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_demo);
        textview = (TextView)findViewById(R.id.textView_helloWorld);
        textview.setTextSize(fontSize);
        //实例化这个文本标签并为其设置最初始的大小

        detector = new GestureDetector(this, this);
        //实例化这个手势检测器对象

    }

    //下面实现的这些接口负责处理所有在该Activity上发生的触碰屏幕相关的事件
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        return detector.onTouchEvent(e);
    }

    //我们就onScroll方法来进行补充
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        showShortToast("The method has been called - onScroll");
        //当该方法被调用时，通过一个Toast来提示用户哪个方法被调用了，下同

        if(distanceY >= 0){
            //和distanceX一样，distanceY这个参数有正有负，我们对该数值所处的不同范围分别处理
            //向上滚动的手势可以让文本标签的字号变小
            if(fontSize > 10)
                fontSize -= 5;
            //加一个判断的目的是防止字号太小或者太大，下同
            textview.setTextSize(fontSize);
            //将计算好的字号应用到文本标签上
        }
        else{
            //向下滚动的手势可以让文本标签的字号变小
            if(fontSize <60)
                fontSize += 5;
            textview.setTextSize(fontSize);
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e)
    {
        showShortToast("The method has been called - onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e)
    {
        showShortToast("The method has been called - onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
        showShortToast("The method has been called - onSingleTapUp");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {
        showShortToast("The method has been called - onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        showShortToast("The method has been called - onFling");
        return false;
    }

    //我们将Toast封装一下，以便调用时只需要传入待显示的消息，而略去了填写Context和持续时间等参数。
    public void showShortToast(String message){
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
