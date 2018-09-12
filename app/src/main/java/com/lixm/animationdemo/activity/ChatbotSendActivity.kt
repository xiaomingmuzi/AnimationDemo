package com.lixm.animationdemo.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.lixm.animationdemo.R
import com.lixm.animationdemo.utils.ChatbotSendUtil
import com.lixm.animationdemo.utils.SystemUtil

/**
 * 直接发送消息给钉钉群组
 */
class ChatbotSendActivity : BaseActivity() {

    val et_input: EditText by lazy {
        findViewById<EditText>(R.id.et_input)
    }

    val btn_send: Button by lazy {
        findViewById<Button>(R.id.btn_send)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot_send)
        btn_send.setOnClickListener(View.OnClickListener {
            var conent = et_input.text.toString().trim()
            if (TextUtils.isEmpty(conent)) {
                conent = "大家好~"
            }
            conent += "本条消息来源于："+SystemUtil.getDeviceBrand() + " "+SystemUtil.getSystemModel()+" "+SystemUtil.getSystemVersion()
            ChatbotSendUtil.sendData(conent)
        })
    }
}
