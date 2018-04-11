package com.lixm.animationdemo.activity.event_bus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.lixm.animationdemo.R
import com.lixm.animationdemo.bean.MessageEvent
import org.greenrobot.eventbus.EventBus

class EventBus3Activity : AppCompatActivity() {

    val btn3 by lazy {
        findViewById(R.id.go_to_1) as Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus3)

        btn3.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("name","Li")
            val message= MessageEvent(bundle)
            EventBus.getDefault().post(message)
            finish()
        }
    }
}
