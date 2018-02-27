package com.lixm.kotlin.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import com.lixm.kotlin.util.FinishActivityManager

open class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FinishActivityManager.addActivity(this)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }
}
