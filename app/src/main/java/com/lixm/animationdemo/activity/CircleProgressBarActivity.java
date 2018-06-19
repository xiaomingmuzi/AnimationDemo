package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.CircleProgressBar;
import com.lixm.animationdemo.customview.CircularPercentagView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleProgressBarActivity extends BaseActivity {

    private String TAG = getClass().getSimpleName();
    private CircleProgressBar circleProgressBar;
    private TextView txtProgress;
    private SeekBar sbFirst, sbSecond;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.cp)
    CircularPercentagView cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress_bar);

        ButterKnife.bind(this);

        circleProgressBar = (CircleProgressBar) findViewById(R.id.cpb);
        circleProgressBar.setDotDiameter(20);
        circleProgressBar.setMaxProgressWidth(5);
        circleProgressBar.setFirstProgressWidth(8);
        circleProgressBar.setSecondProgressWidth(10);
        circleProgressBar.setFirstProgress(80, 1000);
        circleProgressBar.setSecondProgress(40, 1000);

        txtProgress = (TextView) findViewById(R.id.text_progress);

        sbFirst = (SeekBar) findViewById(R.id.sb_first);
        sbSecond = (SeekBar) findViewById(R.id.sb_second);
        txtProgress.setText(80 + "");
        sbFirst.setProgress(80);
        sbSecond.setProgress(40);
        sbFirst.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());
        sbSecond.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());

    }

    @OnClick(R.id.btn_reset)
    public void onClick(View view) {
        cp.setEndAngle((int) (Math.random()*360));
    }


    class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener, CircleProgressBar.UpdateProgress {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.w(TAG, "progress = " + progress);
            if (R.id.sb_first == seekBar.getId()) {
                circleProgressBar.setFirstProgress(progress, MyOnSeekBarChangeListener.this);
                txtProgress.setText(progress + "");
            } else if (R.id.sb_second == seekBar.getId()) {
                circleProgressBar.setSecondProgress(progress, MyOnSeekBarChangeListener.this);
            }
            if (progress > 90) {
                circleProgressBar.setCanDisplayDot(false);
            } else circleProgressBar.setCanDisplayDot(true);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void updateFirstProgress(int progress) {
            sbFirst.setProgress(progress);
        }

        @Override
        public void updateSecondProgress(int progress) {
            sbSecond.setProgress(progress);
        }
    }
}
