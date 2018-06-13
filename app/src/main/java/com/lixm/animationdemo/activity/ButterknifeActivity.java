package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterknifeActivity extends AppCompatActivity {

   @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.textView3, R.id.button5, R.id.imageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView3:
                break;
            case R.id.button5:
                break;
            case R.id.imageView:
                Toast.makeText(this, "这是一个ImageView", Toast
                        .LENGTH_SHORT).show();
                break;
        }
    }
}
