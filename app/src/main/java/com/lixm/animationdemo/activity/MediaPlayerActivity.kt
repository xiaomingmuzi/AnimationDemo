package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lixm.animationdemo.R
import com.lixm.animationdemo.utils.MediaPlayerUtils
import kotlinx.android.synthetic.main.activity_media_player.*

class MediaPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        imageView2.setOnClickListener {
            MediaPlayerUtils.getInstense().setPlayorStop("http://images-shichai.test.cnfol.com/thumbnail/201802/vote_1517451025.amr", imageView2)
        }
    }
}
