package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.RelativeLayout
import com.lixm.animationdemo.R
import com.lixm.animationdemo.adapter.ListViewAdapter
import com.lixm.animationdemo.bean.TestData
import com.lixm.animationdemo.customview.CustomHScrollView

class FullScrollActivity : AppCompatActivity() {

    public var mHead: RelativeLayout? = null
    private var mListView: ListView? = null
    private var mDataList: List<TestData>? = null
    private var mAdapter: ListViewAdapter? = null
    private var leftPos = 0 //用于记录CustomHScrollView初始位置
    private var mScrollView: CustomHScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_scroll)
        initView()
    }

    private fun initView() {
        mListView = findViewById(R.id.list_view)
        mScrollView = findViewById(R.id.h_scrollview)
        mHead = findViewById(R.id.head_layout)
        mHead!!.setBackgroundColor(resources.getColor(R.color.red_record_title_tab))
        mHead!!.setOnTouchListener(object :View.OnTouchListener{
            override  fun onTouch(v: View?, event: MotionEvent?): Boolean {
                //挡在表头和ListView控件上touch时，将事件分发给ScrollView
                var headScrollView: HorizontalScrollView
//                headScrollView=
                        return false
            }
        })
    }

}
