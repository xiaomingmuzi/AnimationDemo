package com.lixm.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.CollegePlayerView;


public class CollegePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_player);
        CollegePlayerView collegePlayerView= (CollegePlayerView) findViewById(R.id.player_view);
    }
}
