package com.lixm.animationdemo.activity

import android.os.Bundle
import com.lixm.animationdemo.R
import com.lixm.animationdemo.customview.UnBlockView
import org.jetbrains.anko.contentView

/**
 * @author Lixm
 * @date 2014-04-11
 * @detail 手势密码解锁
 */
class GestureSecretActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_secret)
        val blockView = contentView?.findViewById<UnBlockView>(R.id.gesture_view)
    }
}
