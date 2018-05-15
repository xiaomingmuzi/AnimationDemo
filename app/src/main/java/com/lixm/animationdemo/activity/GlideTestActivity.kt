package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.lixm.animationdemo.R

class GlideTestActivity : BaseActivity() {

    private val context = this
    private val rv_1 by lazy{
        findViewById<RecyclerView>(R.id.rv_1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_test)
        val manager:GridLayoutManager
    }
}
