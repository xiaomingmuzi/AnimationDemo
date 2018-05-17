package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.lixm.animationdemo.R
import com.lixm.animationdemo.adapter.GlideRecycleViewAdapter
import com.lixm.animationdemo.bean.GlideBean

class GlideTestActivity : BaseActivity() {

    private val context = this
    private var mDatas = ArrayList<GlideBean>()
    private val adapter: GlideRecycleViewAdapter = GlideRecycleViewAdapter(context, mDatas)
    private val rv_1 by lazy {
        findViewById<RecyclerView>(R.id.rv_1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_test)
        val manager = GridLayoutManager(context, 2)
        rv_1.layoutManager = manager
        for (x in 0..10) {
            println(x)
            var bean = GlideBean(x, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526446784607&di=18a31bd64f00dc811bbb757de7bdb411&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F150717%2F14-150GF9355MO.jpg")
            mDatas.add(bean)
        }
        rv_1.adapter = adapter
    }
}
