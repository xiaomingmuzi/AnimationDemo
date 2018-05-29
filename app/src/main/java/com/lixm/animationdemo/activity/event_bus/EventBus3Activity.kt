package com.lixm.animationdemo.activity.event_bus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.lixm.animationdemo.R
import com.lixm.animationdemo.bean.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.contentView

class EventBus3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus3)

        val btn3=contentView!!.findViewById<Button>(R.id.go_to_1)

        btn3!!.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "Li")
            val message = MessageEvent(bundle)
            EventBus.getDefault().post(message)
            finish()
        }
    }
}
