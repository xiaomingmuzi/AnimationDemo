package com.lixm.animationdemo.activity.event_bus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.lixm.animationdemo.R

class EventBus2Activity : AppCompatActivity() {

    private val mContext: Context? = this

    val btn2 by lazy {
        findViewById(R.id.go_to_3) as Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus2)
        btn2.setOnClickListener{
            val intent = Intent(this, EventBus3Activity::class.java)
            mContext?.startActivity(intent)
            finish()
        }
    }
}
