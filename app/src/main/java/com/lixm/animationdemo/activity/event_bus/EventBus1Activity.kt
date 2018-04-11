package com.lixm.animationdemo.activity.event_bus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.lixm.animationdemo.R
import com.lixm.animationdemo.bean.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.xutils.common.util.LogUtil

class EventBus1Activity : AppCompatActivity() {

    private val mContext: Context? = this
    val btn1 by lazy {
        findViewById(R.id.go_to_2) as Button
    }
    val tv1 by lazy {
        findViewById(R.id.text_1) as TextView
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus1)
        EventBus.getDefault().register(this)
        btn1?.setOnClickListener {
            val intent = Intent(this, EventBus2Activity::class.java)
            mContext?.startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
     fun Event(messageEvent: MessageEvent) {
        LogUtil.i("===接收到消息===")
        tv1!!.setText(messageEvent.message.getString("name"))
    }
}


